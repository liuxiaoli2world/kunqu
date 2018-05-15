import React from "react";
import "../reset.css";
import "./detail.scss";
import {Icon,Input,Button,message} from "antd";
import {get,post} from "util/request.js";
import {
	SubEmiter,
	Emiter
} from 'util';
import {hashHistory,Link} from "react-router";


//曲典
export default class Detail extends React.Component{
	constructor(props){
		super(props);
		this.state={
			detail:{},
			sibList:[]
		}
	}
	componentDidMount(){
		const id = this.props.params.id;
		this.getData(id);
		message.config({
			top : "45%"
		})
	}
	componentWillReceiveProps (nextProps){
		window.scrollTo(0,0);
		const id = nextProps.params.id;
		(this.props.params.id!==id) && this.getData(id);
	}
	getData (id) {
		let infoDetail,sibList;
		get(`kunqu/article/query/${id}`)
			.then((data) => {
				if(data.meta.success){
					this.setState({
						detail:data.data[0]
					})
				}
			})
		get(`kunqu/article/selectPreAndAfter/${id}`)
			.then((data) => {
				if(data.meta.success){
					this.setState({
						sibList:data.data
					})
				}
			})
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
					"messageTheme": this.state.detail&&this.state.detail.title,
					"themeId": this.props.params.id,
					"themeType": "article",
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
	render() {
		const detail = this.state.detail;
		const sibList = this.state.sibList||[];
		const index = detail.articleImages ? (detail.articleImages.findIndex((value, index, arr) =>   
						(value.imageScene == '03')  
						)):-1;
		let {commentValue} = this.state;
		return (
			<div className="articleWrapper">
				<div className="banner">
					<img src={require('assets/images/banner.png')} />
				</div>
				<div className="txtContent">
					<div className="header">
						<p className="title">{detail.title}</p>
						<p className="info">
							<span>作者：{detail.author||'佚名'}</span>
							<span>来源：{detail.source||'未知'}</span>
							<span><Icon type="clock-circle-o" style={{marginRight:'5px'}}/>{detail.releaseDate?detail.releaseDate.substring(5,16):''} | 阅读：{detail.sumReadCount}</span>
						</p>
					</div>
					<div className="details">
						<div className="img">
							{(index > -1) ? (<img src={detail.articleImages[index].imageUrl} />) : null}
						</div>
						<div className="text">
							{detail.content}
						</div>
					</div>
					{(sibList.length>0) ? 
						(<div className="pre_next_info clearfix">
							{sibList[0]?<Link to={`article/detail/${sibList[0].articleId}`} title="上一篇" className="left">上一篇：  {sibList[0].title} </Link> : ''}
							{sibList[1]?<Link to={`article/detail/${sibList[1].articleId}`} title="下一篇" className="right">下一篇：  {sibList[1].title} </Link> : ''}
						</div>) : null}
						<div className="comment-part clearfix">
							<Input type="textarea" maxLength="500" onChange={this.handleInput} value={commentValue}/>
							<Button onClick={this.handleComment.bind(this)}>发表评论</Button>
						</div>
				</div>
			</div>
		)
	}
}

