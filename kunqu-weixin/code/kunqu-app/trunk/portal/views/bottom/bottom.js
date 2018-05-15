import React from 'react';
import ReactDOM from 'react-dom';
import './bottom.css';
import '../common.js'
import {
	Link,
	hashHistory
} from "react-router";
			
class BottomItem extends React.Component{
	render(){
		return (
			<Link to={this.props.url}><li className="bottom-item" className={this.props.className}>
				<img src={this.props.img}/>
				<div className="bottom-title oneline">{this.props.text}</div>
			</li></Link>
		)
	}
}

export default class BottomList extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			bottomListData : [],
			currentIndex: 0
		}
	}
	changeBottomState(){
		if(hashHistory.getCurrentLocation().pathname.indexOf("/read")!==-1){
			return 1;
		}else if(hashHistory.getCurrentLocation().pathname.indexOf("/drama")!==-1){
			return 2;
		}else if(hashHistory.getCurrentLocation().pathname.indexOf("/opera")!==-1){
			return 3;
		}else if(hashHistory.getCurrentLocation().pathname.indexOf("/pcenter")!==-1||hashHistory.getCurrentLocation().pathname.indexOf("/records")!==-1){
			return 4;
		}else{
			return 0;
		}
	}
	componentDidMount(){
		this.setState({bottomListData : [
			{
				img:require("assets/images/bottom-icon-1.png"),
				imgCurrent:require("assets/images/bottom-icon-1-cur.png"),
				text:"首页",
				url:"/"
			},{
				img:require("assets/images/bottom-icon-2.png"),
				imgCurrent:require("assets/images/bottom-icon-2-cur.png"),
				text:"阅读",
				url:"/read"
			},{
				img:require("assets/images/bottom-icon-3.png"),
				imgCurrent:require("assets/images/bottom-icon-3-cur.png"),
				text:"剧典",
				url:"/drama"
			},{
				img:require("assets/images/bottom-icon-4.png"),
				imgCurrent:require("assets/images/bottom-icon-4-cur.png"),
				text:"曲典",
				url:"/opera"
			},{
				img:require("assets/images/bottom-icon-5.png"),
				imgCurrent:require("assets/images/bottom-icon-5-cur.png"),
				text:"我的",
				url:"/pcenter"
			}	
		]})
		
	}
	render(){
		var currentIndex = this.changeBottomState();
		let bottomList = this.state.bottomListData.map((item,index) => {
			var curImg;
			var className=""
			if(currentIndex == index){
				curImg = item.imgCurrent
				className="bottom-current"
			}else{
				curImg = item.img
			}
			return (
				<BottomItem img={curImg} className={className} text={item.text} key={index} url={item.url}/>
			)
		})
		return(			
			<div className="container">
				{this.props.children}
				<ul className="bottom-list">
					{bottomList}
				</ul>
			</div>
		)
	}	
}