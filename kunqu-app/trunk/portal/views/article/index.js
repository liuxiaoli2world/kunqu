import React from "react";
import './article.scss';
import {
    hashHistory,
    Link
} from "react-router";

import BoutiqueListModule from './BoutiqueListModule.js';
import RankListModule from './RankListModule.js';
import RecommendListModule from './RecommendListModule.js';

class Article extends React.Component {
    constructor(props) {
        super(props);
    }
    render() {       
        return (
            <div className="content">
                <img src={require('assets/images/banner.png')} style={{margin:"30px 0"}}/>
                <BoutiqueListModule/>
                <div style={{float:"right",width:"400px"}}>                    
                    <RankListModule/>
                    <RecommendListModule/>
                </div>
            </div>
        )
    }
}

export default Article;