import React, { Component } from 'react';
import {Link} from "react-router";
import {Select,Input,Button,Popconfirm,Pagination,message} from "antd";
import {Box} from "./style.js";
import {get,post,formPost} from "../../utils/request.js";

const { TextArea } = Input;

class CommentList extends Component {
    constructor(props, context) {
        super(props, context);
        this.state = {
            show : -1 ,              //回复框显示
            commentlist : [],
            total : 1,
            pageSize : 6,
            current : 1,
            btnloading : false,
            showQuickJumper : true
        }
    }

    componentDidMount() {
        this.getOpinionlist(1);
    }

    getOpinionlist(pagenum){
        let params = {
            pageNum : pagenum,
            pageSize : 6
        }
        formPost(`remote/api/kunqu/messageboard/queryBy`,params)
        .then(res=>{
            if(res && res.data){
                res.data.list && res.data.list.map((item,i)=>{
                    item.userimg = 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509689638292&di=09e16bc1b7948926db481bb68b9b7804&imgtype=0&src=http%3A%2F%2Fcimage.tianjimedia.com%2FuploadImages%2F2017%2F10%2F20171010112821608.jpg';
                })
                this.setState({
                    commentlist : res.data.list || [],
                    total : res.data.total,
                    current : pagenum,
                })
            }
        }).catch(e=>{
            console.log(e)
        })
    }

    //点击回复
    showReply = (param)=>{
        this.setState({show : param})
    }

    //回复内容
    handleReplychange = (e)=>{
        this.setState({ answer : e.target.value })
    }

    //确定回复
    doReply = (id,i)=>{
        let {commentlist} = this.state;
        this.setState({ btnloading : true })
        if(this.state.answer){
            let queryStr = {
                messageBoardId : id,
                reply : encodeURI(this.state.answer)
            };
            formPost(`remote/api/kunqu/messageboard/replyMessage`,queryStr)
            .then(res=>{
                if(res && res.meta && res.meta.success){
                    commentlist[i].reply = this.state.answer;
                    this.setState({
                        commentlist,
                        show : -1,
                        answer : "",
                        btnloading : false,
                    })
                    message.success("回复成功");
                }
            }).catch(e=>{
                console.log(e)
                message.error("服务超时，回复失败");
                this.setState({
                    btnloading : false
                })
            })
        }else{
            this.setState({
                show : -1,
                btnloading : false,
            })
        }
    }

    //确认删除
    handleDelete = (id,i)=>{
        let {current} = this.state;
        get(`remote/api/kunqu/messageboard/remove/${id}`)
        .then(res=>{
            if(res && res.meta && res.meta.success){
                message.success("删除成功");
                this.getOpinionlist(current);
            }else{
                message.error("删除失败")
            }
        }).catch(e=>{
            console.log(e)
            message.error("删除失败")
        })
    }

    //页码切换
    pageChange = (page,pageSize)=>{
        this.getOpinionlist(page);
    }
    
    render() {
        let {commentlist,answer} = this.state;
        return (
            <Box className="clearfix">
                <div className="opinion-list">
                    {
                        commentlist && commentlist.map((item,i)=>{
                            return (
                                <div className="opinion-item clearfix" key={i}>
                                    <dl className="clearfix">
                                        <dt><img src={item.userimg} alt="用户头像"/></dt>
                                        <dd className="date"><i>{item.messageName}</i>发表于{item.createdAt}</dd>
                                        <dd className="con">@#{item.messageTheme}# {item.messageContent}</dd>
                                    </dl>
                                    <div className="reply-input clearfix" style={{display:this.state.show==i?"block":"none"}}>
                                        <TextArea onChange={this.handleReplychange.bind(this)} value={answer}/>
                                        <Button onClick={this.doReply.bind(this,item.messageBoardId,i)} loading={this.state.btnloading}>回复</Button>
                                    </div>
                                    <div className="reply-content" style={{display:item.reply?"block":"none"}}>
                                        <div className="arrow"><em></em><span></span></div>
                                        {item.reply?item.reply:""}
                                    </div>
                                    <div className="action">
                                        {item.reply?<span style={{color:"#999999"}}>已回复</span>
                                        :<a href="javascript:void(0);" onClick={this.showReply.bind(this,i)}>回复</a>}
                                        <Popconfirm title="确定删除该评论？" okText="是" cancelText="否" onConfirm={this.handleDelete.bind(this,item.messageBoardId,i)}>
                                            <a href="javascript:void(0);">删除</a>
                                        </Popconfirm>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>
                <Pagination  {...this.state} onChange={this.pageChange}/>
            </Box>
        );
    }
}

export default CommentList;
