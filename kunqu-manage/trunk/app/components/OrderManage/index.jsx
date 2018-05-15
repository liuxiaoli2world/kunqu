import React, { Component } from "react"
import { Link } from "react-router"
import { Tabs, Select, DatePicker, Button, Input, Pagination } from 'antd'
import './style.less'
import { get } from '../../utils/request.js'
import moment from 'moment'

const TabPane = Tabs.TabPane
const Option = Select.Option
const { RangePicker } = DatePicker;
const dateFormat = 'YYYY/MM/DD'

class OrderManage extends Component {
    constructor(props) {
        super(props)
        this.state = {
            orderList: []
        }
    }
    componentDidMount() {
        this.getData()        
    }
    getData(params = '&pageNum=1') {
        const url = `order/order/queryBy?pageSize=10${params}`;
        get(url)
            .then((res) => {
                if (res.meta.success) {
                    this.setState({
                        orderList: res.data.list || [],
                        total: res.data.total,
                        currentPage: res.data.pageNum
                    })
                }
            })
    }
    orderTypeChange = (e) =>{
            this.setState({
                payStatus : e
            })

    }

    goodTypeChange = (e) => {
            this.setState({
                goodsTypeCode: e
            })
    }
    
    onChange = (date,dateString) => {
        this.setState({
            startDate : `${dateString[0]}`,
            endDate : `${dateString[1]}`
        })
    }

    handleChange = (e) => {
        this.setState({
            condition : e.target.value
        })
    } 

    onPageChange = (pageNum) => {
        this.queryBtnClick(event, pageNum)
    }

    queryBtnClick = (event, pageNum = '1') => {
        let url = '';
        const {payStatus,goodsTypeCode,condition,startDate,endDate} = this.state;
        const params = {payStatus,goodsTypeCode,condition,startDate,endDate};
        for (var key in params) {
            if(params[key]){
                url += `&${key}=${params[key]}`
            }
        }
        console.log(pageNum.target)
        this.getData(`${url}&pageNum=${pageNum}`)
    }

    render() {
        return (
            <div className='tab-content'>
                <div className='head'>
                    <span className='label-name'>订单状态：</span>
                    <Select size='large' className='select order-type' style={{ width: 88 }} onChange={this.orderTypeChange}>
                        <Option value=''>全部</Option>
                        <Option value='0'>待支付</Option>
                        <Option value='1'>已完成</Option>
                    </Select>
                    <span className='label-name'>商品类型：</span>
                    <Select size='large' className='select order-type' style={{ width: 88 }} onChange={this.goodTypeChange}>
                        <Option value=''>全部</Option>
                        <Option value='ARTICLE'>文章</Option>
                        <Option value='AUDIO'>音频</Option>
                        <Option value='VIDEO'>视频</Option>
                        <Option value='PDF'>PDF</Option>
                    </Select>
                    <span className='label-name'>收货人/订单号：</span>
                    <Input size='large' className="order-name" onChange={this.handleChange} style={{ width: 200 }} />
                    <span className='label-name'>下单时间：</span>
                    <RangePicker size='large' onChange={this.onChange} />
                    <Button size='large' type='primary' onClick={this.queryBtnClick} className="query-btn">查询</Button>
                </div>
                {
                    this.state.orderList.length > 0 ?
                        <div>
                            <div className='table'>
                                <div className='table-head'>
                                    <span className='column1'>商品详情</span>
                                    <span className='column2'>商品类型</span>
                                    <span className='column3'>订单金额（元）</span>
                                    <span className='column4'>状态</span>
                                    <span className='column5'>购买用户</span>
                                </div>
                                {
                                    this.state.orderList.map((item, index) => (
                                        <div key={index} className='table-body-item'>
                                            <div className='table-body-item-row1'>
                                                <span className='row1-column1'>订单编号：{item.orderNo}</span>
                                                <span className='row1-column2'>创建时间：{item.createdAt}</span>
                                            </div>
                                            <div className='table-body-item-row2'>
                                                <div className='row2 column1 clearfix'>
                                                    <img className='row2-img left' src={item.coverUrl} />
                                                    <span className="goodName left">{item.goodsName}</span>
                                                </div>
                                                <div className='row2 column2'>{item.goodsTypeCode == 'ARTICLE' ? '文章' : item.goodsTypeCode == 'PDF' ? 'PDF' : item.goodsTypeCode == 'VIDEO' ? '视频' : '音频'}</div>
                                                <div className='row2 column3'>{item.amount}</div>
                                                <div className='row2 column4'>{item.payStatus == 0 ? "待支付" : "已完成"}</div>
                                                <div className='row2 column5'><img className="head_img" src={item.headimgurl} />{item.customerName}</div>
                                            </div>
                                        </div>
                                    ))
                                }
                            </div>
                            <Pagination className="pagenation" showQuickJumper current={this.state.currentPage} total={this.state.total} pageSize={10} onChange={this.onPageChange} />
                        </div>
                        :
                        <div className="noData">
                            {/* <img src={require('assets/images/no-data.png')} alt="" /> */}
                        </div>
                }

            </div>
        )
    }
}

export default OrderManage;