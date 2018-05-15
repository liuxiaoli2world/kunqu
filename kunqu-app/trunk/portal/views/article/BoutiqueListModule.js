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
import "./BoutiqueListModule.scss";

/**
 * 分类-左边标签项
 */
export default class BoutiqueListModule extends React.Component {
    
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
        post("kunqu/article/selectByStyle?pageNum=1&pageSize=7&type=2")
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
            item1.releaseDate = item0.releaseDate;
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
            <div className="boutiqueContent" >                
                <div style={{position:"relative"}}>
                    <span className="titleLabel"></span>
                    <span className="moduleTitle">精品阅读</span>
                    <Link to='/classify/0/article'><span className="more">更多></span></Link>
                </div>
                <div className="topLine"></div>
                <Row gutter={6} >
                    {
                        this.state.contentList && this.state.contentList.map((item,index)=>(                         
                          <Col key={index} className="gutter-row" span={24} >
                            <div className="gutter-box boutiqueItem" >
                                <img src={item.image}/>
                                <div className="innerContent">
                                    <Link to={`article/detail/${item.id}`}><div className="title">文：{item.title}</div></Link>
                                    <div className="contentDesc" >{item.content&&item.content.length<100?item.content:item.content&&item.content.substr(0,90)+'...'}
                                        <Link to={`article/detail/${item.id}`}><span className="showDetail">《查看全文》</span></Link>
                                    </div>
                                    <div className="label"><span className="labelItem">发布：{item.releaseDate}</span><span className="separator">|</span><span className="labelItem">总阅读：{item.sumReadCount}</span><span className="separator">|</span><span className="labelItem">30天阅读：{item.monthCount}</span></div>
                                </div>
                            </div>
                            <div className="line"></div>
                          </Col>                          
                        ))
                    }
                </Row>
            </div>
        )
    }
}