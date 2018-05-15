import React from "react";
import config from "config";
import {Link,hashHistory} from "react-router";
import {Carousel,Tabs,Menu} from "antd";
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
		get('kunqu/special/queryAll?pageNum=1&pageSize=8&type=5')
			.then((msg) => {
				this.setState({
					suggestListData:msg.data.list
				})
			})
	}
	renderDescText(desc){
		if(desc && desc.length>50){
			desc = desc.substring(0,50) + "…"
		}
		return desc;
	}
	handleClick (i,event){
		this.props.callbackParent(i);
	}
	changeImg = (event)=>{
	}
	render(){
		var suggestListData = this.state.suggestListData ? this.state.suggestListData : [];
		var that = this;
		var operaListName = ["default","default","default","gray","gray"];
		var suggestList = suggestListData.map(function(item,index){	
			var imgs = item.imageList;
			var imgUrl = "";
			if(imgs) {
				for ( var i = 0 ; i<imgs.length; i++){
					if(imgs[i].imageScene == "01"){
						imgUrl = imgs[i].imageUrl;
					}
				}
			}
			var operaList = item.operaList;
			var length = item.operaList&&item.operaList.length;
			var classes = ["default","default","default","gray","gray"];
			var operas = [];
			var pathname = "/opera/detail/" + item.specialId
			var specialId = item.specialId
			if(operaList){
					operas = operaList.map(function(item,index){
					var cur = index + 1;
					return(<Link to={{ pathname:`/opera/detail/${specialId}`,state:index}} key={index}><li className={classes[index]} key={index}><span className="index" style={{padding:'2px 4px'}}>{'0'+cur}</span>{item.operaName}<div className="right-arrow"></div></li></Link>)
				})
			}
			var newOperas = [];
			for(var i=0 ; i<5; i++){
				if(operas[i]){
					newOperas.push(operas[i])
				}else{
					newOperas.push(<li key={i}></li>)
				}
			}
			return (
				<div className="suggest-item fl" key={index}>
					<div className="suggest-cont-title oneline">
						{item.specialName}
					</div>
					<div className="suggest-img">
						<img src={imgUrl} className="qd-pic"/>
						<div className="suggest-img-title">
							播放<span className="play-num">{item.palyAmount?item.palyAmount:0}</span>次
						</div>
					</div>
					<ul className="suggest-list">
						{newOperas}
					</ul>
					<Link to={{ pathname:`/opera/detail/${specialId}`,state:0}} className="play-list">
						<div className="play-btn"></div>
					</Link>
				</div>
			)
		})
		return(
			<div className="suggest-cont">
				<div className="suggest-title overflow-hidden">
					<img src={require("../assets/images/suggest-icon.png")} className="suggest-icon"/>
					<span className="fl">曲典推荐</span>
					<Link to={`/classify/${this.props.type}/${this.props.value}`}><span className="more fr" >更多>></span></Link>
				</div>
				<div className="suggest-list">
					{suggestList}
				</div>
			</div>
		)
	}		
}
