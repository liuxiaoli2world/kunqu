import React from 'react';
import ReactDOM from 'react-dom';
		
export default class OperaItem extends React.Component{
	render(){
		return (
			<li className="opera-item">
				<div className="image-cont">
					<img src={this.props.img} alt=""/>
					<img src={require('assets/images/play-icon.png')} className="play-icon"/>
				</div>
				<div className="opera-title oneline">{this.props.text}</div>
			</li>
		)
	}
}