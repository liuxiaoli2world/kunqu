import React from 'react';
import ReactDOM from 'react-dom';
import OperaItem from './item.js';
import '../common.js'
import ReactRefresh from '../react-fresh-and-getmore.js';
import '../spinner.css'
import {
	Link,
	hashHistory
} from "react-router";


export default class OperaList extends ReactRefresh{	
	constructor(props){
		super(props);
		this.state = {
			operaListData : []
		}		
	}
	componentWillReceiveProps(nextProps){
		this.setState({operaListData : nextProps.specialList})
		window.addEventListener("scroll",this.viewDidScroll,false)
	}
	render(){
		let operaList = this.state.operaListData.map((item,index) => {
			let currentImage
			for(let i=0;i<item.imageList.length;i++){
				if(item.imageList[i].imageScene=="wx_01"){
					currentImage = item.imageList[i].imageUrl;
				}
			}
			return (
				<Link to={`/opera/detail/${item.specialId}`} key={index}><OperaItem img={currentImage?currentImage:require('assets/images/opera-1.png')} text={item.specialName} key={index}/></Link>
			)
		})
		return(
		<div className="tableView container">
			<ul className="opera-list" style={{'paddingBottom':'1.8rem','border':'none','marginTop':'4px'}}>
				{operaList}
			</ul>
		</div>
		)
	}	
}
