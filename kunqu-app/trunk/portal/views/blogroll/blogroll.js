import React from "react";
import config from "config";
import {Link} from "react-router";
import Header from "../header/header.js";
import {get,post} from "../util/request";
import "./blogroll.scss";

class Blogroll extends React.Component{
	constructor(props){
		super(props);
		this.state={
			links: []
		}
	}
	componentDidMount() {
		get(`site/pressfriendlylink/selectAllPressFriendlyLink`)
			.then((data) => {
				if(data.meta.success){
					this.setState({
						links:data.data
					})
				}
			})
	}
	render(){
		const links = this.state.links;
		const len = links.length-1;
		return (
			<div>
				<Header crumbPos={[{pos:"友情链接",src:"blogroll"}]}/>
				<div id="blogroll_main">
					<div className="content">
						<fieldset>
							<legend>友情链接</legend>
							<div className="links">
							{
			 				links.map((link,i)=>{
			 					return (

			 						<span key={i}><span><a href={link.url} title={link.name}>{link.name}</a></span><span className="line">{i!==len?'|':''}</span></span>
			 					)
			 				})
			 				}
			 				</div>
						</fieldset>
					</div>
				</div>
			</div>
		)
	}
}

export default Blogroll;
