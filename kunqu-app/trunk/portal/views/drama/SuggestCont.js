import React from "react";
import config from "config";
import {Link,hashHistory} from "react-router";
import {Carousel,Tabs,Menu} from "antd";
import "../reset.css";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;


export default class SuggestCont extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			hotContListData : []			
		}
	}
	componentDidMount(){
		var that = this;
		get(this.props.source).then((msg) => {
                that.setState({
					suggestListData : msg.data.list
				});
		})
	}
	renderDescText(desc){
		if(desc.length>50){
			desc = desc.substring(0,50) + "…"
		}
		return desc;
	}
	render(){
		var suggestListData = this.state.suggestListData ? this.state.suggestListData : [];
		var that = this;
		var suggestList = suggestListData.map(function(item,index){	
			const imgI = item.dramaImages.findIndex((val) => {
				return val.imageScene == '01'
			})
			return (
				<Link to={`/drama/detail/${item.dramaId}`} className="suggest-item fl" key={index}  style={{border:'none'}}>
					<div className="suggest-pic">
						<img src={imgI>=0 ? item.dramaImages[imgI].imageUrl : ""} className="suggest-pic-img"/>
						<img src={require("assets/images/play-icon.png")} className="play-icon"/>
						<span className="play-times">播放量：<span>{item.palyAmount}</span>次</span>
					</div>
					<div style={{fontSize:'16px',lineHeight:'38px',color:'#333'}}>
						{item.dramaName}
					</div>
					<div className="suggest-cont-desc">
						{that.renderDescText(item.dramaDesc)}
					</div>
				</Link>
			)
		})
		return(
			<div className="suggest-cont">
				<div className="suggest-title overflow-hidden">
					<img src={require("../assets/images/suggest-icon.png")} className="suggest-icon"/>
					<h1 className="fl">{this.props.text}</h1>
					<Link to={'/classify'}><span className="more fr" >更多>></span></Link>
				</div>
				<div className="suggest-list">
					{suggestList}
				</div>
			</div>
		)
	}		
}
