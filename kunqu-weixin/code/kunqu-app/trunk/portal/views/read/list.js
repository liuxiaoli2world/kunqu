import React from 'react';
import ReactDOM from 'react-dom';
import ReadItem from './item.js';
import '../common.js';
import ReactRefresh from '../react-fresh-and-getmore.js';
import '../spinner.css'
import {
	Link,
	hashHistory
} from "react-router";

export default class ReadList extends ReactRefresh{	
	constructor(props){
		super(props);
		this.state = {
			readListData : []
		}
		
	}
	componentWillReceiveProps(nextProps){
		this.setState({readListData : nextProps.readList})
		window.addEventListener("scroll",this.viewDidScroll,false)
	}
	render(){
		let readList = this.state.readListData.map((item,index) => {
			let currentImage
			for(let i=0;i<item.articleImages.length;i++){
				if(item.articleImages[i].imageScene=="wx_01"){
					currentImage = item.articleImages[i].imageUrl;
				}
			}
			return (
				<Link to={`/read/detail/${item.articleId}`} key={index}><ReadItem img={currentImage?currentImage:require('assets/images/read-1.png')} text={item.title} desc={item.articleDesc} key={index}/></Link>
			)
		})
		return(
			<div className="tableView container">
				<ul className="read-list" style={{'paddingBottom':'1.8rem','border':'none','marginTop':'10px'}}>
					{readList}
				</ul>
			</div>
		)
	}	
}
