import React from "react";
import ReactDOM from 'react-dom'; 
import config from "config";
import {Link,hashHistory} from "react-router";
import {Carousel,Tabs,Menu} from "antd";
import "../reset.css";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

class HotList extends React.Component{
	constructor(props){
		super(props);
	}
	render(){		
		var hotContListData = this.props.hotContListData ? this.props.hotContListData : [];
		const style1 = {borderBottom: '1px solid #f4f4f4'};
		const style2 = {borderTop: 'none'};
 		var hotContList	= hotContListData.map(function(item,index){
			if(index >= 1){
				const i = item.dramaImages.findIndex((item) => {
					return item.imageScene == '01';
				})
				return (
					<Link to={`/drama/detail/${item.dramaId}`} key={index} >
						<div style={index==1?style1:style2} className="hot-item overflow-hidden" >
							<img src={i>=0 ? item.dramaImages[0].imageUrl : ""} className="hot-pic-left fl"/>
							<div className="hot-right fl">
								<div className="hot-title">
									<h1 className="bold">《{item.dramaName}》</h1>
									<div className="hot-cont">							
										{item.dramaDesc.length > 110? item.dramaDesc.substring(0,120)+"…" : item.dramaDesc}
									</div>
								</div>
							</div>
						</div>
					</Link>
				)
			}
		})
		return (
			<div className="hot-cont">
				{hotContList}
			</div>
		)
	}		
};

export default HotList;
