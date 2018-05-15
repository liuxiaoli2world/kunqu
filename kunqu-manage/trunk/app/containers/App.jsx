
import React, { Component } from 'react'
import { Link } from 'react-router'
import { Menu, Layout, Icon } from 'antd'
// import 'antd/dist/antd.css'
import 'normalize.css'
import '../common/reset.css'
import config from '../../platforms/common/config/default'

const { Content, Footer, Sider, Header } = Layout

class App extends Component {
  constructor() {
    super()
    this.state = {
      selectedKey: 'home'
    }
  }

  componentDidMount() {

  }

  render() {
    let path = typeof window !== "undefined" ? window.location.pathname : "";
    let selectedKey = (path == '/ordermanage') ? 'resource' : 'home';
    switch (path) {
      case `${config.contextPath}resource`:
        selectedKey = 'resource';
        break;
      case `${config.contextPath}knowledge`:
        selectedKey = 'knowledge';
        break;
      case `${config.contextPath}user`:
        selectedKey = 'user';
        break;
      case `${config.contextPath}data`:
        selectedKey = 'data';
        break;
      default:
        selectedKey = 'home';
        break;

    }
    return (
      <Layout className="ant-layout-has-sider">
        <Sider
          trigger={null}
          breakpoint="lg"
          width={230}
        >
          <div className="logo">
            <img src={require("../Images/logo.png")} alt="log" />
          </div>
          <Menu
            onClick={this.menuClick}
            selectedKeys={[selectedKey]}
          >
            <Menu.Item key="home">
              <Link to={config.contextPath}><span className="nav-text">首页</span></Link>
            </Menu.Item>
            <Menu.Item key="resource">
              <Link to={config.contextPath+'resource'}><span className="nav-text">资源管理</span></Link>
            </Menu.Item>
            <Menu.Item key="knowledge">
              <Link to={config.contextPath+'knowledge'}><span className="nav-text">知识服务</span></Link>
            </Menu.Item>
            <Menu.Item key="user">
              <Link to={config.contextPath+'user'}><span className="nav-text">用户管理</span></Link>
            </Menu.Item>
            <Menu.Item key="data">
              <Link to={config.contextPath+'data'}><span className="nav-text">数据分析</span></Link>
            </Menu.Item>

          </Menu>
        </Sider>
        <Layout>
          <Header style={{ background: '#5a6378', color: 'white', fontSize: '14px', paddingLeft: '40px', height: 80, lineHeight: '80px' }}>
            <img className="logo-title" src={require("../Images/logo-title.png")} alt="昆曲源管理系统" />
            {/* <div style={{ float: 'right' }}> */}
            {/* <img src={require("assets/images/user-top.png")} style={{ verticalAlign: 'middle', marginRight: '8px' }} /> */}
            {/* <span onClick={this.quit} style={{ marginLeft: '8px', cursor: 'pointer' }} >退出</span> */}
            {/* </div> */}
          </Header>
          <Content style={{ background: '#fff' }}>
            {this.props.children}
          </Content>
        </Layout>
      </Layout>
    )
  }
}

export default App;