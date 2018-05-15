import React from "react";
import {
    hashHistory,
    Link
} from "react-router";
import {
    Row,
    Col
} from 'antd';
import {get,
    post
} from '../util/request.js';
import "./RankListModule.scss";

/**
 * 分类-左边标签项
 */
export default class RankListModule extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            contentList: []
        };
    }

    componentWillMount() {
    }

    /**
     * 页面首次渲染完毕请求后台数据，5个请求
     */
    componentDidMount() {
        this.getContentList();
    }    

    /**
     * 获取剧目
     */
    getContentList() {
        let self = this;
        post("kunqu/article/selectByStyle?pageNum=1&pageSize=10&type=3")
            .then((data) => {
                if (data.meta.success) {
                    let list = data.data.list || [];  
                    let resultList = self.mapToDisplayProp(list);
                    self.setState({
                        contentList: resultList
                    });
                } else {

                }
            });
    }

        /**
     * 字段映射
     */
    mapToDisplayProp(list) {
        let resultList = [];
        for (let i = list.length - 1; i >= 0; i--) {
            let item0 = list[i];
            let item1 = {};
            if(i === list.length-1) {
                item1.display = "block";
            }else{
                item1.display = "none";
            }
            item1.id = item0.articleId;
            item1.title = item0.title;
            item1.sumReadCount = item0.sumReadCount;
            item1.monthCount = item0.monthCount;
            item1.weekCount = item0.weekCount;
            item1.image = this.getImageUrl(item0.articleImages);
            item1.content = item0.content;
            resultList.push(item1);
        }
        return resultList;
    }

    /**
     * 剧典中有多个图片，用imageScene字段区分，01表示小图，02表示大图
     */
    getImageUrl(imagesList) {
        let image;
        if (!imagesList) {
            image = '';
        } else {
            for (var i = imagesList.length - 1; i >= 0; i--) {
                let imageItem = imagesList[i];
                if (imageItem.imageScene === '01') {
                    image = imageItem.imageUrl;
                    break;
                }
            }
            if (!image) {
                image = '';
            }
        }
        return image;
    }

    /**
     * 标签点击事件
     */
    handleClick(articleId, event) {
        let newList = [];
        for(let i=0;i<this.state.contentList.length;i++) {
            let item0 = this.state.contentList[i];
            let item = item0;
            if(item0.id === articleId) {
                item.display = 'block';
            }else{
                item.display = 'none';
            }
            newList.push(item);
        }
        this.setState({
            contentList: newList
        });
    }  

    render() {
        return (
            <div className="rankContent" >                                
                <div style={{position:"relative"}}>
                    <span className="titleLabel"></span>
                    <span className="moduleTitle">阅读排行</span>
                    <span className="rank">月排行</span>
                    <span className="rank">周排行</span>
                </div>
                <div className="topLine"></div>
                <Row gutter={6} >
                    {
                        this.state.contentList && this.state.contentList.map((item,index)=>{    
                            let indexColor;
                            let titleColor;
                            switch(index){
                                case 0:
                                    indexColor = '#e60012';
                                    titleColor = '#e60012';
                                    break;
                                case 1:
                                    indexColor = '#ff3300';
                                    titleColor = '#ff3300';
                                    break;
                                case 2:
                                    indexColor = '#ff6633';
                                    titleColor = '#a2a2a2';
                                    break;
                                default:
                                    indexColor = '#a2a2a2';
                                    titleColor = '#a2a2a2';
                            }
                            return (
                                  <Col key={index} className="gutter-row" span={24} >
                                    <div className="gutter-box rankItem" onClick={this.handleClick.bind(this,item.id)}>
                                        <div className="titleLine">
                                            <span className="index" style={{background:indexColor}}>{index+1}</span><span className="title" style={{color:titleColor}}>{item.title}</span><span className="rank">{item.monthCount}</span><span className="rank">{item.weekCount}</span>
                                        </div>
                                        <div className="line"></div>
                                        <div className="realContent" style={{display:item.display}}>
                                            <img src={item.image}/>
                                            <div className="innerContent">
                                                <Link to={`article/detail/${item.id}`}><div className="contentDesc" >{item.content&&item.content.length<44?item.content:item.content&&item.content.substr(0,40)+'...'}</div></Link>
                                            </div>
                                        </div>
                                    </div>
                                  </Col>  
                              ) 
                        })
                    }
                </Row>
            </div>
        )
    }
}