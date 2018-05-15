import React, { Component } from 'react';
import DetailData from './DetailData';
import { get } from '../../utils/request'

var echarts = require('echarts/lib/echarts');
//var zrender = require('zrender');
// 引入柱状图
require('echarts/lib/chart/bar');
require('echarts/lib/chart/line');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
const option = {
    axisLine: {
        lineStyle: {
            color: '#cfcfcf'
        }
    },
    splitLine: {
        lineStyle: {
            color: "#f0f0f0"
        }
    },
    axisLabel: {
        textStyle: {
            color: '#333'
        }
    }
}

class ResourceData extends Component {
    constructor (props) {
        super(props);
        this.state = {
            detailDatas : {
                drama: {},
                article: {},
                special: {}
            }
        }
    }
    handleDrawResource = (data1,data2) => {
        let resChart = echarts.init(document.getElementById('resChart'));
        window.addEventListener('resize', resChart.resize);

        resChart.setOption({
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '6%',
                containLabel: true,
                show: true
            },
            legend: {
                data: ['浏览量', '用户量']
            },
            xAxis: {
                type: 'category',
                axisLine: option.axisLine,
                splitLine: option.splitLine,
                axisLabel: {
                    textStyle: {
                        color: '#333',
                        fontSize: '14'
                    }
                },
                data: ['剧典', '曲典', '文章']
            },
            yAxis: [
                {
                    type: 'value',
                    axisLine: option.axisLine,
                    splitLine: option.splitLine,
                    axisLabel: {
                        textStyle: option.axisLabel.textStyle,
                        formatter: function (value, index) {
                            if (!Number.isInteger(value)) {
                                return "";
                            } else {
                                return value;
                            }
                        }
                    }
                }
            ],
            series: [{
                // 根据名字对应到相应的系列
                name: '浏览量',
                type: 'bar',
                barWidth: 20,
                barGap: '3.5',
                color: ['#ff87b1'],
                data: data2
            }, {
                name: '用户量',
                type: 'bar',
                barWidth: 20,
                color: ['#ffd65b'],
                data: data1
            }]
        });
    }

    componentDidMount() {
        get(`remote/api/kunqu/dir/analyzeSource`)
            .then((res) => {
                if(res.meta && res.meta.success){
                    this.handleResourceData(res.data.chartList);
                    this.handleTopData('drama',res.data.dramaTopList)
                    this.handleTopData('article',res.data.articleTopList)
                    this.handleTopData('special',res.data.specialTopList)
                }
            })
        
    }

    handleTopData = (type,data) => {
        let{detailDatas} = this.state;
        let clickCounts = [],clickNames= [],commentNames=[],commentCounts = []; 
        const clickData = [...data].sort((a,b) => 
            a[`${type}ClickCount`] - b[`${type}ClickCount`]
            
        );
        const commentData = [...data].sort((a,b) => 
            a[`${type}CommentCount`] - b[`${type}CommentCount`]
        );
        clickData.forEach((item, i) => {
            clickNames.push(item[`${type}Name`])
            clickCounts.push(item[`${type}ClickCount`])
        })
        commentData.forEach((item, i) => {
            commentNames.push(item[`${type}Name`])
            commentCounts.push(item[`${type}CommentCount`])
        })
        const detailData = {clickNames,clickCounts,commentNames,commentCounts}
        detailDatas[type] = detailData;
        this.setState({
             detailDatas
        })
    }

    handleResourceData = (data) => {
        let counts = [], pageViews = [];
        if(data.length>0){
            data.forEach((item,i) => {
                switch(item.sourceType) {
                    case '剧典':
                        counts[0] = item.count;
                        pageViews[0] = item.pageView;
                        break;
                    case '曲典':
                        counts[1] = item.count;
                        pageViews[1] = item.pageView;
                        break;
                    case '文章':
                        counts[2] = item.count;
                        pageViews[2] = item.pageView;
                        break;
                    default:
                        break;
                }
            })
        }
        this.handleDrawResource(counts,pageViews)
    }

    componentWillUnmount() {
        window.removeEventListener('resize', true);
    }

    render() {
        const {detailDatas} = this.state;
        // if(detailDatas.special){console.log(1)}
        return (
            <div>
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">资源统计</span>
                    </div>
                    <div className="resource">
                        <div className="resOption">
                            <span className="view-count"></span>浏览量
                            <span className="user-count"></span>用户量
                        </div>
                        <div id="resChart" className="res-chart"></div>
                    </div>
                </div>
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">剧典排行</span>
                    </div>
                    {detailDatas.drama.clickNames ?
                    <DetailData
                        index={1}
                        leftOptions={{
                            color: '#ffa16d',
                            dataX: detailDatas.drama.clickNames, 
                            dataY: detailDatas.drama.clickCounts
                        }}
                        rightOptions={{
                            color: '#6db5ff',
                            dataX: detailDatas.drama.commentNames, 
                            dataY: detailDatas.drama.commentCounts
                        }}
                    />: null}
                </div>
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">曲典排行</span>
                    </div>
                    {detailDatas.special.clickNames ?
                    <DetailData
                        index={2}
                        leftOptions={{
                            color: '#acee85',
                            dataX: detailDatas.special.clickNames, 
                            dataY: detailDatas.special.clickCounts
                        }}
                        rightOptions={{
                            color: '#ffd16d',
                            dataX: detailDatas.special.commentNames, 
                            dataY: detailDatas.special.commentCounts
                        }}
                    />: null}
                </div>
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">文章排行</span>
                    </div>
                    {detailDatas.article.clickNames ?
                    <DetailData
                        index={3}
                        leftOptions={{
                            color: '#ee7ce5',
                            dataX: detailDatas.article.clickNames, 
                            dataY: detailDatas.article.clickCounts
                        }}
                        rightOptions={{
                            color: '#88d3eb',
                            dataX: detailDatas.article.commentNames, 
                            dataY: detailDatas.article.commentCounts
                        }}
                    />: null}
                </div>
            </div>

        );
    }
}

export default ResourceData;