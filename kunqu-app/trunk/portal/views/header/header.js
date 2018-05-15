import React from "react";
import config from "config";
import {
	Link,
	hashHistory
} from "react-router";
import "./header.scss";
import {
	autobind
} from 'core-decorators'
import {
	SubEmiter,
	Emiter
} from 'util';
import {Carousel,Tabs,Menu,Icon,Modal,Form,Input,Button,message} from "antd";
import {get,formPost,post} from 'views/util/request';

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;
const FormItem = Form.Item;

class FormWrap extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			loading : false
		}
	}

	componentWillReceiveProps(props){
		this.setState({
			actionType : props.type
		})
		if(!props.isClose){
			this.props.form.resetFields();
		}
	}

	componentDidMount(){
		this.setState({
			actionType : this.props.type
		})
		message.config({
			top:"45%"
		})
	}

	handleSubmit = (e)=>{
		e.preventDefault();
		let {actionType} = this.state;
		this.props.form.validateFields((err,values)=>{
			if(!err){
				this.setState({
					loading : true
				})
				if(actionType == "login"){
					formPost(`kunqu/user/login`,{username:values.userName,password:values.password})
					.then(res=>{
						console.log(res)
						if(res && res.meta && res.meta.success){
							message.success("登录成功！")
							localStorage.setItem("user",res.data.username);
							localStorage.setItem("id",res.data.userId)
							this.setState({ 
								loading : false,
							})
							this.props.parentCloseCallback({
								isSuccess : true
							});
						}else{
							message.error("登录失败！")
							this.setState({ loading : false })
						}
					}).catch(e=>{
						this.setState({ loading : false })
					})
				}else{
					let params = {
						"password": values.password,
						"username": values.userName
					  };
					post(`kunqu/user/register`,params)
					.then(res=>{
						if(res && res.meta && res.meta.success){
							message.success("注册成功！")
							this.props.parentCloseCallback();
							this.setState({ loading : false })
						}else{
							message.error(res.meta.message)
							this.setState({ loading : false })
						}
					}).catch(e=>{
						this.setState({ loading : false })
					})
				}
			}
		})
	}

	checkPassword = (rule,value,callback)=>{
		const form = this.props.form;
		if(value && value !== form.getFieldValue('password')){
			callback("两次输入的密码不一致,请重新输入！")
		}else{
			callback();
		}
	}

	handleFocus = (type)=>{
		switch(type){
			case "username" :
				this.setState({
					isFocus1 : true
				})
			break;
			case "pwd" :
				this.setState({
					isFocus2 : true
				})
			break;
			case "pwd1" :
				this.setState({
					isFocus3 : true
				})
			break;
			default:break;
		}
	}

	handleBlur = (type)=>{
		switch(type){
			case "username" :
				this.setState({
					isFocus1 : false
				})
			break;
			case "pwd" :
				this.setState({
					isFocus2 : false
				})
			break;
			case "pwd1" :
				this.setState({
					isFocus3 : false
				})
			break;
			default:break;
		}
	}
	
	handleRegister = ()=>{
		this.props.parentCallback({btnText:"register"})
		this.props.form.resetFields();
	}

	render(){
		const { getFieldDecorator } = this.props.form;
		let {actionType , isFocus1 , isFocus2 , isFocus3 ,loading} = this.state;
		return (
			<Form onSubmit={this.handleSubmit} className="login-form">
				<FormItem>
					{getFieldDecorator('userName', {
						rules:actionType=="login" ? 
							[{ required: true, message: '用户名不能为空!' },] 
							: 
							[
								{ required: true, message: '用户名不能为空!' },
								{ pattern:/^[0-9a-zA-Z_]{6,24}$/, message: '请输入6-24位字符！' }
							],
					})(
						<Input prefix={<img src={isFocus1 ? require("assets/images/username-active.png") : require('assets/images/username.png')} alt="用户名" style={{width:25,height:25}}/>} placeholder="请输入用户名"  onFocus={this.handleFocus.bind(this,"username")} onBlur={this.handleBlur.bind(this,"username")}/>
					)}
				</FormItem>
				<FormItem style={{marginBottom:actionType=="login" ? 0 : 24}}>
					{getFieldDecorator('password', {
						rules: actionType=="login" ?
						[{ required: true, message: '密码不能为空!' },] :
						[
							{ required: true, message: '密码不能为空!' },
							{ pattern:/^[0-9a-zA-Z_]{6,24}$/, message: '请输入6-24位字符！' }
						],
					})(
						<Input prefix={<img src={isFocus2 ? require("assets/images/lock-active.png") : require('assets/images/lock.png')} alt="密码" style={{width:17,height:26}}/>} placeholder="请输入密码"  onFocus={this.handleFocus.bind(this,"pwd")} onBlur={this.handleBlur.bind(this,"pwd")} type="password"/>
					)}
				</FormItem>
				{
					actionType == "register" ?
					<FormItem>
						{getFieldDecorator('confirm', {
							rules: [{ required: true, message: '密码不能为空！' },{
								validator : this.checkPassword
							}],
						})(
							<Input prefix={<img src={isFocus3 ? require("assets/images/lock-active.png") : require('assets/images/lock.png')} alt="密码" />} placeholder="请确认密码" onFocus={this.handleFocus.bind(this,"pwd1")} onBlur={this.handleBlur.bind(this,"pwd1")} type="password"/>
						)}
					</FormItem>
					:
					null
				}
				<FormItem>
					<div className="form-text" style={{display : actionType=='login' ? 'block' : 'none'}}><span onClick={this.handleRegister.bind(this)}>注册账号</span></div>
					<Button type="primary" htmlType="submit" loading={loading}>
						{actionType=="login"?"登录":"注册"}
					</Button>
				</FormItem>
			</Form>

		)
	}
}

