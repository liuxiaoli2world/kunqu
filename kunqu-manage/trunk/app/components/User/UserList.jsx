import React, { Component } from 'react';
import {UserWrap} from "./style.js";
import {Pagination} from "antd";
import {get,post} from "../../utils/request.js";

class UserList extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            list : [],
            current : 1,
            total : 1,
            pageSize : 20,
            showQuickJumper : true,
        }
    }

    pageChange = (page,pageSize)=>{
        this.getUserlist(page);
    }

    componentDidMount() {
        this.getUserlist(1);
    }

    getUserlist = (pagenum)=>{
        get(`remote/api/kunqu/user/queryAll?pageNum=${pagenum}&pageSize=20`)
        .then(res=>{
            if(res&&res.meta&&res.meta.success){
                this.setState({
                    list : res.data.list || [],
                    total : res.data.total,
                    current : pagenum
                })
            }   
        }).catch(e=>{
            console.log(e)
        })
    }
    
    
    render() {
        let {list} = this.state;
        return (
            <UserWrap>
                <div className="clearfix">
                {
                    list && list.map((item,i)=>{
                        return (
                            <div className="panel-wrap" key={i}>
                                <div className='panel'>
                                    <dl>
                                        <dt><img src={require("../../Images/user.png")} alt='用户头像' /></dt>
                                        <dd className='name'>{item.username}</dd>
                                        <dd className='address'>{item.address}</dd>
                                    </dl>
                                    <ul>
                                        <li><i>{item.dramaNum}</i>剧典</li>
                                        <li><i>{item.specialNum}</i>曲典</li>
                                        <li><i>{item.articleNum}</i>文章</li>
                                    </ul>
                                </div>
                            </div>
                        )
                    })  
                }
                </div>
                <Pagination  {...this.state} onChange={this.pageChange.bind(this)}/>
            </UserWrap>
        );
    }
}

export default UserList;