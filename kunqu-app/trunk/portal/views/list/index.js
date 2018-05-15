import React from "react";
import {Link,hashHistory} from "react-router";
import {Menu, Icon, Row, Col, Pagination} from "antd";
import "../reset.css";
import "./index.scss";
import {get,post} from "util/request.js";
import {SubEmiter,Emiter} from 'util';
import TitleBar from "../home/TitleBar";

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

class Sider extends React.Component {
	constructor(props){
		super(props);
		this.state={
			result:null,
			type:null,
			openKeys: []
		}
	}
	componentWillReceiveProps(nextprops) {
		const allList = nextprops.allList;
		if(nextprops.currentId){
			const info = {
				contentType : 'thesis',
				dirId : nextprops.currentId,
				openKeys: [nextprops.parentId]
			}
			this.getData(info,1)
		}else{
			this.getData(allList[0],1)
		}
	}
	handleClick = (e) => {
    	const {info} = e.item.props;
    	this.getData(info,1);
  	}
  	onOpenChange = (openKeys) => {
  		const state = this.state;
		const latestOpenKey = openKeys.find(key => !(state.openKeys.indexOf(key) > -1));
   		const latestCloseKey = state.openKeys.find(key => !(openKeys.indexOf(key) > -1));  	
		let nextOpenKeys = [];
		if (latestOpenKey) {
      		nextOpenKeys = this.getAncestorKeys(latestOpenKey).concat(latestOpenKey);
    	}
    	if (latestCloseKey) {
      		nextOpenKeys = this.getAncestorKeys(latestCloseKey);
    	}
    	this.setState({ openKeys: nextOpenKeys });
   	}
	
	getAncestorKeys = (key) => {
   		const map = {	
   		};
    	return map[key] || [];
  	}

  	pageChange = (params) => {
  		this.getData(this.state.info,params.pageNum);
  	}

  	getData(info,pageNum) {
  		if(info && info.contentType){
  			post(`kunqu/dir/queryContent?type=${info.contentType}&dirId=${info.dirId}&pageNum=${pageNum}&pageSize=12`)
    			.then((data) => {
    				window.scrollTo(0,0);
    				if(data && data.data && data.meta.success){
    					const result = data.data;
    					this.setState({
							result,
							type:info.contentType,
							info:info,
							selectId:info.dirId
    					})
    				}else{
    					this.setState({
    						result:null,
    						type:info.contentType,
							info:info,
							selectId:info.dirId
    					})
    				}
    				
    			})
    	}
  	}
	render() {
		const {allList} = this.props;
		const result = this.state.result;
			{if(allList.length>0) {
				return (<div className="showContent clearfix">
							<SubEmiter eventName="pageChange" listener= {this.pageChange}></SubEmiter>
							<Menu mode="inline"
								style={{ width: 233,float:'left',padding:'20px 0 100px 0',marginBottom:'200px',flex:'0 0 233px',letterSpacing:'2px'}}
								onClick={this.handleClick} 
								selectedKeys={[this.state.selectId+'']}
        						openKeys={this.state.openKeys}
								onOpenChange={this.onOpenChange}
        						>
								{allList.map((item,i) => {
									if(!item.children) {
										return <Menu.Item key={item.dirId} style={{fontSize:'14px'}} info={item}><span className="iblock"></span><span>{item.name}</span></Menu.Item>
									}else {
										return (
											<SubMenu key={item.dirId} title={<span><span className="iblock"></span>{item.name}</span>} >
											{item.children.map((list,j) => (
												<Menu.Item key={list.dirId} info={list}>{list.name}</Menu.Item>
											))}
											</SubMenu>
										)
									}
								})}
							</Menu>
							<ShowList data={result} type={this.state.type}/>
						</div>)
				}else{
					return (<div style={{textAlign:'center'}}><img style={{margin:'8% 0'}} src={require('assets/images/nodata.png')} /></div>)
				}
			}
	}
}

class ShowList extends React.Component{
	constructor(props){
		super(props);
	}