const LoginForm = Form.create()(FormWrap);

class Header extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			index: -1,
			open: false,
			items:[],
			typeValue: 'drama',
			keyword: '',
			visible : false
		}
	}
	@autobind
	show(i) {
		this.setState({
			index: i,
			open: true
		})
	}
	@autobind
	hide(i) {
		this.setState({
			index: -1,
			open: false
		})
	}

	textOnChange(event) {
		const keyword = event.target.value;
		this.setState({
			keyword: keyword
		});

		// 搜索条件变化
		Emiter.emit("keywordChange", keyword);
	}

	handleChange(event) {
		this.setState({
			typeValue: event.target.value
		});
	}

	onSearchBtnClickEvent(event) {
		// 在分类页面时不跳转 需要单独处理
		let path = hashHistory.getCurrentLocation().pathname;
		if(path.includes('classify')) {
			var params = {
	            type: 0,
	            category: this.state.typeValue,
	            keyword: this.state.keyword
	        };
	        Emiter.emit("selectChange", params);
	        Emiter.emit("tagChange", params);
		}
	}

	componentDidMount() {
		get('kunqu/dir/queryRoot')
			.then((data) => {
				if(data && data.meta && data.meta.success){
					this.setState({
						items:data.data
					})
				}
			})
	}

	handleCancel = (option)=>{
		this.setState({
			visible : false,
		})

	}

	handleLogin = ()=>{
		this.setState({
			formtype : "login",
			visible : true
		})
	}

	handleCallback = (option)=>{
		this.setState({
			formtype : option.btnText
		})
	}
	handleOut = ()=>{
		localStorage.clear();
		this.setState({
			username : ''
		})
	}

	render() {
		const pageName = this.props.pagename;
		const navList = [
        	{path:"/",name:"首页"},
        	{path:"classify",name:"分类"},
        	{path:"drama",name:"剧典"},
        	{path:"opera",name:"曲典"},
        	{path:"article",name:"精品阅读"}
        ];
		const items = this.state.items;
		let {confirmLoading ,visible,formtype} = this.state;
		let path = hashHistory.getCurrentLocation().pathname;
		let username = localStorage.getItem("user");
        //onClick={this.onSearchBtnClickEvent.bind(this)}
		return (
			<div>
				<SubEmiter eventName="goLogin"  listener= {this.handleLogin.bind(this)}></SubEmiter> 
				{/*--------------头部-------------------*/}
		        <header>
	            	<div className="headContent clearfix">
	            		<div className="logo left">
	            			<Link to="/" title="时代昆曲"><img src={require("assets/images/logo_pc.png")} alt="logo"/></Link>
	            		</div>
	            		<div className="searchModule">
	            		 	<select className="selectInput" defaultValue="drama" onChange={this.handleChange.bind(this)}>
						      <option value="drama">&nbsp;&nbsp;剧典</option>
						      <option value="special">&nbsp;&nbsp;曲典</option>
						      <option value="article">精品阅读</option>
						    </select>
	            			<input className="searchInput" type="text" onChange={this.textOnChange.bind(this)}/>
	            			<Link to={{pathname:`/classify/0/${this.state.typeValue}/${this.state.keyword}`}} style={{float:"right",marginRight:-1}}><button className="searchBtn iconfont" name="search_btn" onClick={this.onSearchBtnClickEvent.bind(this)}>&#xe8e0;</button></Link>
	            		</div>
						<div className="wx_login" onClick={username ? null : this.handleLogin.bind(this)}>
							<img src={username ? require("assets/images/userimg.png") :require("assets/images/mine.png")} alt="登录" />
							<span>{username ? username : "登录"}</span>
							<span style={{marginLeft:6,display:username ? "inline-block" : "none"}} onClick={this.handleOut.bind(this)}>【退出】</span>
						</div>
	            	</div>
	            </header>

	            {/*--------------导航条-------------------*/}
	           <nav>
	           		<div className="content" style={{background:"transparent",color:"white"}}>
	           			<Menu mode="horizontal" selectedKeys={[this.state.current]}>	           				
	           				<SubMenu style={{width:"180px"}} title={<span><Icon type="bars" style={{fontSize:'16px'}}/>总目录</span>} key="contact">
	           					{items.map((item,i) => (
				            			<Menu.Item style={{color:"black"}} key={`setting:${++i}`}><Link to={{pathname:'/list',state:item}}>{item.name}</Link></Menu.Item>
	           						)
	           					)}
					        </SubMenu>
	           				{
		            			navList.map((navli,i) => {
		            				let background;
									if(path.trim() === '/' && navli.path.trim() === '/') {
		            					background = '#6a6355';
		            				}else{
		            					if(navli.path.trim() === '/') {
											background = '#8c8475';
		            					}else{
											if(path.includes('/classify')){
												background = navli.path=='classify'?"#6a6355":"#8c8475";
											}else{
												background = path.includes(navli.path)?"#6a6355":"#8c8475";
											}
		            						{/* background = path.includes(navli.path)?"#6a6355":"#8c8475"; */}
		            					}
		            				}
		            				return <Menu.Item className="menuItem" style={{background:background}} key={i}><Link to={navli.path}>{navli.name}</Link></Menu.Item>
		            			})
	           				}
	           			</Menu>
	           		</div>
	           </nav>
			   <Modal title="Title"
					visible={visible}
					onCancel={this.handleCancel}
					title = ""
					footer = {null}
					wrapClassName="vertical-center-modal"
					maskClosable = {false}
					>
					<div className="modal-container" style={{height: formtype == "login" ? 462 : 538}}>
						<img src={require("assets/images/modal-logo.png")} alt="时代昆曲" />
						<div className="title">时代昆曲<span style={{color:formtype=='login' ? '#666666':'#ef455a'}}>{formtype=="login" ? "登录" : "注册"}</span></div>
						<LoginForm type={formtype} parentCallback={this.handleCallback} isClose={visible} parentCloseCallback={this.handleCancel}/>
					</div>
				</Modal>
			</div>
		)
	}
}

export default Header;