import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Card } from 'antd';

class HomeCard extends Component {
    render() {
        return (
            <Card className="homeCard">
				<div className="left-content">
					<span>{this.props.title}</span>
					<br />
					<span style={{ fontSize: 30 }}>{this.props.count}</span>
				</div>
				<div className="right-content">
					<img src={this.props.imgUrl} />
					<p style={{ fontSize: 12 }}>{this.props.desc}</p>
				</div>
			</Card>
        );
    }
}

HomeCard.propTypes = {
    title: PropTypes.string,
    count: PropTypes.number,
    desc: PropTypes.string
};

export default HomeCard;