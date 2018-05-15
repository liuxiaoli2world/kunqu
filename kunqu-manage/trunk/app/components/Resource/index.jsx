import React, { Component } from 'react';
import { Item, RecommondItem } from './item';
import NewDrama from './newDrama';
import NewOpera from './newOpera';
import NewArticle from './newArticle';
import { getList, getAboutList, batchAddAbout } from './resourceData';
import styled from 'styled-components';
import '../../global.css';
import './style.scss';
import { Tabs, Button, Input, Modal, Pagination } from 'antd';
const TabPane = Tabs.TabPane;
/**
 * 
 * 资源管理
 * 
 * @class Resource
 * @extends {Component}
 */
const ItemSwapper = styled.div`
    width: 304px;
    display: inline-block;
    margin: 10px;
    border: 1px solid #ccc;
    .show-content {
        padding: 15px;
    }
    .image {
        display: block;
        height: 180px;
        width: 100%;
        border: 1px solid #ccc;
    }
    .title {
        font-size: 14px;
        line-height: 21px;
        height: 42px;
        color: #333;
        margin: 10px 0;
    }
    .content {
        font-size: 12px;
        line-height: 18px;
        color: #666;
        height: 36px;
        margin-bottom: 14px;
    }
    .count {
        font-size: 12px;
        color: #666;
        height: 15px;
    }
    .buttons {
        background: #f8f8f8;
        height: 70px;
        line-height: 70px;
        text-align: center;
        .btn {
            font-size: 12px;
            background: #eee;
            color: #666;
            width: 70px;
            height: 32px;
            border: 1px solid #ccc;
            border-radius: 0px;
            margin: 0 10px;
            padding: 0;
            &:hover {
                color: #8c8475;
                border: 1px solid #8c8475;
                background: #fdf7ec;
            }
        }
    }
    
`

class Resource extends Component {
    constructor(props) {
        super(props);
        this.newTabIndex = 0;
        this.propMapping = {
            'drama': {
                'id': 'dramaId',
                'title': 'dramaName',
                'content': 'dramaDesc',
                'count': 'palyAmount',
                'imageUrl': 'dramaImages'
            },
            'special': {
                'id': 'specialId',
                'title': 'specialName',
                'content': 'specialDesc',
                'count': 'palyAmount',
                'imageUrl': 'imageList'
            },
            'article': {
                'id': 'articleId',
                'title': 'title',
                'content': 'articleDesc',
                'count': 'sumReadCount',
                'imageUrl': 'articleImages'
            }
        }
        this.state = {
            activeKey: '1',
            newPanel: [],
            beforeNewKey: '1',
            recommendVisible: false,
            confirmLoading: false,
            items: [],
            tempArr: [],
            aboutList: [],
            current: 1,
            total: 0,
            searchWord: ''
        };
    }

    componentDidMount() {
        this.loadList('drama');
    }

    loadList = (type, pageNum = 1) => {
        let params = {
            queryType: type,
            pageNum: pageNum,
            pageSize: 10,
            keyword: this.state.searchWord
        }
        getList(params).then(respData => {
            if (true) {
                this.transferData(respData, type);
            } else {

            }
        })
    }

    /**
     * 
     * 返回的数据字段隐射
     * 
     * @memberof Resource
     */
    transferData = (respData, type) => {
        let items = [];
        let items0 = respData.data.list;
        let length = items0.length;
        let props = this.propMapping[type];
        for (let i = 0; i < length; i++) {
            let item = {
                'id': items0[i][props['id']],
                'title': items0[i][props['title']],
                'content': items0[i][props['content']],
                'count': items0[i][props['count']],
                'imageUrl': this.getImageUrl(items0[i][props['imageUrl']], type),
                'typename': '剧典',
                'deleteResorce': this.deleteResorce,
                'modify': this.modify,
                'recommend': this.recommend
            }
            items.push(item);
        }
        this.setState({
            items,
            current: respData.data.pageNum,
            total: respData.data.total
        });
    }

