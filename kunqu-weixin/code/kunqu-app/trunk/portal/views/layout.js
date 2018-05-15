import React from "react";
import {Link} from "react-router";
import "./reset.css";
import BottomList from "portal/views/bottom/bottom";
import hashHistory from "react-router";
/**
 * 这个文件定义页面的结构
 */
class Layout extends React.Component{
    constructor(props){
		super(props)
	}
	render(){
        return (
        	<div>
	            <div>
		            {this.props.children}
	            </div>
				{
					this.props.location.pathname.indexOf('/opera/detail')<0 ? 
					<footer>
	          			<BottomList/>
	            	</footer>
					: null
				}
	            
	        </div>
	    )
    }
}

export default Layout;