import React from "react";
import {Link,hashHistory} from "react-router";
import {Icon} from "antd";
import "../reset.css";
import "./index.scss";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";
import OperaList from "./Opera";
import TitleBar from "./TitleBar";


//大典
class Topic extends React.Component{
	constructor(props){
		super(props);
		this.state={
			topicList:[
				{
					image: require('assets/images/topic1_cover.png'),
					name: '西厢记',
					id: 1
				},
				{
					image: require('assets/images/topic2_cover.png'),
					name: '还魂记',
					id: 2
				},
				{
					image: require('assets/images/topic3_cover.png'),
					name: '牡丹亭',
					id: 3
				},
				{
					image: require('assets/images/topic4_cover.png'),
					name: '琵琶记',
					id: 4
				}
			]
		}
	}
	componentDidMount() {
		
	}
	render() {
		const topicList = this.state.topicList;
		return(
		<div className="topicList">
		{topicList.map((item,i)=>
			(<div className='topic_item' key={i}>
				<Link to={`topic/${item.id}`}>
                <img src={item.image}/>
                <div className="info">
					<p>{item.name}</p>
                </div>
                </Link>
            </div>)
		)}
		</div>)
	}
}
//剧典
class Drama extends React.Component{
	constructor(props){
		super(props);
		this.state={
			dramaList:[]
		}
	}
	componentDidMount() {
		get('kunqu/drama/query?pageNum=1&pageSize=7&type=3')
			.then((data) => {
				data && data.meta && data.meta.success &&
				this.setState({
					dramaList:data.data.list
				})
				
			})
	}
	render() {
		let topI = -1;
		const dramaList = this.state.dramaList;
		if(dramaList[0]) {
			topI = dramaList[0].dramaImages.findIndex((val) => {
				return val.imageScene == '01'
			})
		}
		return(
			<div className="dramaList">
				{(dramaList[0]) &&
				(<div className='drama_top'>
                    <Link to={`drama/detail/${dramaList[0].dramaId}`}>
                    <img src={topI>=0?dramaList[0].dramaImages[topI].imageUrl:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493972302&di=6eb3685733b97cbffdde851553270e0a&imgtype=jpg&er=1&src=http%3A%2F%2Flbimg.tvmao.com%2Fstillcut%2FKpwmKBKnLB%3DmVnSoKDwnL7%3D.jpg'}/>
                    <div className="info">
                    	<Icon className="playicon" type="play-circle-o" />
						<p className="dramaName">{dramaList[0].dramaName}</p>
						<p className="dramaOther">{dramaList[0].dramaDesc}</p>
                    </div>
                    </Link>
                </div>)
            	}
            	<div className='drama_rt'>
					{dramaList.map((item,i)=>{
						if(i>0){
							const imgI = item.dramaImages.findIndex((val) => {
								return val.imageScene == '01'
							})
							return (<div className='drama_item' key={i}>
							<Link to={`drama/detail/${item.dramaId}`}>
							<img src={imgI>=0?item.dramaImages[imgI].imageUrl:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493972302&di=6eb3685733b97cbffdde851553270e0a&imgtype=jpg&er=1&src=http%3A%2F%2Flbimg.tvmao.com%2Fstillcut%2FKpwmKBKnLB%3DmVnSoKDwnL7%3D.jpg'}/>
							<div className="info">
								<p className="dramaName">{item.dramaName}</p>
								<p className="dramaOther">播放量：{item.palyAmount}</p>
							</div>
							</Link>
						</div>)
						} 
							
					})}
				</div>
			</div>
		)
	}
}
//曲典
class Opera extends React.Component{
	constructor(props){
		super(props);
		this.state={
			operaList:[]
		}
	}
	componentDidMount() {
		get('kunqu/special/queryAll?pageNum=1&pageSize=4')
			.then((data) => {
				data && data.meta && data.meta.success &&
				this.setState({
					operaList:data.data.list
				})
			})
	}
	render() {
		const operaList = this.state.operaList;
		return(
		<div className="operaList">
		{operaList.map((item,i)=>
			(<OperaList info={item} key={i} />)
		)}
		</div>)
	}
}
//推荐阅读
class Article extends React.Component{
	constructor(props){
		super(props);
		this.state={
			articleList:[]
		}
	}
	componentDidMount() {
		post('kunqu/article/selectByStyle?pageNum=1&pageSize=4&type=1')
			.then((data) => {
				data && data.meta && data.meta.success &&
				this.setState({
					articleList:data.data.list
				})
			})
	}
	render() {
		const articleList = this.state.articleList;
		return (
			<div className="articleList">
				{articleList.map((item,i)=>{
					const index = item.articleImages.findIndex((value, index, arr) =>   
										(value.imageScene == '01')  
									)
					return (<div className='article_item' key={i}>
						<Link to={`article/detail/${item.articleId}`}>
                		<p className='article_name'>{item.title}</p>
               			<div className='article_content'>
                			<img src={(index>-1) ? item.articleImages[index].imageUrl:''}/>
                			<div className='article_intro'>{item.content&&item.content.length<80?item.content:item.content&&item.content.substr(0,70)+'...'}</div>
                		</div>
                		</Link>
            		</div>)

				}
					
				)}
			</div>
		)
	}
}
//分类
class Classify extends React.Component{
	constructor(props){
		super(props);
		this.state = {
            types: [],
            authors: [],
            times: [],
            roles: []
        };
	}
	componentDidMount(){
        this.getTypes();
        this.getAuthors();
        this.getTimes();
        this.getRoles();
	}
	/**
     * 获取剧目
     */
    getTypes() {
        let self = this;
        get("kunqu/repertoiretag/queryAll")
            .then((data) => {
                if (data && data.meta && data.meta.success) {
                    let list = data.data || [];
                    this.setState({
                        types: list
                    });
                } else {

                }
            });

    }
    /**
     * 获取作者
     */
    getAuthors() {
        let self = this;
        get("kunqu/authortag/queryAll")
            .then((data) => {
                if (data && data.meta && data.meta.success) {
                    let list = data.data || [];
                    this.setState({
                        authors: list
                    });
                } else {

                }
            });

    }
    /**
     * 获取年代
     */
    getTimes() {
        let self = this;
        get("kunqu/agetag/queryAll")
            .then((data) => {
                if (data && data.meta && data.meta.success) {
                    let list = data.data || [];
                    this.setState({
                        times: list
                    });
                } else {

                }
            });

    }
    /**
     * 获取角色
     */
    getRoles() {
        let self = this;
        get("kunqu/roletag/queryAll")
            .then((data) => {
                if (data && data.meta && data.meta.success) {
                    let list = data.data || [];
                    this.setState({
                        roles: list
                    });
                } else {

                }
            });
    }

	render() {
		let types = [];
		for(let key in this.state){
			let num,title;
			switch(key) {
            // 剧目
            case 'types':
                title = '剧目';
                num = 1;
                break;
            // 作者
            case 'authors':
                title = '作者';
                num = 2;
                break;
            // 年代
            case 'times':
                title = '年代';
                num = 3;
                break;
            // 角色
            case 'roles':
                title = '角色';
                num = 4;
                break;
            default:
                break;
        	
        	}
			let type = (
				<div>
					<p className='title'>{title}</p>
					{this.state[key].map((item, i) => 
						(<Link to={`/classify/${num}/${item.repertoireTagId||item.authorTagId||item.ageTagId||item.roleTagId}`} key={i}><span className="tag" >{item.tagName}</span></Link>)
					)}
				</div>
			)
			types.push(type);
		}
		return (
			<div className="typesList">
				{types.map((item,i) => 
					(<div className="types_item" key={i}>
						{item}
					</div>)
				)}
			</div>
		)
	}
}

export default class Home extends React.Component{
	constructor(props){
		super(props);
	}

	componentDidMount(){
		window.scrollTo(0,0);
		$(window).trigger("resize");
	}

	componentDidUpdate(){
		window.dispatchEvent(resizeEvent);
	}

	render(){
		let path = hashHistory.getCurrentLocation().pathname;

		return(
			<div className="home-wrapper">
				<div className="banner">
					<img src={require('assets/images/home_banner.png')} />
				</div>
				<div>
					<TitleBar name="大典专题" />
					<div className="container"><Topic /></div>
				</div>
				<div>
					<TitleBar name="剧典推荐" link='/classify/0/drama' />
					<div className="container"><Drama /></div>
				</div>
				<div>
					<TitleBar name="曲典推荐" link='/classify/0/special' />
					<div className="container"><Opera /></div>
				</div>
				<div>
					<TitleBar name="推荐阅读" link='/classify/0/article' />
					<div className="container"><Article /></div>
				</div>
				<div>
					<TitleBar name="分类索引" link='/classify/0/drama' />
					<div className="container"><Classify /></div>
				</div>
			</div>
		)
	}
}