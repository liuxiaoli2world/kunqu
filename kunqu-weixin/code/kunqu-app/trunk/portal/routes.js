import * as React from 'react';
import {
  Route,
  IndexRoute,
  Redirect,
  IndexRedirect,
  hashHistory
} from 'react-router';

import Layout from "./views/layout.js";
import Home from "./views/home/index.js";
import DramaModule from "./views/drama/index.js";
import DramaDetail from "./views/drama/detail.js";
import OperaModule from "./views/opera/index.js";
import ReadModule from "./views/read/index.js";
import ReadDetail from "./views/read/detail.js";
import PCenterModule from "./views/pcenter/index.js";
import RecordModule from "./views/record/index.js"
import Music from './views/opera/music';

export default (
  <Route path="/" component={Layout}>
    <IndexRoute component={Home}/>
    <Route path="drama" component={DramaModule}/>
    <Route path="opera" component={OperaModule}/>
    <Route path="read" component={ReadModule}/>
    <Route path="/drama/detail/:id" component={DramaDetail}/>
    <Route path="read/detail/:id" component={ReadDetail}/>
    {/*<Route path="opera/detail/:id" component={OperaDetail}/>*/}
    <Route path="opera/detail/:id" component={Music}/>
    <Route path="pcenter" component={PCenterModule}/>
    <Route path="records" component={RecordModule}/>
  </Route>
);