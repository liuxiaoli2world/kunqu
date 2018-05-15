import React from 'react';
import ReactDOM from 'react-dom';
import ReadList from './list.js'
import {get,post} from "util/request.js";

var sto;
var sto2;
export default class ReadModule extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			readList:[],
			index:1,
			isFinished:false,
			totalPage:1
		}
	}
	initData = () => {
		post('/kunqu/article/selectByStyle?pageSize=6&type=2&pageNum=1')
			.then((data) => {
				if(data.meta.success){
					let newData = data.data.list;
					this.setState({
						readList:newData,
						index:1,
						isFinished: false
					})
				}
			})		
	}
	moreData = (index) => {
		var source = '/kunqu/article/selectByStyle?pageSize=6&type=2&pageNum='+index;
		var newData = [];
		post(source)
			.then((data) => {
				if(data.meta.success){
					newData = this.state.readList.concat(data.data.list);
					this.setState({
						readList:newData,
						index:index
					})
				}
			})		
	}
	handleScrollToTop = (completed) => {
		sto=setTimeout(function() {
			this.initData()
			completed()
		}.bind(this), 1500)
	}
	handleScrollToBottom = (completed) => {
				// load more
		sto2=setTimeout(function() {
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
		document.title = '阅读-时代昆曲';
		post('/kunqu/article/selectByStyle?pageNum=1&pageSize=6&type=2')
			.then((data) => {
				if(data.meta.success){
					this.setState({
						readList:data.data.list,
						totalPage:data.data.pages
					})
				}
			})
	}	
	render(){
		return(
		<div>
			<ReadList readList={this.state.readList} onScrollToTop={this.handleScrollToTop}  onScrollToBottom={this.handleScrollToBottom} totalPage={this.props.totalPage} isFinished={this.state.isFinished}/>
			{this.renderFinishedStatement()}
		</div>
		)
	}	
}