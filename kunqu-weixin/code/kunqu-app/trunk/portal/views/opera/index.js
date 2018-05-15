import React from 'react';
import ReactDOM from 'react-dom';
import OperaList from './list.js'
import {
	Link,
	hashHistory
} from "react-router";
import {get,post} from "util/request.js";

var sto;
var sto2;
export default class OperaModule extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			specialList:[],
			index:1,
			isFinished:false,
			totalPage:1
		}
	}
	
	initData = () => {
		get('kunqu/special/queryAll?pageNum=1&pageSize=15')
			.then((data) => {
				if(data.meta.success){
					let newData = data.data.list;
					this.setState({
						specialList:newData,
						index:1,
						isFinished: false
					})
				}
			})		
	}
	moreData = (index) => {
		console.log(index)
		var source = '/kunqu/special/queryAll?pageSize=15&pageNum='+index;
		var newData = [];
		get(source)
			.then((data) => {
				if(data.meta.success){
					newData = this.state.specialList.concat(data.data.list);
					this.setState({
						specialList:newData,
						index:index
					})
				}
			})		
	}
	handleScrollToTop = (completed) => {
		sto2=setTimeout(function() {
			this.initData()
			completed()
		}.bind(this), 1500)
	}
	handleScrollToBottom = (completed) => {
		sto=setTimeout(function() {
			var curIndex = this.state.index + 1;
			if(curIndex <= this.state.totalPage){					  
				this.moreData(curIndex);
			}else{
				this.setState({
					isFinished : true
				}) 
			}
			completed()
		}.bind(this), 1500)
	}
	componentWillUnmount(){
		 clearTimeout(sto);
		 clearTimeout(sto2);
	}
	childChange = () => {
		this.setState({
			isFinished : false
		})
	}
	renderFinishedStatement = () => {
		if(this.state.isFinished){
			return <div className="end-line">~~~已经到底啦~~~</div>
		}else{
			return ''
		}
	}	
	componentWillMount(){
		document.title = '曲典-时代昆曲';
		get('kunqu/special/queryAll?pageNum=1&pageSize=15')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						specialList:data.data.list,
						totalPage:Math.ceil(data.data.total/15)
					})
				}
			})
	}
	render(){
		return(
		<div>
			<OperaList specialList={this.state.specialList} onScrollToTop={this.handleScrollToTop}  onScrollToBottom={this.handleScrollToBottom} totalPage={this.props.totalPage} isFinished={this.state.isFinished}/>
			{this.renderFinishedStatement()}
		</div>
		)
	}	
}