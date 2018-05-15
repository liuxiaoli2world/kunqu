
import React, { Component } from 'react'
import './header.less'

export default class Header extends Component {
  render () {
    return (<header className="header">
       <img src={require("../../Images/decoration.png")} alt="装饰" />
    </header>)
  }
}