import React from 'react';
import ReactDOM from 'react-dom';
import DramaItem from './item.js';
import './drama.css';
import '../common.js';
import ReactRefresh from '../react-fresh-and-getmore.js';
import '../spinner.css'
import {
	Link,
	hashHistory
} from "react-router";

export default class DramaList extends ReactRefresh{	
	constructor(props){
		super(props);
		this.state = {
			dramaListData : []
		}		
	}
	componentDidMount(){
		window.addEventListener("scroll",this.viewDidScroll,false)
	}
	componentWillReceiveProps(nextProps){
		this.setState({dramaListData : nextProps.dramaList})
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
		<div className="tableView container">
			<ul className="drama-list" style={{'paddingBottom':'1.8rem','border':'none','marginTop':'10px'}}>
				{dramaList}
			</ul>
		</div>
		)
	}	
}