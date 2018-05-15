
import React, { Component } from 'react';
import {Tabs} from "antd";
import UserList from "./UserList.jsx";
import CommentList from "./CommentList";
import {Wrapper} from "../Knowledge/style.js";

const TabPane = Tabs.TabPane;


class UserManage extends Component {
    constructor(props, context) {
        super(props, context);
        this.newTabIndex = 0;
        let panes = [
            {
                title: '用户管理',
                content: <UserList userCallback={this.handleusercallback.bind(this)}/>,
                key: '1',
                closable : false,
            },
            {
                title: '用户评论',
                content: <CommentList commentCallback={this.handlecommentCallback.bind(this)}/>,
                key: '2',
                closable : false,
            },
        ];
        this.state = {
            activeKey: "1",
            panes,
        }
    }

    handleusercallback = ()=>{

    }

    handlecommentCallback = ()=>{

    }

    onChange = (activeKey) => {
        this.setState({activeKey})
    }

    
    render() {
        return (
            <Wrapper>
            <Tabs
                type="editable-card"
                animated={false}
                hideAdd
                onChange={this.onChange}
                activeKey={this.state.activeKey}
            >
                    {
                        this.state.panes.map(pane => 
                            <TabPane tab={pane.title} key={pane.key} closable={pane.closable}>{pane.content}</TabPane>
                        )
                    }
            </Tabs>
            </Wrapper>
        );
    }
}

export default UserManage;