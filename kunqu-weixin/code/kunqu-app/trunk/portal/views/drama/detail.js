import React from 'react';
import ReactDOM from 'react-dom';
import '../common.js';
import {get,post} from "util/request.js";
import './detail.css'

export default class DramaDetail extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			dramaListData : [],
			dramaDesc:"",
			dramaName:"",
			videoParts:[],
			current:0,
			source:"",
			imageUrl:""
		}
	}
	
	componentDidMount(){
		var that = this;
		var id = this.props.params.id;
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
			this.setStorage();
			const index = msg.data.dramaImages.findIndex((val) => val.imageScene=='wx_01');
			document.title = msg.data.dramaName;
		    this.setState({
				dramaDesc:msg.data.dramaDesc,
				dramaName:msg.data.dramaName,
				dramaCover:msg.data.dramaImages[index].imageUrl
			})
		})
	}
	
	setStorage() {
        const id = this.props.params.id;
        if(localStorage.drama){
            let arr = localStorage.getItem('drama');
            let drama = [
                id,
                ...arr.split(',')
            ];
            let s = new Set(drama);
            drama = [...s].slice(0,12).join(',');
            localStorage.setItem('drama',drama)
        }else{
            localStorage.setItem('drama',id);
        }
    }
	
	handleClick(i,index,event){
			document.title = i.partName;
			this.setState({
				source : i.partUrl,
				current:index
			})
			var myPlayer = $("#my-video").get(0);
			myPlayer.src = i.partUrl;
	}
	
	renderVideoPart(){
		return(<div className="m fl">
				<video id="my-video" className="video-js" width="100%" style={{objectFit:'cover'}} 
				   data-setup="{}" preload={true}  controls="controls" poster={require("assets/images/poster.gif")}>
					<p className="vjs-no-js">
					  To view this video please enable JavaScript, and consider upgrading to a web browser that
					  <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
					</p>
				</video>
				
			</div>)
	}
	
	render(){
		var that = this;
		let dramaList = this.state.videoParts.map((item,index) => {
			return (
				<li onClick={that.handleClick.bind(that,item,index)} key={index} className={that.state.current==index?"current":""}>
					<img className="suggest-img" src={that.state.dramaCover}/>
					<div className="suggest-title">{item.partName}</div>
				</li>
			)
		})
		return(
		<div style={{paddingBottom:'50px',fontSize:'0'}}>
			{this.renderVideoPart()}
			<SuggestCont dramaDesc={this.state.dramaDesc}/>
			{this.state.videoParts.length>0 ? 
				<div className="suggest-list-title title">相关视频推荐</div>
				: null
			}
			
			<ul className="suggest-list">
				{dramaList}
			</ul>
		</div>
		)
	}	
}

class SuggestCont extends React.Component{
	constructor(props){
		super(props);
		this.state={
			all : 0
		}
	}

	readAll() {
		const {all} = this.state;
		this.setState({
			all:!all
		})
	}

	render(){
		const {all} = this.state;
		return(
			<div className="suggest-detail">
				<div className="title">
					内容简介
				</div>
				{!all ? (
					<div className="suggest-cont">
						{this.props.dramaDesc}
						<div className="readAll" onClick={this.readAll.bind(this)}></div>
					</div>)
					 : (
					<div className="allCon">
						{this.props.dramaDesc}
					</div>
					 )
				}
			</div>
		)
	}
}
