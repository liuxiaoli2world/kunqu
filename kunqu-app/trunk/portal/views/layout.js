import React from "react";
import {Link} from "react-router";
import "./reset.css";
import "./layout.scss";
import Header from "portal/views/header/header";
/**
 * 这个文件定义页面的结构
 */
class Layout extends React.Component{
    render(){
        return (
        	<div>
				<Header />
	            <div>
		            {this.props.children}
	            </div>
	            <footer>
	          		<div className="footer_head">
	          			
	          		</div>
	          		<div className="footer_con" style={{"margin":"0 auto","width":"646px"}}>
	          			<img src={require("assets/images/weixincode.png")}  className="left footer_logo"/>
	          			<div className="left" style={{"width":"84px","marginLeft":"10px","marginTop":"15px","fontSize":"14px","color":"#9a9999"}}>
	          				时代昆曲<br/>微信公众号<br/>关注我们
	          			</div>
	          			<div className="left" style={{"margin":"15px 0 60px 30px","fontSize":"14px"}}>
		          			<div>
		          				<Link to="/">设为首页</Link>
		          				<span style={{color:"#a3a3a3"}}> | </span>
		          				<Link to="/">联系我们</Link>
		          				<span style={{color:"#a3a3a3"}}> | </span>
		          				<Link to="/">加入收藏</Link>
		          				<span style={{color:"#a3a3a3"}}> | </span>
		          				<Link to="/">广告合作</Link>
		          				<span style={{color:"#a3a3a3"}}> | </span>
		          				<Link to="/">申请链接</Link>
		          			</div>
	          				<div className="copyright">
	          					主办单位：时代昆曲<br/>
	          					所有版权未经统一授权不得复制或镜像&nbsp;&nbsp;&nbsp;&nbsp;Copyright@2017 时代昆曲
	          				</div>
	          			</div>
	          		</div>
	            </footer>
	        </div>
	    )
    }
}

export default Layout;