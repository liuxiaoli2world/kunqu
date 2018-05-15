import React, { Component } from 'react'
import ResourceData from './ResourceData'
import UserData from './UserData'
import { Tabs } from 'antd'
import Wrapper from './wrapper'
const TabPane = Tabs.TabPane;

class componentName extends Component {
    render() {
        return (
            <Wrapper>
                <Tabs type="card">
                    <TabPane tab="资源分析" key="1">
                        <ResourceData />
                    </TabPane>
                    <TabPane tab="用户分析" key="2">
                        <UserData />
                    </TabPane>
                </Tabs>
            </Wrapper>
        );
    }
}

export default componentName;