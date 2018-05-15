import 'normalize.css'
import '../../app/common/reset.css'
import '../../app/common/layout.less'
import React from 'react'
import ReactDOM from 'react-dom'
import routes from '../../app/routes'
import { Router, Route, browserHistory } from 'react-router'



ReactDOM.render(<Router history={browserHistory}>
    {routes}
    </Router>
 ,
  document.querySelector('.react-container')
)
