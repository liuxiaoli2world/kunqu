import React from "react";
import {Link} from "react-router";
import {ListWrap} from "./style.js";
import {Button,Input,Pagination,Icon,Popconfirm,message} from "antd";
import Relation from "./Relation.jsx";
import {get,post} from "../../utils/request.js";
import {SubEmiter,Emiter} from "../../utils/index.js";


class List extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            list : [],
            current : 1,
            total : 1,
            pageSize : 10,
            showQuickJumper : true,
            name : '',
        }
    }

    componentDidMount() {
        this.getList(1);
    }
    
    //remote/fs
    getList(pagenum,word){
        get(`remote/api/kunqu/ceremony/queryByKeyword?pageNum=${pagenum}&pageSize=10${word?'&keyword='+encodeURI(word):''}`)  
        .then(res=>{
            if(res&&res.data&&res.data.list){
                this.setState({
                    list : res.data.list || [],
                    current : pagenum,
                    total : res.data.total,
                })
            }
        }).catch(e=>{
            console.log(e)
        })
    }

    handleInputwords = (e)=>{
        this.setState({
            name : e.target.value
        })
    }
    //查询
    handleSearch = ()=>{
        let {name} = this.state;
        this.getList(1,name);
    }

    //页码切换
    pageChange = (page,pageSize)=>{
        let {name} = this.state;
        this.getList(page,name);
    }

    //新增
    addNew = ()=>{
        this.props.parentCallback({type:'add'});
    }
    //修改
    handleModify = (id)=>{
        this.props.parentCallback({type:'modify',id : id});
    }

    //设置关联
    handleClickSet = (ceremonyId,e)=>{
        let typeId = e.target.getAttribute("id");
        // this.setState({
        //     typeId : typeId,
        //     ceremonyId : ceremonyId
        // })
        Emiter.emit('handleSetting',{'type':typeId,'ceremonyId':ceremonyId})
    }

    //删除
    handleConfirm = (id)=>{
        get(`remote/api/kunqu/ceremony/remove/${id}`)
        .then(res=>{
            if(res&&res.meta&&res.meta.success){
                let {current} = this.state;
                message.success("删除成功");
                this.getList(current);
            }else{
                message.error("删除失败");
            }
        }).catch(e=>{
            console.log(e)
            message.error("删除失败");
        })
    }


    onHandleAdd = ()=>{
        this.getList(1);
    }

    handleModifySetting = ()=>{
        let {current,name} = this.state;
        this.getList(current,name);
    }
 
    render(){
        let {list,isSetting,name,ceremonyId} = this.state;
        return (
            <ListWrap>
                <SubEmiter eventName='handleAdd' listener={this.onHandleAdd}></SubEmiter>
                <div className="search-part clearfix">
                    <span>专题名称：</span>
                    <Input onChange={this.handleInputwords.bind(this)} value={name} onPressEnter={this.handleSearch}/>
                    <Button onClick={this.handleSearch.bind(this)}>查询</Button>
                    <Button className="add" onClick={this.addNew.bind(this)}><i>+</i>新增</Button>
                </div>
                <div className="list clearfix">
                    {
                        list && list.map((item,i)=>{
                            return (
                                <div className='item-wrap' key={item.ceremonyId}>
                                    <div className="item">
                                        <div className="item-body">
                                            <div className="custom-image">
                                                <img alt="专题封面" width="100%" src={item.coverImg} />
                                            </div>
                                            <div className="custom-card">
                                                <h3>{item.ceremonyName}</h3>
                                                <p>简介：{item.ceremonyDesc}</p>
                                                <div className="sourceNum">
                                                    <span>剧典：{item.dramaCount}</span>
                                                    <span>曲典：{item.specialCount}</span>
                                                    <span>文章：{item.articleCount}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div className='fot-action'>
                                            <div>
                                                <Button>资源关联</Button>
                                                <ul onClick={this.handleClickSet.bind(this,item.ceremonyId)}>
                                                    <li id='drama'>关联剧典</li>
                                                    <li id='special'>关联曲典</li>
                                                    <li id='article'>关联文章</li>
                                                </ul>
                                            </div>
                                            <Button onClick={this.handleModify.bind(this,item.ceremonyId)}>修改</Button>
                                            <Popconfirm title="确定删除该专题?" onConfirm={this.handleConfirm.bind(this,item.ceremonyId)} okText="是" cancelText="否"><Button>删除</Button></Popconfirm>
                                        </div>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
                <Pagination  {...this.state} onChange={this.pageChange.bind(this)}/>
                {/* <Relation type={this.state.typeId} ceremonyId={this.state.ceremonyId} parentCallback={this.onHandleAdd}/> */}
                <Relation parentCallback={this.handleModifySetting}/>
            </ListWrap>
        )
    }
}

export default List;