import React from 'react';
import ReactDOM from 'react-dom';
import OperaList from './operaList.js';
import DramaList from './dramaList.js';
import ReadList from './readList.js';
import './home.css';
import './title.scss';
import {get,post} from "util/request.js";
import {Link,hashHistory} from "react-router";

class Title extends React.Component{
	constructor(props){
		super(props);
	}
	render(){
		const {img,name,linkname} = this.props;
		return(
			<div className="title-more clearfix" style={(linkname=='/opera') ? {marginBottom:'-6px'} : {}}>
				<img src={img} className="more" style={(linkname=='/drama') ? {width:'16px'} : {}}/>
				<div className="title left">{name}</div>
				<Link to={linkname}><span className="right-more">更多 >></span></Link>
			</div>
		)
	}
}

export default class Home extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			dramaList:[],
			specialList:[],
			readList:[]
		}
	}
	componentWillMount(){
		document.title = '主页-时代昆曲';
		get('kunqu/drama/query?pageNum=1&pageSize=4&type=0')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						dramaList:data.data.list
					})
				}
			})
		get('kunqu/special/queryAll?pageNum=1&pageSize=6')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						specialList:data.data.list
					})
				}
			})
		post('kunqu/article/selectByStyle?pageNum=1&pageSize=5&type=2')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						readList:data.data.list
					})
				}
			})
	}
	render(){
		
		return(
		<div className="container">
			<img src={require('assets/images/banner.png')} className="banner"/>
			<Title img={require('assets/images/drama-more.png')} name='剧典推荐' linkname="/drama"/>			
			<DramaList dramaList={this.state.dramaList}/>

			<Title img={require('assets/images/opera-more.png')} name='曲典推荐' linkname="/opera"/>
			<OperaList specialList={this.state.specialList}/>

			<Title img={require('assets/images/read-more.png')} name='阅读推荐' linkname="/read"/>
			<ReadList readList={this.state.readList}/>
		</div>
		)
	}	
}