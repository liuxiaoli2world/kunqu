import React from 'react';
import ReactDOM from 'react-dom';
import {
	Link,
	hashHistory
} from "react-router";
import {get,post} from "util/request.js";
import './pcenter.css'

export default class PCenterModule extends React.Component{
	constructor(props){
		super(props);	
		this.state={
			playRecords:[],
			dramaRecords:[],
			readRecords:[]
		}	
	}
	componentDidMount(){
		document.title = '个人中心';
		const article = localStorage.getItem('article');
		const drama = localStorage.getItem('drama');
		const special = localStorage.getItem('special');
		special && post(`kunqu/special/selectSpecialBySpecialIds`,{'ids':special})
					.then((res) => {
						if(res.meta.success){
							this.setState({
								playRecords:res.data
							})
						}
					})
		drama && post(`kunqu/drama/queryDramaByIds`,{'ids':drama})
					.then((res) => {
						if(res.meta.success){
							this.setState({
								dramaRecords:res.data
							})
						}
					})
		article && post('kunqu/article/selectByGroupId',{'ids':article})
					.then((res)=>{
						if(res.meta.success){
							this.setState({
								readRecords:res.data
							})	
						}
					})
	}
	
	GetQueryString(name){
    	const reg = new RegExp(`(^|&)${name}=([^&]*)(&|$)`);
    	const r = window.location.search.substr(1).match(reg);
    	if(r!=null)return  r[2]; return null;
	}
	render(){
		const {dramaRecords,playRecords,readRecords} = this.state;
		const headImgUrl = decodeURIComponent(this.GetQueryString('headimgurl'));
		const nickName = decodeURI(this.GetQueryString('nickname'));
		return(
			<div className="container" style={{paddingBottom:'1rem',background:'#f8f8f8'}}>
				<div className="avatar">
					<div className="headImg" >
						<img src={headImgUrl} className="logo" alt=""/>
					</div>
					<div className="point"></div>
					<div className="point2"></div>
					<div className="nickName">{nickName}</div>
				</div>
				<PCenterTitle iconImage={require('assets/images/play-records.png')} titleName="剧典记录" type="0" listData={this.state}/>
				<PCenterCont listData={dramaRecords} mode="drama"/>
				<PCenterTitle iconImage={require('assets/images/opera-records.png')} titleName="曲典记录" type="1" listData={this.state}/>
				<PCenterCont listData={playRecords} mode="opera"/>
				<PCenterTitle iconImage={require('assets/images/read-records.png')} titleName="阅读记录" type="2" listData={this.state}/>
				<PCenterCont listData={readRecords} mode="read"/>
			</div>
		)
	}	
}

class PCenterTitle extends React.Component{
	constructor(props){
		super(props);
	}
	render(){
		const {type,listData} = this.props;
		return(
			<div className="p-center-title">
				<img src={this.props.iconImage} className="icon-image"/>
				{this.props.titleName}
				<Link to={{pathname:'/records',state:{type:type,listData:listData}}}><img src={require('assets/images/right-arrow.png')} className="right-arrow"/></Link>
			</div>	
		)
	}
}

class PCenterCont extends React.Component{
	constructor(props){
		super(props);
	}
	render(){
		const {mode,listData} = this.props;
		let contList = listData.map((item,index)=>{
			const images = (mode == 'drama') ? item.dramaImages : (mode == 'opera') ? item.imageList : item.articleImages;
			const i = images.findIndex((val) => val.imageScene=='wx_01')
			const name = (mode == 'drama') ? item.dramaName : (mode == 'opera') ? item.specialName : item.title;
			const id = (mode == 'drama') ? item.dramaId : (mode == 'opera') ? item.specialId : item.articleId;
			if(index<3){
				return(
					<li key={index}>
						<Link to={`/${mode}/detail/${id}`} key={index}>
							<img src={images[i].imageUrl}/>
							<div className="img-cont">{name}</div>
						</Link>
					</li>
				)
			}
		})
		return(
			<ul className="p-center-cont">
				{contList}
			</ul>	
		)
	}
}