import React from 'react';
import ReactDOM from 'react-dom';
import ReadList from './list.js'
import {get,post} from "util/request.js";
import './detail.css'

export default class ReadDetail extends React.Component{
	constructor(props){
		super(props);
		console.log(this.props.params.id)
		this.state = {
			title:"",
			date:"",
			author:"",
			cont:""		
		}
	}
	componentDidMount(){
		let id = this.props.params.id;
		get(`kunqu/article/query/${id}`)
			.then((data) => {
				if(data.meta.success){
					this.setStorage();
					this.setState({
						title:data.data[0].title,
						date:data.data[0].releaseDate,
						author:data.data[0].author,
						cont:data.data[0].content
					})
				}
			})
	}
	setStorage() {
        const id = this.props.params.id;
        if(localStorage.article){
            let arr = localStorage.getItem('article');
            let article = [
                id,
                ...arr.split(',')
            ];
            let s = new Set(article);
            article = [...s].slice(0,12).join(',');
            localStorage.setItem('article',article)
        }else{
            localStorage.setItem('article',id);
        }
    }
	render(){
		return(
		<div className="detail-container">
			<div className="detail-title">{this.state.title}</div>
			<div className="date-author">
				<div className="date">
					{this.state.date}
				</div>
				<div className="author">
					作者：{this.state.author}
				</div>
				<div className="clear"></div>
				<div className="detail-cont">
					{this.state.cont}
				</div>
			</div>
			
		</div>
		)
	}	
}