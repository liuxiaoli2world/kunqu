import App from './containers/App'
import Home from './components/Home'
import Resource from './components/Resource'
import Knowledge from './components/Knowledge'
import UserManage from './components/User'
import Data from './components/Data'
import {  Route, IndexRoute } from 'react-router'
import React from 'react'
import config from '../platforms/common/config/default'
export default (

  <Route path={config.contextPath} component={App}>
    <Route path="resource" component={Resource} />
    <Route path="knowledge" component={Knowledge} />
    <Route path="user" component={UserManage} />
    <Route path="data" component={Data} />
    <IndexRoute component={Home} />
  </Route>

)
