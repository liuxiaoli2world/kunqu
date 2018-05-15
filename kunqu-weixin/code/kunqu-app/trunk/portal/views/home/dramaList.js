import React from 'react';
import ReactDOM from 'react-dom';
import DramaItem from '../drama/item.js';
import '../drama/drama.css';
import '../common.js';
import {Link,hashHistory} from "react-router";

export default class DramaList extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			dramaListData : []
		}
	}
	componentWillReceiveProps(nextprops){
		this.setState({dramaListData : nextprops.dramaList})
	}
	render(){
		let dramaList = this.state.dramaListData.map((item,index) => {
			let currentImage
			for(let i=0;i<item.dramaImages.length;i++){
				if(item.dramaImages[i].imageScene=="wx_01"){
					currentImage = item.dramaImages[i].imageUrl;
				}
			}
			return (
				<Link to={`/drama/detail/${item.dramaId}`} key={index}><DramaItem img={currentImage?currentImage:require('assets/images/drama-1.png')} text={item.dramaName} dramaDesc={item.dramaDesc} key={index}/></Link>
			)
		})
		return(
			<ul className="drama-list">
				{dramaList}
			</ul>
		)
	}	
}