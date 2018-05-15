import React from 'react';
import ReactDOM from 'react-dom';
import DramaList from './list.js';
import {get,post} from "util/request.js";

export default class DramaModule extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			dramaList:[],
			index:1,
			isFinished:false,
			totalPage:1
		}
	}
	
	initData = () => {
		get('kunqu/drama/query?pageSize=10&type=0&pageNum=1')
			.then((data) => {
				if(data.meta.success){
					let newData = data.data.list;
					this.setState({
						dramaList:newData,
						index:1,
						isFinished: false
					})
				}
			})		
	}
	moreData = (index) => {
		console.log(index)
		var source = 'kunqu/drama/query?pageSize=10&type=0&pageNum='+index;
		var newData = [];
		get(source)
			.then((data) => {
				if(data.meta.success){
					newData = this.state.dramaList.concat(data.data.list);
					this.setState({
						dramaList:newData,
						index:index
					})
				}
			})		
	}
	handleScrollToTop = (completed) => {
		setTimeout(function() {
			this.initData()
			completed()
		}.bind(this), 1500)
	}
	handleScrollToBottom = (completed) => {
				// load more
				console.log("load more")
		setTimeout(function() {
			var curIndex = this.state.index + 1;
			console.log(curIndex,this.state.totalPage)
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
		document.title = '剧典-时代昆曲';
		get('kunqu/drama/query?pageNum=1&pageSize=10&type=0')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						dramaList:data.data.list,
						totalPage:Math.ceil(data.data.total/10)
					})
				}
			})
	}
	
	render(){
		return(
		<div>
			<DramaList dramaList={this.state.dramaList} onScrollToTop={this.handleScrollToTop}  onScrollToBottom={this.handleScrollToBottom} totalPage={this.props.totalPage} isFinished={this.state.isFinished}/>
			{this.renderFinishedStatement()}
		</div>
		)
	}	
}