    getImageUrl = (imageData, type) => {
        let imageScene = '01';
        let imageUrl = '';
        switch (type) {
            case 'drama':
                imageScene = '01';
                break;
            case 'special':
                imageScene = '01';
                break;
            case 'article':
                imageScene = '02';
                break;
            default:
                imageScene = '01';
        }

        for (let i = 0, len = imageData.length; i < len; i++) {
            if (imageData[i].imageScene === imageScene) {
                imageUrl = imageData[i].imageUrl;
                break;
            }
        }
        return imageUrl;
    }

    getDefaultInfo = (key) => {
        let url = '';
        switch (key) {
            case '1':

                break;
            case '2':

                break;
            case '3':

                break;
            default:

        }
        return {};
    }

    queryBtnClickEvent = (type) => {
        this.loadList(type);
    }

    inputOnChange = (event) => {
        this.setState({
            searchWord: event.target.value
        });
    }
    /**
     * 
     * 翻页按钮事件
     * 
     * @memberof Resource
     */
    onPageChange = (page) => {
        console.log(page);
        let type = 'drama';
        switch (this.state.activeKey) {
            case '1':
                type = 'drama';
                break;
            case '2':
                type = 'special';
                break;
            case '3':
                type = 'article';
                break;
            default:
                type = 'drama';
        }
        this.loadList(type, page);
    }

    /**
     * 
     * tab标签页切换事件
     * 
     * @memberof Resource
     */
    onChange = (activeKey) => {
        this.setState({ activeKey });
        let type;
        switch (activeKey) {
            case '1':
                type = 'drama';
                break;
            case '2':
                type = 'special';
                break;
            case '3':
                type = 'article';
                break;
            default:
                type = 'drama';
        }
        this.setState({
            searchWord: ''
        });
        this.loadList(type);
    }

    /**
     * 
     * 关闭tab标签页
     * 
     * @memberof Resource
     */
    onEdit = (targetKey, action) => {
        if (action === 'remove' && targetKey === '4') {
            this.setState({
                newPanel: [],
                activeKey: this.state.beforeNewKey
            });
        }
    }

    /**
     * 
     * 打开tab标签
     * 包含新增和修改
     * 
     * @memberof Resource
     */
    add = (key, defaultInfo) => {
        let newPanel;
        let preName = defaultInfo ? '修改' : '新增';
        switch (key) {
            case '1':
                newPanel = <TabPane tab={`${preName}剧典`} key="4" closable={true}>
                    <NewDrama {...defaultInfo} />
                </TabPane>;
                break;
            case '2':
                newPanel = <TabPane tab={`${preName}曲典`} key="4" closable={true}>
                    <NewOpera  {...defaultInfo} />
                </TabPane>;
                break;
            case '3':
                newPanel = <TabPane tab={`${preName}文章`} key="4" closable={true}>
                    <NewArticle  {...defaultInfo} />
                </TabPane>;
                break;
            default:
                newPanel = <TabPane tab={`${preName}剧典`} key="4" closable={true}>
                    <NewDrama  {...defaultInfo} />
                </TabPane>;
        }
        this.setState({ activeKey: '4', newPanel: newPanel, beforeNewKey: key });
    }

    handleChange = (key) => {
        console.log('handleChange');
    }

    /**
     * 
     * 相关推荐按钮事件
     * 
     * @memberof Resource
     */
    recommend = (id) => {
        console.log('recommond');
        this.showModule();
        let params = {
            id: id,
            pageNum: 1,
            pageSize: 10
        }
        getAboutList(params).then(respData => {
            if (true) {
                debugger;
                let list = respData.data.list;
                this.initTempArry(list);
                this.setState({
                    // aboutList: this.processRecommonData(),
                    aboutList: list,
                    currentDramaId: id
                });
            } else {

            }
        })
    }

