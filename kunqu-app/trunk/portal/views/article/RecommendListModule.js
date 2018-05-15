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
import "./RecommendListModule.scss";

/**
 * 分类-左边标签项
 */
export default class RecommendListModule extends React.Component {
    
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
        post("kunqu/article/selectByStyle?pageNum=1&pageSize=6&type=4")
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
            item1.id = item0.articleId;
            item1.title = item0.title;
            item1.sumReadCount = item0.sumReadCount;
            item1.monthCount = item0.monthCount;
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
                if (imageItem.imageScene === '02') {
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
    handleClick(type, value, event) {
    }    

    render() {
        return (
            <div className="recommendContent" >
                <div style={{position:"relative"}}>
                    <span className="titleLabel"></span>
                    <span className="moduleTitle">推荐阅读</span>
                </div>
                <div className="topLine"></div>
                <Row gutter={6} >
                    {
                        this.state.contentList && this.state.contentList.map((item,index)=>(                         
                          <Col key={index} className="gutter-row" span={24} >
                            <div className="gutter-box contentItem" >
                                <img src={item.image}/>
                                <div className="innerContent">
                                    <Link to={`article/detail/${item.id}`}><div className="title">{item.title}</div></Link>
                                    <div className="label">人气：<span className="count">{item.sumReadCount}</span></div>
                                </div>
                            </div>
                          </Col>                          
                        ))
                    }
                </Row>
            </div>
        )
    }
}