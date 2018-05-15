import React from "react";
import ReactDOM from "react-dom";
import {
    hashHistory,
    Link
} from "react-router";
import HotCont from "./HotCont.js";
import SuggestCont from "./SuggestCont.js"
import {get,post} from "util/request.js";
import "./drama.css";



class Drama extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {       
        return (
		<div className="videos container">
			<img src={require('assets/images/banner.png')} className="banner"/>
			<img src={require('assets/images/hot-title.png')} className="hot-title-img"/>
			<HotCont/>
			<SuggestCont source="/kunqu/drama/query?type=2&pageSize=8&pageNum=1" text="推荐剧典"/>
			<SuggestCont source="/kunqu/drama/query?type=4&pageSize=4&pageNum=1" text="折子戏"/>
		</div>)
    }
}

export default Drama;