    processRecommonData = (list) => {
        for (let i = 0, len = list.length; i < len; i++) {
            let item = list[i];
            for (let j = 0, len1 = this.state.tempArr.length; j < len1; j++) {
                let temp = this.state.tempArr[j];
                if (item.drama.dramaId === temp.drama.dramaId) {
                    item.isAbout = temp.isAbout;
                    continue;
                }
            }
        }
        return list;
    }


    onRecommondPageChange = (pageNum) => {
        getAboutList(params).then(respData => {
            if (true) {
                this.setState({
                    aboutList: this.processRecommonData(respData.data.list),
                    currentDramaId: id
                });
            } else {

            }
        })
    }

    /**
     * 
     * 修改按钮事件
     * 
     * @memberof Resource
     */
    modify = (panelKey, id) => {
        console.log('modify' + panelKey);
        let defaultInfo = this.getDefaultInfo(panelKey);
        this.add(panelKey, defaultInfo);
    }

    /**
     * 
     * 删除按钮事件
     * 
     * @memberof Resource
     */
    deleteResorce = (panelKey, id) => {
        console.log('deleteResorce' + panelKey);
    }

    showModule = () => {
        this.setState({
            recommendVisible: true
        });
    }

    handleCancel = () => {
        console.log("cancel");
        this.setState({
            recommendVisible: false
        });
    }

    handleModalOK = () => {
        console.log("ok");
        this.setState({
            confirmLoading: true,
        });
        setTimeout(() => {
            this.setState({
                recommendVisible: false,
                confirmLoading: false
            });
        }, 2000);
        this.onAboutSave();
    }

    initTempArry = (list) => {
        debugger;
        let tempArr = [];
        for (let i = 0, len = list.length; i < len; i++) {
            if (list[i].isAbout === 'y') {
                tempArr.push(list[i]);
            }
        }
        this.setState({
            tempArr
        });
    }

    onAboutItemClickEvent = (item, isAbout) => {
        debugger;
        console.log(item);
        let arr = this.state.tempArr;
        item.isAbout = isAbout;
        let index;
        if (isAbout === 'y') {
            arr.push(item);
        } else {
            index = arr.findIndex((value, index0, arr) => {
                if (item.drama.dramaId === value.drama.dramaId) {
                    return true;
                } else {
                    return -1;
                }
            });
            console.log(index);
            arr = [...arr.slice(0, index), ...arr.slice(index + 1, arr.length)];
        }
        this.setState({
            tempArr: arr
        });
    }

    onAboutSave = () => {
        debugger;
        let aboutIds = [];
        for (let i = 0, len = this.state.tempArr.length; i < len; i++) {
            aboutIds.push(this.state.tempArr[i].drama.dramaId);
        }
        let params = {
            aboutIds,
            dramaId: this.state.currentDramaId
        };
        batchAddAbout(params).then((respData) => {
            console.log('ok');
        });
    }

