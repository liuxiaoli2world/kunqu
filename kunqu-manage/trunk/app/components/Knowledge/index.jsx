import React, {Component} from 'react';
import {Link} from "react-router";
import {Tabs} from "antd";
import {Wrapper} from "./style.js";
import List from "./List.jsx";
import NewKnowledge from "./NewKnowledge.jsx";
import {get,post} from "../../utils/request.js";

const TabPane = Tabs.TabPane;

class componentName extends Component {
    constructor(props) {
        super(props);
        this.newTabIndex = 0;
        let panes = [
            {
                title: '知识服务',
                content: <List parentCallback={this.handleCallback.bind(this)}/>,
                key: '1',
                closable : false,
            }
        ];
        this.state = {
            activeKey: "1", //当前激活tab面板的key
            panes,
        }
    }

    handleCallback = (param)=>{
        if(param.type == 'add'){
            this.add('add');
        }else if(param.type == 'remove'){
            this.remove(this.state.activeKey);
        }
        else if(param.type == "modify"){
            this.setState({
                id : param.id
            },function(){
                this.add('modify');
            })
        }
    }

    onChange = (activeKey) => {
        this.setState({activeKey})
    }

    onEdit = (targetKey, action) => {
        this[action](targetKey);
    }

    add = (param) => {
        let panes = this.state.panes;
		const activeKey = `add${this.newTabIndex++}`;
		panes.forEach((pane, i) => {
			if (pane.key.indexOf("add") >= 0) {
				let targetKey = pane.key;
				panes = panes.filter(pane => pane.key !== targetKey);
			}
        });
        if(param == "add"){
            panes.push({ title: '知识构建', content: <NewKnowledge parentCallback={this.handleCallback.bind(this)}/>, key: activeKey ,closable : true});
        }else{
            panes.push({ title: '知识修改', content: <NewKnowledge parentCallback={this.handleCallback.bind(this)} id={this.state.id}/>, key: activeKey ,closable : true});
        }
		this.setState({ panes, activeKey })
    }
    remove = (targetKey) => {
        let activeKey = this.state.activeKey;
        let lastIndex;
        this.state.panes.forEach((pane, i) => {
            if (pane.key === targetKey) {
                lastIndex = i - 1;
            }
        });
        const panes = this.state.panes.filter(pane => pane.key !== targetKey);
        if (lastIndex >= 0 && activeKey === targetKey) {
            activeKey = panes[lastIndex].key;
        }
        this.setState({panes, activeKey});
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
                    onEdit={this.onEdit}>
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

export default componentName;