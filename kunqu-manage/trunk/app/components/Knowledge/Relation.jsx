import React, { Component } from 'react';
import {RelationWrapper} from "./style.js";
import {Select,Button,Pagination,message} from "antd";
import {get,post} from "../../utils/request.js";
import {SubEmiter,Emiter} from "../../utils/index.js";

const Option = Select.Option;

class Relation extends Component {
    constructor(props){
        super(props);
        this.state = {
            sourcelist : [],
            current : 1,
            total : 1,
            pageSize : 6,
            showQuickJumper : true,
            type : 'drama',
            arr : [],
            isShow : false
        }
    }

    componentWillReceiveProps(nextProps) {
        // if(nextProps.type && nextProps.ceremonyId){
        //     let {arr} = this.state;
        //     this.getSourceList(1,nextProps.type,nextProps.ceremonyId);
        //     get(`remote/api/kunqu/ceremonysource/querySourceIds?id=${nextProps.ceremonyId}&sourceType=${nextProps.type}`)
        //     .then(res=>{
        //         if(res&&res.meta&&res.meta.success){
        //             this.setState({
        //                 isShow : true,
        //                 arr : res.data
        //             })
        //         }
        //     }).catch(e=>{
        //         console.log(e)
        //     })
            
        // }
    }

    onHandleSetting = (params)=>{
        if(params.type && params.ceremonyId){
            let {arr} = this.state;
            this.getSourceList(1,params.type,params.ceremonyId);
            get(`remote/api/kunqu/ceremonysource/querySourceIds?id=${params.ceremonyId}&sourceType=${params.type}`)
            .then(res=>{
                if(res&&res.meta&&res.meta.success){
                    this.setState({
                        isShow : true,
                        arr : res.data
                    })
                }
            }).catch(e=>{
                console.log(e)
            })
            
        }
    }
    

    componentDidMount() {
        
    }
    
    getSourceList(pagenum,type,id){
        let _this = this;
        get(`remote/api/kunqu/ceremonysource/queryBySourceType?id=${id}&sourceType=${type}&pageNum=${pagenum}&pageSize=6`)
        .then(res=>{
            if(res&&res.data&&res.data.list.length){
                let {sourcelist} = _this.state;
                res.data.list.map((item,i)=>{
                    if(type=='drama'){
                        item.name = item.dramaName;
                        item.des = item.dramaDesc;
                        item.imgUrl = item.dramaImages[0].imageUrl;
                        item.id = item.dramaId;
                    }else if(type=='special'){
                        item.name = item.specialName;
                        item.des = item.specialDesc;
                        item.imgUrl = item.imageList[0].imageUrl;
                        item.id = item.specialId;
                    }else{
                        item.name = item.title;
                        item.des = item.articleDesc;
                        item.imgUrl = item.articleImages[0].imageUrl;
                        item.id = item.articleId;
                    }  
                })
                _this.setState({
                    sourcelist : res.data.list || [],
                    total : res.data.total,
                    current : pagenum,
                    type,
                    ceremonyId:id,
                })
            }
        })
    }

    pageChange = (page,pageSize)=>{
        let {type,ceremonyId} = this.state;
        this.getSourceList(page,type,ceremonyId)
    }


    //资源关联
    handleRelation = (id,i)=>{
        let {arr,sourcelist} = this.state;
        console.log(arr,id)
        if(arr.indexOf(id)>=0){
            for(var j=0;j<arr.length;j++){
                if(arr[j] == id){
                    arr.splice(j,1)
                }
            }
        }else{
            arr.push(id);
        }
        sourcelist[i].isRelative = false
        this.setState({
            arr,
            sourcelist
        })
    }

    //取消
    handleCancel = ()=>{
        this.setState({
            isShow : false,
            arr : [],
            sourcelist : [] 
        })
    }
    //保存
    handleSave = ()=>{
        let {type,ceremonyId,arr} = this.state;
        post(`remote/api/kunqu/ceremonysource/add?id=${ceremonyId}&sourceType=${type}`,arr)
        .then(res=>{
            if(res&&res.meta&&res.meta.success){
                message.success("保存成功");
                this.setState({
                    isShow : false,
                    arr : []
                })
                this.props.parentCallback();
            }
        }).catch(e=>{
            console.log(e)
            message.error("保存失败");
        })

    }


    render() {
        let {sourcelist,type,arr,isShow} = this.state;
        return (
            <RelationWrapper style={{display:isShow ? 'block':'none'}}>
                <SubEmiter eventName="handleSetting" listener={this.onHandleSetting}>
                <div className="container">
                    {/* <div className="search-type">
                        资源类型：
                        <Select defaultValue={type} style={{ width: `${90/14}rem` }} onChange={this.handleTypeChange}>
                            <Option value="drama">剧典</Option>
                            <Option value="special">曲典</Option>
                            <Option value="article">文章</Option>
                        </Select>
                    </div> */}
                    <div className="source-list clearfix" style={{marginTop:`${40/14}rem`}}>
                        {
                            sourcelist && sourcelist.map((item,i)=>{
                                return (
                                    <div className="source-item-wrap" key={i}  onClick={this.handleRelation.bind(this,item.id,i)}>
                                        <div className={`source-item  ${ item.isRelative || arr.indexOf(item.id)>=0 ?'ischosed':''}`}>
                                            <div><img src={item.imgUrl} alt="资源封面" /></div>
                                            <h4>{item.name}</h4>
                                            <p>简介：{item.des}</p>
                                            <img src={require("../../Images/chosed.png")} alt="选择" className='tag' style={{display:item.isRelative || arr.indexOf(item.id)>=0 ?'block':'none'}}/>
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div> 
                    <div className="footer">
                        <Button onClick={this.handleSave.bind(this)}>保存</Button>
                        <Button onClick={this.handleCancel.bind(this)}>取消</Button>
                        <Pagination  {...this.state} onChange={this.pageChange.bind(this)}/>
                    </div>
                </div>
                </SubEmiter>
            </RelationWrapper>
        );
    }
}

export default Relation;