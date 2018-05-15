import React from "react";
import ReactDOM from "react-dom";
import {
    hashHistory,
    Link
} from "react-router";
import {Input,Button,message} from "antd";
import SuggestCont from "./SuggestCont.js";
import {get,post} from "util/request.js";
import {
	SubEmiter,
	Emiter
} from 'util';
import "./drama.css";

export default class DramaDetail extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			displayPart : 0,
			videoParts : [],
			source : "",
			dramaName: "",
			dramaDesc: "",
			current:0,
		}		
	}
	componentDidMount(){
		this.getData(this.props.params.id)
		message.config({
			top:"45%"
		})
	}
	componentWillReceiveProps(nextProps){
		this.getData(nextProps.params.id)
	}
	getData(id) {
		get(`kunqu/dramapart/queryParts?dramaId=${id}`).then( (msg) =>　{
                this.setState({
					videoParts:msg.data,
					source:msg.data[0].partUrl
				})
				var myPlayer = $("#my-video").get(0);
				myPlayer.src = msg.data[0].partUrl;
				myPlayer.play();
		})
		get(`kunqu/drama/queryDetail/${id}`).then((msg) => {
                this.setState({
					dramaDesc:msg.data.dramaDesc,
					dramaName:msg.data.dramaName
				})
		})
	}
	handleClick(i,index,event){
		if(typeof(i) == "object"){
			this.setState({
				source : i.partUrl,
				current:index
			})
			/*var myPlayer = videojs('my-video');
			myPlayer.src({type: "video/mp4", src: i.partUrl});
			myPlayer.play();*/
			
			var myPlayer = $("#my-video").get(0);
			myPlayer.src = i.partUrl;
			myPlayer.play();
		}else{
			this.setState({
				displayPart : i
			})
			$(".video-parts-navlist li").removeClass("current").eq(i).addClass("current");
		}
	}
	renderVideoCont(){
		var videoList = [];
		var that = this;
		videoList = this.state.videoParts.map(function(item,index){
			return(
				<li onClick={that.handleClick.bind(that,item,index)} key={index} className={that.state.current==index?"current":""}>{item.partName}</li>
			)
		})
		switch(this.state.displayPart){
			case 0:
				return(
					<ul className="video-parts-list">
						{videoList}
					</ul>
				)
			break;
			case 1:
				return(
					<div className="video-desc">			
						{this.state.dramaDesc&&this.state.dramaDesc.length>250?this.state.dramaDesc.substring(0,250)+'…':this.state.dramaDesc}
					</div>
				)
			break;
			default:
				return ""
			break;
		}
	}
	renderVideoPart(){
		return(<div className="m fl">
				<video id="my-video" className="video-js" preload="auto" width="100%" 
				  poster={require("assets/images/poster.gif")} data-setup="{}" autoPlay controls>
					{/*<source src={this.state.source} type="video/mp4"/>
					<source src={this.state.source} type="video/webm"/>
					<source src={this.state.source} type="video/ogg"/>*/}
					<p className="vjs-no-js">
					  To view this video please enable JavaScript, and consider upgrading to a web browser that
					  <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
					</p>
				  </video>
			</div>)
	}

	handleInput = (e)=>{
		this.setState({
			commentValue : e.target.value
		})
	}
	handleComment = ()=>{
		if(localStorage.getItem("user")){
			if(this.state.commentValue){
				let record = {
					"messageContent": this.state.commentValue,
					"messageName": localStorage.getItem("user"),
					"messageTheme": this.state.dramaName,
					"themeId": this.props.params.id,
					"themeType": "drama",
					"userId": localStorage.getItem("id")
				}
				post(`kunqu/messageboard/add`,record)
				.then(res=>{
					console.log(res)
					if(res && res.meta && res.meta.success){
						message.success('评论成功！')
						this.setState({
							commentValue : ''
						})
					}else{
						message.error('评论失败！')
					}
				}).catch(e=>{
					message.error("评论失败！")
				})
			}else{
				return false;
			}
		}else{
			Emiter.emit("goLogin");
		}
	}
	render(){
		let {commentValue} = this.state;
		return(
			<div className="container">
				<div className="crumb">
					当前位置：<Link to="/drama">剧典</Link><span>></span>剧典推荐
				</div>
				<div className="detail-video overflow-hidden">
					{this.renderVideoPart()}
					<div className="video-part-list fl">
						<div className="video-part-name">
							{this.state.dramaName}
						</div>
						<ul className="video-parts-navlist overflow-hidden">
							<li onClick = {this.handleClick.bind(this,0)} className="fl current">片段</li>
							<li onClick = {this.handleClick.bind(this,1)} className="fl">视频简介</li>
						</ul>
						{this.renderVideoCont()}
						<div className="control-bar"></div>
					</div>
				</div>
				<SuggestCont text="相关剧典" source={`/kunqu/dramaabout/queryAbout.json?pageSize=8&pageNum=1&dramaId=${this.props.params.id}`}/>
				<div className="comment-part clearfix">
					<Input type="textarea" maxLength="500" onChange={this.handleInput} value={commentValue}/>
					<Button onClick={this.handleComment.bind(this)}>发表评论</Button>
				</div>
			</div>
		)
	}
}