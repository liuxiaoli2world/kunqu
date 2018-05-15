import React from 'react';
import ReactDOM from 'react-dom';
import OperaItem from '../opera/item.js';
import '../opera/opera.css';
import '../common.js'
import {Link,hashHistory} from "react-router";

export default class OperaList extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			operaListData : []
		}
	}
	componentWillReceiveProps(nextprops){
		this.setState({operaListData : nextprops.specialList})
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
				// <OperaItem img={currentImage?currentImage:require('assets/images/opera-1.png')} text={item.specialName} key={index}/>
			)
		})
		return(
			<ul className="opera-list">
				{operaList}
			</ul>
		)
	}	
}