	pageChange(pageNum) {
		window.scrollTo(0,0);
        Emiter.emit("pageChange", {pageNum:pageNum});
    }

	render() {
		if((!this.props.data)||(this.props.data.list && (this.props.data.list.length==0))) {
			return (<div style={{textAlign:'center',flex:'1'}}><img style={{margin:'8% 0'}} src={require('assets/images/nodata.png')} /></div>)
		}else{
			const {data} = this.props;
			if(this.props.type=='overview'){
				return (
					<div className="txtContent left">
						<div className="header">
							<p className="title">{data.title}</p>
							<p className="info">
								<span>作者：{data.author}</span>
								<span>来源：{data.source}</span>
								<span><Icon type="clock-circle-o" style={{margin:'0 5px 0 10px'}}/>{data.releaseDate?data.releaseDate.substring(5,16):''}</span>
							</p>
						</div>
						<div className="details">{data.content}</div>
					</div>
				)
			}else if(this.props.type=='thesis'){
				return (
					<div>
					<Row gutter={6} className="conContent" style={{marginLeft:'0'}}>
                    {
                        data.list.map((item,index)=>(
                          <Col key={index} className="gutter-row" span={8} >
                            <div className="gutter-box contentItem" >
                            	<Link to={`${(item.compositeType=='drama') ? 'drama': (item.compositeType=='special') ? 'opera' : 'article'}/detail/${item.compositeId}`} target="_blank">
                                <div className="title">{item.compositeName}</div>
                                <div className="line"></div>
                                <div className="realContent" >
                                    <img src={item.compositeImage}/>
                                    <div className="innerContent">
                                        <div className="contentDesc" >{item.compositeDesc&&item.compositeDesc.length<54?item.compositeDesc:item.compositeDesc&&item.compositeDesc.substr(0,50)+'...'}</div>
                                    </div>
                                </div>
                                </Link>
                            </div>
                          </Col>
                        ))
                    }
               		</Row>
					<Pagination showQuickJumper defaultCurrent={1} pageSize={data.pageSize} total={data.total} onChange={this.pageChange.bind(this)} style={{padding:"20px 30px 28px 0",marginLeft:'35px'}} />
					</div>
				)
			}
		}
	}
}

export default class List extends React.Component{
	constructor(props){
		super(props);
		this.state={
			allList:[],
			title:'',
			parentId:''
		}
	}

	componentDidMount(){
		window.scrollTo(0,0);
		if(this.props.params.dirId){
			get(`kunqu/dir/queryParentList/${this.props.params.dirId}`)
				.then((res) => {
					const data = res.data;
					this.getData(res.data[0]);
					this.setState({
						parentId: res.data[1].dirId
					})
				})
		}else{
			this.getData(this.props.location.state);
		}
	}
	
	componentWillReceiveProps(nextprops) {
		if(nextprops.location.state != this.props.location.state){
			this.getData(nextprops.location.state);
			this.setState({
				parentId: ''
			})
		}
	}

	getData(state) {
		const id = state.dirId;
		get(`kunqu/dir/queryChildList/${id}`)
			.then((data) => {
				if(data.meta.success){
					let temp={};
					data.data.map((item,i) => {
						if(!temp[item.parentId]){
							temp[item.parentId]=[];
						}
						temp[item.parentId].push(item);
					})
					for(let key in temp){
						if(key != id){
							const index  = (temp[id].findIndex(function(value, index, arr) {  
								return value.dirId == key;  
							}))
							temp[id][index].children = temp[key];
						}
					}
					this.setState({
						allList:temp[id]||[],
						title:state.name
					})
				}
			})
	}

	render(){
		return(
			<div className="wrapper">
				<TitleBar name={this.state.title} style={{padding: '10px 0 25px 0'}}/>
				<Sider allList={this.state.allList} currentId={this.props.params.dirId?this.props.params.dirId:''} parentId={this.state.parentId}/>
			</div>
		)
	}
}