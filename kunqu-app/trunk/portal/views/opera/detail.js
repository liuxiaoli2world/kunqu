import React from "react";
import ReactDOM from 'react-dom'; 
import config from "config";
import {Link,hashHistory} from "react-router";
import {
	SubEmiter,
	Emiter
} from 'util';
import {Carousel,Tabs,Menu,Input,Button,message} from "antd";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";
import Player from "./MusicControl.js"
import './opera.css'

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

export default class OperaDetail extends React.Component{
	constructor(props) {
        super(props);
		
		this.state = {
			operaListData :[],
			operaLen:0,
			specialDesc : "",
			curIndex : this.props.location.state?this.props.location.state : 0,
			specialName : '',
			source:'',
			dirId:''
		}
    }
	componentWillMount(){
		var that = this;
		get(`kunqu/special/query/${this.props.params.id}`).then(
            (msg) => {
				this.setState({
					operaListData : msg.data.operaList,
					operaLen : msg.data && msg.data.operaList && msg.data.operaList.length,
					specialDesc : msg.data.specialDesc,
					specialName : msg.data.specialName,
					source : msg.data.source,
					dirId:msg.data.dirId
				});
            },
		)
		message.config({
			top:"45%"
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
					"messageTheme": this.state.specialName,
					"themeId": this.props.params.id,
					"themeType": "special",
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
		return (
			<div className="container2 opera-detail">
				<div className="submenu"><Link to="/opera" className="link">音频</Link>><span>曲典推荐</span></div>
				<div className="special-name">{this.state.specialName}</div>
				<Player tracks={this.state.operaListData} specialDesc={this.state.specialDesc} specialName={this.state.specialName} source={this.state.source} curIndex={this.state.curIndex} dirId={this.state.dirId} operaLen={this.state.operaLen}/>
				<div className="comment-part clearfix">
					<Input type="textarea" maxLength="500" onChange={this.handleInput.bind(this)} value={commentValue}/>
					<Button  onClick={this.handleComment.bind(this)}>发表评论</Button>
				</div>
			</div>
		)
	}
}
