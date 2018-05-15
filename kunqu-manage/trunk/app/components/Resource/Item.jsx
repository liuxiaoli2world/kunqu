import React, { Component } from 'react';
import ItemSwapper from './itemSwapper';
import { Tabs, Button, Input } from 'antd';

/**
 * 
 * 剧典、曲典、文章
 * 单个项目
 * 
 * @class Item
 * @extends {Component}
 */
class Item extends Component {
  render() {
    let { id, title, count, typeName, content, imageUrl, recommend, deleteResorce, modify, panelKey } = this.props;
    return (
      <ItemSwapper>
        <div className="show-content">
          <img className="image" src={imageUrl} />
          <div className="title">{title && title.length > 28 ? title.substr(0, 34) + '...' : title}</div>
          <div className="content">{typeName}简介：{content && content.length > 35 ? content.substr(0, 34) + '...' : content}</div>
          <div className="count">阅读量：{count}</div>
        </div>
        <div className="buttons">
          <Button className="btn" onClick={recommend.bind(this, id)}>相关推荐</Button>
          <Button className="btn" onClick={modify.bind(this, panelKey, id)}>修改</Button>
          <Button className="btn" onClick={deleteResorce.bind(this, panelKey, id)}>删除</Button>
        </div>
      </ItemSwapper>
    );
  }
}

class RecommondItem extends Component {
  render() {
    let { title, content, imageUrl, id, onClick, sourceData, isAbout } = this.props;
    return (
      <ItemSwapper onClick={onClick.bind(this, sourceData, isAbout ? 'n' : 'y')}>
        {isAbout ? <img className="selected-image" src={require('../../Images/item-selected.png')} />
          :
          null
        }
        <div className="show-content">
          <image className="image" src={imageUrl} />
          <div className="title">{title}</div>
          <div className="content">简介：{content}</div>
        </div>
      </ItemSwapper>
    );
  }
}

export default { Item, RecommondItem };