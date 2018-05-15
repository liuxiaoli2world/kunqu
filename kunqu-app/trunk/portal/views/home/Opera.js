import React from "react";
import {Icon} from "antd";
import {Link,hashHistory} from "react-router";
import "../reset.css";
import "./index.scss";
//曲典
export default class OperaList extends React.Component{
	constructor(props){
		super(props);
	}
	componentDidMount() {
	}
	render() {
		const {info} = this.props;
		const index = info.imageList ? (info.imageList.findIndex((value, index, arr) =>   
						(value.imageScene == '01')  
					)):-1;
		return (
			<div className='opera_item'>
                <p className="special_name">{info.specialName}</p>
                <div className="cover">
                	<img src={(index>-1)?(info.imageList[index].imageUrl):''}/>
                	<p className="info">播放：{info.palyAmount||0}次&nbsp;&nbsp;</p>
                </div>
                <ul className="detail">
					{info.operaList && info.operaList.map((opera,i)=>
						(<Link to={{pathname:`/opera/detail/${info.specialId}`,state:i}} key={i}>
							<li className={i>2 ? 'upthree' : ''}>
							<div>
								<span className="num">{`0${++i}`}</span>
								<span>{opera.operaName}</span>
							</div>
							<Icon className="playbutton" type="caret-right" />
						</li>
						</Link>)
					)}
                </ul>
                <Link to={{pathname:`/opera/detail/${info.specialId}`}}><p className="play">播放专辑</p></Link>
            </div>)
		
	}
}
