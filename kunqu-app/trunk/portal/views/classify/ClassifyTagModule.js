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
 * 分类-左边标签项
 */
export default class ClassifyTagModule extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            // 分类 默认选剧典
            category: 'drama',
            // 剧目 默认选全部
            type: '-1',
            // 作者 默认选全部
            author: '-1',
            // 年代 默认选全部
            time: '-1',
            // 角色，默认选全部
            role: '-1',
            typesList: [],
            authorsList: [],
            timesList: [],
            rolesList: [],
        };
    }

    componentWillMount() {
    }

    /**
     * 页面首次渲染完毕请求后台数据，5个请求
     */
    componentDidMount() {
        this.getTypes();
        this.getAuthors();
        this.getTimes();
        this.getRoles();
        this.selectDefaultTag(this.props.typeParam, this.props.valueParam);
    }    

    /**
     * 获取剧目
     */
    getTypes() {
        let self = this;
        get("kunqu/repertoiretag/queryAll")
            .then((data) => {
                if (data.meta.success) {
                    let list = data.data || [];
                    let typeParam = self.props.typeParam;
                    if(typeParam == '1') {
                        self.setSelectedValue('repertoireTagId', list);
                    }
                    self.setState({
                        typesList: list
                    });
                } else {

                }
            });
    }

    setSelectedValue(prop, list) {
        let valueParam = this.props.valueParam;
        
        list.map(function(item, index) {
            if(item[prop] == valueParam) {
                item.selected = true;
            }else{
                item.selected = false;
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
                if (data.meta.success) {
                    let list = data.data || [];

                    let typeParam = self.props.typeParam;
                    if(typeParam == '2') {
                        self.setSelectedValue('authorTagId', list);
                    }
                    self.setState({
                        authorsList: list
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
                if (data.meta.success) {
                    let list = data.data || [];

                    let typeParam = self.props.typeParam;
                    if(typeParam == '3') {
                        self.setSelectedValue('ageTagId', list);
                    }
                    self.setState({
                        timesList: list
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
                if (data.meta.success) {
                    let list = data.data || [];

                    let typeParam = self.props.typeParam;
                    if(typeParam == '4') {
                        self.setSelectedValue('roleTagId', list);
                    }
                    self.setState({
                        rolesList: list
                    });
                } else {

                }
            });
    }

    selectDefaultTag(type, value, el) {
        let classSelector;
        switch (type+'') {
            case '1':
                this.state.type = value;
                classSelector = '.typeItem';
                break;
            case '2':
                this.state.author = value;
                classSelector = '.authorItem';
                break;
            case '3':
                this.state.time = value;
                classSelector = '.timeItem';
                break;
            case '4':
                this.state.role = value;
                classSelector = '.roleItem';
                break;
            case '0':
            default:
                this.state.category = value;
                classSelector = '.categoryItem';
                break;
        }
        let targetEl;
        if(!el) {
            targetEl = $(`${classSelector}[name=${value}]`);
        }else{
            targetEl = $(el);
        }
        // 注意顺序
        $(classSelector).removeClass('selected');
        targetEl.addClass('selected');
    }

    /**
     * 标签点击事件
     */
    handleClick(type, value, event) {
        this.selectDefaultTag(type, value, event.target);
        var params = {
            type: this.state.type,
            author: this.state.author,
            time: this.state.time,
            role: this.state.role,
            category: this.state.category
        };
        Emiter.emit("tagChange", params);
    }    

    selectTag(params) {
        this.selectDefaultTag(params.type, params.category);
        // 只要点搜索进来的，其它默认全部
        this.selectDefaultTag(1, -1);
        this.selectDefaultTag(2, -1);
        this.selectDefaultTag(3, -1);
        this.selectDefaultTag(4, -1);
    }

    render() {
        let path = hashHistory.getCurrentLocation().pathname;
        return (
            <div className="tagContent" >
                <div className="tagTitle">
                    分类
                </div>
                <SubEmiter eventName="selectChange"  listener= {this.selectTag.bind(this)}>
                <Row gutter={6}>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box">
                        <span name="drama" className="categoryItem tagItem selected" onClick={this.handleClick.bind(this,'0','drama')} >剧典</span>
                    </div>
                  </Col>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box" >
                        <span name="special" className="categoryItem tagItem" onClick={this.handleClick.bind(this,'0','special')} >曲典</span>
                        </div>
                  </Col>
                    <Col className="gutter-row" span={8}>
                        <span name="article" className="categoryItem tagItem" onClick={this.handleClick.bind(this,'0','article')} >文章</span>
                  </Col>
                </Row>
                </SubEmiter>
                <div className="tagTitle">
                    剧目
                </div>
                <Row gutter={6}>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box" >
                        <span name="-1" className="typeItem tagItem selected" onClick={this.handleClick.bind(this,'1','-1')}>全部</span>
                        </div>
                  </Col>
                  {
                      this.state.typesList.map((item,index)=>(
                            <Col key={index} className="gutter-row" span={8}>
                                <div className="gutter-box">
                                    <span name={item.repertoireTagId} className={`typeItem tagItem ${item.selected?'selected':''}`} onClick={this.handleClick.bind(this,'1',item.repertoireTagId)} >{item.tagName&&item.tagName<5?item.tagName:item.tagName&&item.tagName.substr(0,5)}</span>
                                </div>
                            </Col> 
                        ))
                  }                       
                </Row>
                <div className="tagTitle">
                    作者
                </div>
                <Row gutter={6}>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box" >
                        <span name="-1" className="authorItem tagItem selected" onClick={this.handleClick.bind(this,'2','-1')} >全部</span>
                        </div>
                  </Col>
                  {
                      this.state.authorsList.map((item,index)=>(
                            <Col key={index} className="gutter-row" span={8}>
                                <div className="gutter-box">
                                    <span name={item.authorTagId} className={`authorItem tagItem ${item.selected?'selected':''}`} onClick={this.handleClick.bind(this,'2',item.authorTagId)}>{item.tagName&&item.tagName<5?item.tagName:item.tagName&&item.tagName.substr(0,5)}</span>
                                </div>
                            </Col> 
                        ))
                  }       
                </Row>
                <div className="tagTitle">
                    年代
                </div>
                <Row gutter={6}>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box" >
                        <span name="-1" className="timeItem tagItem selected" onClick={this.handleClick.bind(this,'3','-1')}>全部</span>
                        </div>
                  </Col>
                  {
                      this.state.timesList.map((item,index)=>(
                            <Col key={index} className="gutter-row" span={8}>
                                <div className="gutter-box">
                                    <span name={item.ageTagId} className={`timeItem tagItem ${item.selected?'selected':''}`} onClick={this.handleClick.bind(this,'3',item.ageTagId)} >{item.tagName&&item.tagName<5?item.tagName:item.tagName&&item.tagName.substr(0,5)}</span>
                                </div>
                            </Col> 
                        ))
                  }       
                </Row>
                <div className="tagTitle">
                    角色
                </div>
                <Row gutter={6}>
                    <Col className="gutter-row" span={8}>
                    <div className="gutter-box" >
                        <span name="-1" className="roleItem tagItem selected" onClick={this.handleClick.bind(this,'4','-1')} >全部</span>
                        </div>
                  </Col>
                  {
                      this.state.rolesList.map((item,index)=>(
                            <Col key={index} className="gutter-row" span={8}>
                                <div className="gutter-box">
                                    <span name={item.roleTagId} className={`roleItem tagItem ${item.selected?'selected':''}`} onClick={this.handleClick.bind(this,'4',item.roleTagId)}>{item.tagName&&item.tagName<5?item.tagName:item.tagName&&item.tagName.substr(0,5)}</span>
                                </div>
                            </Col> 
                        ))
                  }       
                </Row>
            </div>           
        )
    }
}