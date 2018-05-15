import React from 'react';
import ReactDOM from 'react-dom';
		
export default class ReadItem extends React.Component{
	render(){
		return (
			<li className="read-item clearfix">
				<div className="read-cont left" style={{background:`url(${this.props.img})`,backgroundSize: '100% auto'}}>
				</div>	
				<div className="right-cont left">
					<div className="read-title oneline">{this.props.text}</div>
					<div className="read-desc">{this.props.desc}</div>					
				</div>
			</li>
		)
	}
}