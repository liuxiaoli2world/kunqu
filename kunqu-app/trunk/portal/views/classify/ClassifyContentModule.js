import React from "react";
import {
    hashHistory,
    Link
} from "react-router";
import {
    Row,
    Col,
    Pagination
} from 'antd';
import {get,
    post
} from '../util/request.js';
import {SubEmiter,Emiter} from 'util';
import "./index.scss";

/**
 * 分类-右边资源列表
 */
export default class ClassifyContentModule extends React.Component {
    
    constructor(props) {
        super(props);
        this.defaultPageNumber = 9;
        this.state = {
            // 分类 默认选剧典
            category: 'drama',
            // 剧目 默认选全部
            type: '-1',
            // 作者 默认选全部
            author: '-1',
            // 年代 默认选全部
            time: '-1',
            // 角色 默认选全部
            role: '-1',
            contentList: [],
            current: 1,
            pageSize: 9,
            total: 0,
            hasListData: false
        }
    }

    componentWillMount() {
        this.state.keyword = this.props.keyword;
        this.setTagState(this.props.typeParam, this.props.valueParam)
    }

    componentDidMount() {
        this.getContent();
    }

    setTagState(type, value) {
        switch (type+'') {
            case '1':
                this.state.type = value;
                break;
            case '2':
                this.state.author = value;
                break;
            case '3':
                this.state.time = value;
                break;
            case '4':
                this.state.role = value;
                break;
            case '1':
            default:
                this.state.category = value;
                break;
        }
    }

    /**
     * 生成url
     */
    buildUrl(pageNumber = 1) {
        let baseUrl;
        const keyword = this.state.keyword;
        if(keyword) {
            baseUrl = `kunqu/classify/queryByKeyword?keyword=${keyword}&pageNum=${pageNumber}&pageSize=${this.defaultPageNumber}`;
        }else{
            baseUrl = `kunqu/classify/queryByTag?pageNum=${pageNumber}&pageSize=${this.defaultPageNumber}`;
        }
        let tagFilter = this.generalAllTagFilterStr();
        let url = `${baseUrl}${tagFilter}`;
        return url;
    }

    /**
     * 生成标签过滤条件字符串
     */
    generalAllTagFilterStr() {
        let categoryFilter = this.buildTagFilterStr('0', this.state.category);
        let typeFilter = this.buildTagFilterStr('1', this.state.type);
        let authorFilter = this.buildTagFilterStr('2', this.state.author);
        let timeFilter = this.buildTagFilterStr('3', this.state.time);
        let roleFilter = this.buildTagFilterStr('4', this.state.role);

        let str = `${categoryFilter}${typeFilter}${authorFilter}${timeFilter}${roleFilter}`;
        return str;
    }

    /**
     * 构建过滤条件
     */
    buildTagFilterStr(typeParam, valueParam) {
        let filter;

        // 选择全部时value为-1，此时不加过滤条件
        if (valueParam == '-1') {
            return '';
        }
        switch (typeParam) {
            // 剧目
            case '1':
                filter = `&repertoireTagId=${valueParam}`;
                break;
                // 作者
            case '2':
                filter = `&authorTagId=${valueParam}`;
                break;
                // 年代
            case '3':
                filter = `&ageTagId=${valueParam}`;
                break;
                // 角色
            case '4':
                filter = `&roleTagId=${valueParam}`;
                break;
                // 类别特殊处理
            case '0':
                filter = `&queryType=${valueParam}`;
            default:
                break;
        }
        return filter;
    }

    /**
     * 获取右边内容
     */
    getContent() {
        this.getPageData();
    }

    /**
     * 分页获取数据，默认取第一页
     */
    getPageData(pageNumber = 1) {
        let url = this.buildUrl(pageNumber);
        post(url)
            .then((data) => {
                this.processResult(data);
            });
    };

    /**
     * 处理结果集
     */
    processResult(data) {
        if (data.meta.success) {

            this.setState({
                contentList: this.mapToDisplayProp((data && data.data && data.data.list) || []),
                total: (data && data.data && data.data.total) || 0,
                pageSize: (data && data.data && data.data.pageSize) || this.defaultPageNumber,
                current: (data && data.data && data.data.pageNum) || 1,
                hasListData: (data && data.data && data.data.list && data.data.list.length>0)?true:false
            });
        } else {
            this.setState({
                contentList: [],
                total: 0,
                pageSize: 0,
                current: 1,
                hasListData: false
            });
        }
    };

