import React from "react";
import "../reset.css";
import "./index.scss";
import {Link,hashHistory} from "react-router";

export default class TitleBar extends React.Component{
	constructor(props){
		super(props);
	}

	render() {
		return (
			<div className="titleBox clearfix">
				<div className="lf left">
					<span className="sj"></span>
					<span className="typeName">{this.props.name}</span>
				</div>
				{this.props.link ?
				(<Link className="moreItem right" to={this.props.link}><span>更多 ></span></Link>):null
				}
			</div>
		)
	}
}
