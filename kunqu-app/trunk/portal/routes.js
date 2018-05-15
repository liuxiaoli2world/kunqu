import * as React from 'react';
import {
  Route,
  IndexRoute,
  Redirect,
  IndexRedirect,
  hashHistory
} from 'react-router';

import Layout from "./views/layout.js";
import Home from "./views/home";
import Classify from "./views/classify";
import Drama from "./views/drama";
import Detail from "./views/drama/detail";
import Opera from "./views/opera";
import OperaDetail from "./views/opera/detail";
import MainList from './views/list';
import Article from './views/article';
import ArticleDetail from './views/article/detail';
import Topic from './views/home/topic';

export default (
  <Route path="/" component={Layout}>
    <Route path="list(/:dirId)" component={MainList}>
    </Route>
    <Route path="classify(/:type)(/:value)(/:keyword)" component={Classify}>    
    </Route>
    <Route path="topic/:id" component={Topic}>    
    </Route>
    <Route path="drama" component={Drama}>  
    </Route>
    <Route path="drama/detail/:id" component={Detail}>
    </Route> 
    <Route path="opera" component={Opera}>   
    </Route>
	   <Route path="opera/detail(/:id)(/:index)" component={OperaDetail}>
    </Route> 
      <Route path="article" component={Article}>
    </Route> 
    <Route path="article/detail/:id" component={ArticleDetail}>
    </Route> 
    <IndexRoute component={Home}/>
  </Route>
);