    /**
     * 字段映射
     */
    mapToDisplayProp(list) {
        let resultList = [];
        switch (this.state.category) {
            // 文章
            case 'article':
                for (let i = list.length - 1; i >= 0; i--) {
                    let item0 = list[i];
                    let item1 = {};
                    item1.type = 'article';
                    item1.id = item0.articleId;
                    item1.title = item0.title;
                    item1.image = this.getImageUrl(item0.articleImages,'01');
                    item1.content = item0.articleDesc;
                    item1.buttonName = '文章';
                    resultList.push(item1);
                }
                break;
                // 曲典
            case 'special':
                for (let i = list.length - 1; i >= 0; i--) {
                    let item0 = list[i];
                    let item1 = {};
                    item1.type = 'special';
                    item1.id = item0.specialId;
                    item1.title = item0.specialName;
                    item1.image = this.getImageUrl(item0.imageList,'04');
                    item1.content = item0.specialDesc;
                    item1.buttonName = '音频';
                    resultList.push(item1);
                }
                break;
                // 剧典
            case 'drama':
            default:
                for (let i = list.length - 1; i >= 0; i--) {
                    let item0 = list[i];
                    let item1 = {};
                    item1.type = 'drama';
                    item1.id = item0.dramaId;
                    item1.title = item0.dramaName;
                    item1.image = this.getImageUrl(item0.dramaImages,'02');
                    item1.content = item0.dramaDesc;
                    item1.buttonName = '视频';
                    resultList.push(item1);
                }

        }
        return resultList;
    }

    /**
     * 剧典中有多个图片，用imageScene字段区分
     */
    getImageUrl(imagesList,imageScene) {
        let image;
        if (!imagesList) {
            image = '';
        } else {
            for (var i = imagesList.length - 1; i >= 0; i--) {
                let imageItem = imagesList[i];
                if (imageItem.imageScene === imageScene) {
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
     * 翻页
     */
    pageChange(pageNumber) {
        this.getPageData(pageNumber);
    }

    /**
     * 处理跳转
     */
    handleJump(type, id, event) {
        let path;
        switch(type+'') {
            case 'drama':
            path = 'drama/detail/';
            break;
            case 'special':
            path = 'opera/detail/';
            break;
            case 'article':
            path = 'article/detail/';
            break;
        }
        
        hashHistory.push(`${path}${id}`);
    }

    tagChange(params) {
        // 特别注意不能用setState赋值
        this.state.type = params.type || '-1';
        this.state.author = params.author || '-1'
        this.state.time = params.time || '-1';
        this.state.role = params.role || '-1';
        this.state.category = params.category || 'drama';
        // 
        if(params.keyword) {
            this.state.keyword = params.keyword;
        }
        else{ 
            this.state.keyword = '';
        }
        this.getContent();
    }

    keywordChange(keyword) {
        this.state.keyword = keyword;
    }

    render() {
        return (            
            <div className="conContent" >
            <SubEmiter eventName="tagChange"  listener= {this.tagChange.bind(this)}></SubEmiter> 
            <SubEmiter eventName="keywordChange"  listener= {this.keywordChange.bind(this)}></SubEmiter>
                <div style={{display:this.state.hasListData?'block':'none'}}>
                    <Row gutter={6} >
                        {
                            this.state.contentList && this.state.contentList.map((item,index)=>(                         
                              <Col key={index} className="gutter-row" span={8} onClick={this.handleJump.bind(this,item.type,item.id)}>
                                <div className="gutter-box contentItem">
                                    <div className="title">{item.title}</div>
                                    <div className="line"></div>
                                    <div className="realContent" >
                                        <div className="img">
                                            <img className="labelImage" src={require('assets/images/play-icon.png')} style={{display:item.type!=="article"?"block":"none"}}/>
                                            <img className="realImage" src={item.image}/>
                                        </div>                                        
                                        <div className="innerContent">
                                            <div className="contentDesc" >{item.content&&item.content.length<44?item.content:item.content&&item.content.substr(0,40)+'...'}</div>
                                            <button className="tag" >{item.buttonName}</button>
                                        </div>
                                    </div>
                                </div>
                              </Col>
                            ))
                        }
                    </Row>
                    <Pagination showQuickJumper current={this.state.current} defaultCurrent={1} pageSize={this.state.pageSize} total={this.state.total} onChange={this.pageChange.bind(this)} style={{padding:"20px 30px 28px 4px"}}/>
                </div>
                <div style={{display:this.state.hasListData?"none":"block",textAlign:"center","marginTop":"80px"}}>
                    <img src={require('assets/images/noContent.png')}/>
                </div>
            </div>
        );
    }
}