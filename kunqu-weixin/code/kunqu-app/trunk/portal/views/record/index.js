import React from 'react';
import ReactDOM from 'react-dom';
import {get,post} from "util/request.js";
import {Link,hashHistory} from "react-router";
import './records.css'
import DramaList from '../drama/list.js';
import OperaList from '../opera/list.js';
import ReadList from '../read/list.js';

class VideoRecord extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			recordListData : []
		}
		
	}
	componentDidMount(){
		this.setState({
			recordListData:this.props.data
		})
	}
	render(){
		let recordList = this.state.recordListData.map((item,index) => {
			const i = item.dramaImages.findIndex((val) => val.imageScene == 'wx_01');
			return (				
				<li className="record-item" key={index}>
					<Link to={`/drama/detail/${item.dramaId}`}>
					<img src={item.dramaImages[i].imageUrl} className="left-cont"/>
					<img src={require('assets/images/play-drama.png')} className="play-drama"/>
					<div className="right-cont">
						<div className="record-title oneline">{item.dramaName}</div>
						<div className="record-desc ">{item.dramaDesc}</div>					
					</div>
					</Link>
				</li>

			)
		})
		return(
			<div className="tableView container">
				{/*<ul className="record-list">
					{recordList}
				</ul>*/}
				<DramaList dramaList={this.state.recordListData} />
			</div>
		)
	}	
}


class MusicRecord extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			recordListData : []
		}
		
	}
	componentDidMount(){
		this.setState({
			recordListData:this.props.data
		})
	}
	render(){
		let recordList = this.state.recordListData.map((item,index) => {
			const i = item.imageList.findIndex((val) => val.imageScene == 'wx_01');
			return (				
				<li className="record-item" key={index}>
					<Link to={`/opera/detail/${item.specialId}`}>
					<img src={item.imageList[i].imageUrl} className="left-cont"/>
					<img src={require('assets/images/play-icon.png')} className="play-icon"/>
					<div className="right-cont">
						<div className="record-title oneline">{item.specialName}</div>
						{/*<div className="record-date oneline">{item.date}</div>*/}
						<div className="record-desc ">{item.specialDesc}</div>					
					</div>
					</Link>
				</li>

			)
		})
		return(
			<div className="tableView container">
				{/*<ul className="record-list">
					{recordList}
				</ul>*/}
				<OperaList specialList={this.state.recordListData}/>
			</div>
		)
	}	
}


class ReadRecord extends React.Component{	
	constructor(props){
		super(props);
		this.state = {
			recordListData : []
		}
		
	}
	componentDidMount(){
		console.log(this.props)
		this.setState({
			recordListData:this.props.data
		})
	}
	render(){
		let recordList = this.state.recordListData.map((item,index) => {
			const i = item.articleImages.findIndex((val) => val.imageScene == 'wx_01');
			return (				
				<li className="record-item" key={index}>
					<Link to={`/read/detail/${item.articleId}`}>
					<img src={item.articleImages[i].imageUrl} className="left-cont"/>
					<div className="right-cont">
						<div className="record-title oneline">{item.title}</div>
						{/*<div className="record-date oneline">{item.date}</div>*/}
						<div className="record-desc ">{item.content}</div>					
					</div>
					</Link>
				</li>

			)
		})
		return(
			<div className="tableView container">
				{/*<ul className="record-list">
					{recordList}
				</ul>*/}
				<ReadList readList={this.state.recordListData} />
			</div>
		)
	}	
}

export default class extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			index : 0
		}
	}
	componentDidMount(){
		const {type} = this.props.location.state;
		// console.log(listData);
		this.setState({
			index: parseInt(type)
		})
	}
	handleClick = (index,event) => {
		this.setState({
			index:index,
		})
	}
	renderRecordStatement(index){
		const {listData} = this.props.location.state;
		switch(index){
			case 0:
			return (<VideoRecord data={listData.dramaRecords}/>);
			case 1:
			return (<MusicRecord data={listData.playRecords}/>);
			case 2:
			return (<ReadRecord data={listData.readRecords}/>);
		}
	}
	render(){
		let classes = [];
		for(let i=0; i<3; i++){
			if(i==this.state.index){
				classes.push("current")
			}else{
				classes.push("")
			}
		}
		return(
		<div>
			<ul className="record-nav-list">
				<li className={classes[0]} onClick={this.handleClick.bind(this,0)}>剧典播放记录</li>
				<li className={classes[1]} onClick={this.handleClick.bind(this,1)}>曲典播放记录</li>
				<li className={classes[2]} onClick={this.handleClick.bind(this,2)}>阅读历史记录</li>
			</ul>
			{this.renderRecordStatement(this.state.index)}
		</div>
		)		
	}
}