import React from 'react';
import ReactDOM from 'react-dom';
		
export default class DramaItem extends React.Component{
	render(){
		return (
			<li className="drama-item">
				<div className="image-cont" style={{background:`url(${this.props.img})`,backgroundSize: 'auto 100%'}}>
				</div>				
				<div className="drama-title oneline">{this.props.text}</div>
				<div className="drama-desc oneline">{this.props.dramaDesc}</div>
			</li>
		)
	}
}
