import React from "react";
import ReactDOM from "react-dom";
import {
    hashHistory,
    Link
} from "react-router";
import "./opera.css";
import {get,post} from "util/request.js";
import SuggestCont from "./SuggestCont.js";



class Drama extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {       
        return (
		<div className="videos container">
			<img src={require('assets/images/banner-2.png')} className="banner"/>
			<SuggestCont value="special" type={0} />
		</div>)
    }
}

export default Drama;