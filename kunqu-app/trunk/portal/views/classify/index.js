import React from "react";
import ClassifyTagModule from './ClassifyTagModule.js';
import ClassifyContentModule from './ClassifyContentModule.js';
import "./index.scss";

/**
 ** 分类模块入口
 */
export default class Classify extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            typeParam: 0,
            valueParam: 'drama'
        };
    }

    componentWillMount() {
        // 接收参数
        const state = this.props.params;
        const typeParam = state.type ? state.type : 0;
        const valueParam = state.value ? state.value : 'drama';
        const keyword = state ? state.keyword : '';
        this.setState({
            typeParam,
            valueParam,
            keyword
        });
    }

    render() {
        return (
            <div className="classifyContent">
                <ClassifyTagModule {...this.state}/>
                <ClassifyContentModule {...this.state}/>
            </div>
        )
    }
}