import React from "react";
import ReactDOM from 'react-dom'; 
import config from "config";
import {Link,hashHistory} from "react-router";
import {Carousel,Tabs,Menu} from "antd";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";
import HotList from "./HotList.js"

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

export default class HotCont extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			hotContListData : []			
		}
	}
	componentDidMount(){
		var that = this;
		get("kunqu/drama/query?pageNum=1&pageSize=3&type=1").then((msg) => {
                that.setState({
					hotContListData : msg.data.list
				});
            },
		)
	}	
	render(){
		let index = -1;
		let hotContListData = this.state.hotContListData[0] ? this.state.hotContListData[0].dramaDesc : "";
		hotContListData = hotContListData.length > 200 ? hotContListData.substring(0,200) + '…' : hotContListData;
		if(this.state.hotContListData[0]){
			index = this.state.hotContListData[0].dramaImages.findIndex((item) => {
						return item.imageScene === '03'
					})
		}
		return ( <div className="hot-videos clearfix">
					<div className="imgBox left">	
						<img src={index>=0?this.state.hotContListData[0].dramaImages[index].imageUrl:""} className="hot-pic-big-left" />
					</div>
					<div className="cont-right left">
						<div className="cont-desc">
							<div className="cont-desc-title">
								<span className="bold title-name">
									{this.state.hotContListData[0]? this.state.hotContListData[0].dramaName : ""}
								</span>
								<Link to={this.state.hotContListData[0]?`/drama/detail/${this.state.hotContListData[0].dramaId}`:''}><span className="title-text">
									{this.state.hotContListData[0]? this.state.hotContListData[0].dramaName : ""}“焉有不看之理”
								</span></Link>
							</div>
							<div className="cont-desc-text">
								{hotContListData}
							</div>
						</div>
						<HotList hotContListData={this.state.hotContListData} /> 
					</div>
				</div>
		)
	}
}
