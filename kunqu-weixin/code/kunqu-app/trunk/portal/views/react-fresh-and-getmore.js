'use strict'
import React from 'react'; import ReactDOM from 'react-dom';

class ReactReFresh extends React.Component {
  constructor(props) {
    super(props)
      this.viewDidScroll = this.viewDidScroll.bind(this)
      this.state = {
        isRefreshing : false,
        isLoadingMore : false,
		beforeScrollTop : document.body.scrollTop,
      }
  }
  render() {
    return (
      <div/>
    )
  }
  findNodeIndex(dom) {
    var targetNodeIndex = 0
    var nodes = document.getElementsByClassName(dom.className)
    for (var i = 0; i < nodes.length; i++) {
      if (nodes[i] = dom) {
        targetNodeIndex = i
        break
      }
    }
    return targetNodeIndex
  }
  viewDidScroll(event) {
    var dom = ReactDOM.findDOMNode(this)
    var tableViewIdName = dom.id
    var tableViewClassName = dom.className
    var targetNodeIndex = this.findNodeIndex(dom)
    var isFindNodeById = tableViewIdName ? true : false
    var indicatorClassName = 'infinit-table-spinner'
    var scrollviewOffsetY = document.body.scrollTop
    var scrollviewFrameHeight = dom.clientHeight
    var scrollviewContentHeight = dom.scrollHeight
	var afterScrollTop = document.body.scrollTop, delta = afterScrollTop - this.state.beforeScrollTop;	
    if( delta === 0 ) return false;
    var isDown = delta > 0 ? true : false;
	this.setState({beforeScrollTop : afterScrollTop});
    var sum = scrollviewOffsetY + scrollviewFrameHeight
    if (scrollviewOffsetY <=0 && !isDown) {
		  indicatorClassName = 'infinit-table-spinner-init'
		  if (!this.props.onScrollToTop) { return }
		  if (this.state.isRefreshing) { return }
		  this.setState({ isRefreshing: true })
		  if (this.props.useDefaultIndicator) {
			var refreshIndicator = document.createElement("div")
			refreshIndicator.className = indicatorClassName
			var tableView = isFindNodeById ? document.getElementById(tableViewIdName) : document.getElementsByClassName(tableViewClassName)[targetNodeIndex]
			tableView.insertBefore(refreshIndicator, tableView.firstChild)
		  }
		  // event
		  this.props.onScrollToTop(function () {
			this.setState({ isRefreshing: false })
			if (this.props.useDefaultIndicator) {
			  var tableView = isFindNodeById ? document.getElementById(tableViewIdName) : document.getElementsByClassName(tableViewClassName)[targetNodeIndex]
			  var firstChild = tableView.firstChild
			  if (firstChild.className.indexOf(indicatorClassName) > -1) {
				tableView.removeChild(firstChild)
			  }
			}
		  }.bind(this))
    } else if (document.documentElement.clientHeight + document.body.scrollTop + 768 >= document.body.scrollHeight && isDown && !this.props.isFinished) {
		console.log(123)
		  indicatorClassName = 'infinit-table-spinner-more'
		  if (!this.props.onScrollToBottom) { return }
		  if (this.state.isLoadingMore) { return }
		  this.setState({ isLoadingMore: true })
		  if (this.props.useDefaultIndicator) {
			var loadMoreIndicator = document.createElement("div")
			loadMoreIndicator.className = indicatorClassName
			var tableView = isFindNodeById ? document.getElementById(tableViewIdName) : document.getElementsByClassName(tableViewClassName)[targetNodeIndex]
			tableView.insertBefore(loadMoreIndicator, tableView.lastChild.nextSibling)
		  }
		  // event
		 
		  this.props.onScrollToBottom(function () {
			this.setState({ isLoadingMore: false })
			if (this.props.useDefaultIndicator) {
			  var tableView = isFindNodeById ? document.getElementById(tableViewIdName) : document.getElementsByClassName(tableViewClassName)[targetNodeIndex]
			  var lastChild = tableView.lastChild
			  if (lastChild.className.indexOf(indicatorClassName) > -1) {
				tableView.removeChild(lastChild)
			  }
			}
		  }.bind(this))
    }
  }
}

ReactReFresh.defaultProps = {
  useDefaultIndicator: true
}
export default ReactReFresh