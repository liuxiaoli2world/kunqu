import React from 'react';
import ReactDOM from 'react-dom';
import ReadItem from '../read/item.js';
import '../read/read.css';
import '../common.js';
import {Link,hashHistory} from "react-router";

export default class ReadList extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			readListData : []
		}
	}
	componentWillReceiveProps(nextProps){
		this.setState({readListData : nextProps.readList})
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
				// <ReadItem img={require('assets/images/read-1.png')} text={item.title} desc={item.articleDesc} key={index}/>
				<Link to={`/read/detail/${item.articleId}`} key={index}><ReadItem img={currentImage?currentImage:require('assets/images/read-1.png')} text={item.title} desc={item.articleDesc} key={index}/></Link>
				// <ReadItem img={currentImage?currentImage:require('assets/images/read-1.png')} text={item.title} desc={item.articleDesc} key={index}/>
			)
		})
		return(
			<ul className="read-list">
				{readList}
			</ul>
		)
	}	
}
