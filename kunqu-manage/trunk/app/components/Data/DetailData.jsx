import React, { Component } from 'react';
import PropTypes from 'prop-types';
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

class DetailData extends Component {
    constructor(props){
        super(props)
    }

    handleDrawLeft = () => {
        let leftChart = echarts.init(document.getElementById(`leftChart${this.props.index}`));
        window.addEventListener('resize', leftChart.resize);
        this.setChartOption(leftChart,this.props.leftOptions);
    }

    handleDrawRight = () => {
        let rightChart = echarts.init(document.getElementById(`rightChart${this.props.index}`));
        window.addEventListener('resize', rightChart.resize);
        this.setChartOption(rightChart,this.props.rightOptions);
    }

    setChartOption = (chart,options) => {
        chart.setOption({
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '5%',
                bottom: '4%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine: option.axisLine,
                splitLine: option.splitLine,
                axisLabel: option.axisLabel,
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                axisLine: option.axisLine,
                splitLine: option.splitLine,
                axisLabel: option.axisLabel,
                data: options.dataX
            },
            series: [
                {
                    type: 'bar',
                    barWidth: 10,
                    color: [options.color],
                    data: options.dataY
                }
            ]
        })
    }
    
    componentDidMount() {
        this.handleDrawLeft()
        this.handleDrawRight()
    }

    // componentWillReceiveProps(nextProps) {
    //     this.handleDrawLeft()
    //     this.handleDrawRight()
    // }
    
    componentWillUnmount() {
        window.removeEventListener('resize',true);
    }

    render() {
        return (
            <div className="video clearfix">
                <div className="left-chart left">
                    <div className="clickTitle">点击量排行TOP5</div>
                    <div id={`leftChart${this.props.index}`} className="left-part"></div>
                </div>
                <div className="right-chart left">
                    <div className="commentTitle">评论排行TOP5</div>
                    <div id={`rightChart${this.props.index}`} className="right-part"></div>
                </div>
            </div>
        );
    }
}

DetailData.propTypes = {
    index: PropTypes.number,
    leftOptions: PropTypes.object,
    rightOptions: PropTypes.object
};

export default DetailData;