    render() {
        let item = {
            title: '剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典',
            content: '剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典剧典',
            typename: '剧典',
            count: 1003,
            deleteResorce: this.deleteResorce,
            modify: this.modify,
            recommend: this.recommend
        }

        let items = [item, item, item, item, item, item];

        return (
            <div className="resource-wrapper">
                <Tabs
                    type="card"
                    onChange={this.onChange}
                    activeKey={this.state.activeKey}
                    type="editable-card"
                    onEdit={this.onEdit}
                    hideAdd={true}
                >
                    <TabPane tab="剧典管理" key="1" closable={false}>
                        <div className="content-header">
                            <p className="item">剧典名称：</p>
                            <Input className="item search-input" size="large" placeholder="剧典名称" value={this.state.searchWord} onChange={this.inputOnChange} />
                            <Button className="item query-btn" onClick={this.queryBtnClickEvent.bind(this, 'drama')}>查询</Button>
                            <Button className="add-btn" onClick={this.add.bind(this, '1')}>新增</Button>
                        </div>
                        <div className="clear content-wrapper">
                            {
                                this.state.items.map((item, index) => (
                                    <Item key={index}
                                        panelKey='1'
                                        id={item.id}
                                        title={item.title}
                                        typeName={item.typename}
                                        content={item.content}
                                        count={item.count}
                                        imageUrl={item.imageUrl}
                                        deleteResorce={item.deleteResorce}
                                        modify={item.modify}
                                        recommend={item.recommend}
                                    />
                                ))
                            }
                        </div>
                    </TabPane>
                    <TabPane tab="曲典管理" key="2" closable={false}>
                        <div className="content-header">
                            <p className="item">曲典名称：</p>
                            <Input className="item search-input" size="large" placeholder="曲典名称" value={this.state.searchWord} onChange={this.inputOnChange} />
                            <Button className="item query-btn" onClick={this.queryBtnClickEvent.bind(this, 'special')}>查询</Button>
                            <Button className="add-btn" onClick={this.add.bind(this, '2')}>新增</Button>
                        </div>
                        <div className="clear content-wrapper">
                            {
                                this.state.items.map((item, index) => (
                                    <Item key={index}
                                        panelKey='2'
                                        id={item.id}
                                        title={item.title}
                                        typeName={item.typename}
                                        content={item.content}
                                        count={item.count}
                                        imageUrl={item.imageUrl}
                                        deleteResorce={item.deleteResorce}
                                        modify={item.modify}
                                        recommend={item.recommend}
                                    />
                                ))
                            }
                        </div>
                    </TabPane>
                    <TabPane tab="文章管理" key="3" closable={false}>
                        <div className="content-header">
                            <p className="item">文章名称：</p>
                            <Input className="item search-input" size="large" placeholder="文章名称" value={this.state.searchWord} onChange={this.inputOnChange} />
                            <Button className="item query-btn" onClick={this.queryBtnClickEvent.bind(this, 'article')}>查询</Button>
                            <Button className="add-btn" onClick={this.add.bind(this, '3')}>新增</Button>
                        </div>
                        <div className="clear content-wrapper">
                            {
                                this.state.items.map((item, index) => (
                                    <Item key={index}
                                        panelKey='3'
                                        id={item.id}
                                        title={item.title}
                                        typeName={item.typename}
                                        content={item.content}
                                        count={item.count}
                                        imageUrl={item.imageUrl}
                                        deleteResorce={item.deleteResorce}
                                        modify={item.modify}
                                        recommend={item.recommend}
                                    />
                                ))
                            }
                        </div>
                    </TabPane>
                    {this.state.newPanel}
                </Tabs>
                <Pagination showQuickJumper current={this.state.current} total={this.state.total} onChange={this.onPageChange} />

                <Modal title=""
                    wrapClassName="vertical-center-modal"
                    visible={this.state.recommendVisible}
                    closable={false}
                    footer={[
                        <Button key="submit" type="primary" size="large" loading={this.state.confirmLoading} onClick={this.handleModalOK}>保存</Button>,
                        <Button key="back" type="default" size="large" onClick={this.handleCancel}>关闭</Button>
                    ]}
                >
                    <div className="content-header">
                        <p className="item">剧典名称：</p>
                        <Input className="item search-input" size="large" placeholder="剧典名称" />
                        <Button className="item query-btn">查询</Button>
                    </div>
                    <div className="clear content-wrapper">
                        {
                            this.state.aboutList.map((item, index) => (
                                <RecommondItem key={index}
                                    isAbout={item.isAbout === 'y' ? true : false}
                                    id={item.drama.dramaId}
                                    sourceData={item}
                                    title={item.drama.dramaName}
                                    content={item.drama.dramaDesc}
                                    onClick={this.onAboutItemClickEvent}
                                />
                            ))
                        }
                    </div>
                </Modal>
            </div>
        );
    }
}

export default Resource;
