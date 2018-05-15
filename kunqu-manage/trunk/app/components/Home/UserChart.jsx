import React, { Component } from 'react';
import PropTypes from 'prop-types';

var echarts = require('echarts/lib/echarts');
//var zrender = require('zrender');
require('echarts/lib/chart/line');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

class UserChart extends Component {
    constructor(props){
        super(props);
    }

    componentDidMount() {
		
        this.drawRightChart(this.props.options.dataX,this.props.options.dataY);
        // this.drawRightChart(['10月27日','10月28日','10月29日','10月30日','10月31日','11月1日','11月2日'],[10,15,20,22,25,32,40])
    }
	
	componentWillReceiveProps(nextProps) {
		if(nextProps.options != this.props.options){
			this.drawRightChart(nextProps.options.dataX,nextProps.options.dataY);
		}
	}
	
	
    componentWillUnmount() {
        window.removeEventListener('resize',true);
    }
    
    drawRightChart(dataX, dataY) {
		let rightChart = echarts.init(document.getElementById('userChart'));
		window.addEventListener('resize', rightChart.resize);
				// 绘制图表
		rightChart.setOption({
					// title: {
					// 	text: '折线图堆叠'
					// },
			backgroundColor: 'white',
			tooltip: {
				trigger: 'axis'
			},
			legend: {
				data: ['一周用户增长量'],
				show: true
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '2%',
				containLabel: true,
				show: true,
				backgroundColor: '#393f4f'
			},
			toolbox: {
				feature: {
					saveAsImage: {}
				}
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				// data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
				data: dataX,
				axisLine: {
					lineStyle: {
						color: '#717580'
					}
				},
				splitLine: {
					show: true,
					lineStyle: {
						color: "rgba(255,255,255,0.2)"
					}
				}
			},
			yAxis: {
				type: 'value',
				axisLine: {
					lineStyle: {
						color: '#717580'
					}
				},
				splitLine: {
					show: true,
					lineStyle: {
						color: "rgba(255,255,255,0.2)"
					}
				},
				axisLabel : {
					formatter : function(value,index){
						if(!Number.isInteger(value)){
							return "";
						}else{
							return value;
						}
					}
				}
			},
			series: [
				{
					name: '一周粉丝增长量',
					type: 'line',
					stack: '总量',
							// data: [1, 0, 0, 0, 0, 0,2],
					data: dataY,
					backgroundColor: '#393f4f',
					smooth: true,
					symbolSize: 4,
					itemStyle: {
						normal: {
							areaStyle: {
								// 区域图，纵向渐变填充
								color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
											offset: 0,
											color: '#5eade9'
										}, {
											offset: 1,
											color: '#c37dd3'
										}])
							},
							color: '#717580'
						}
					}
				}
			]
		});
    }
    
    render() {
        return (
            <div id="userChart" className="right-chart"></div>
        );
    }
}

UserChart.propTypes = {
    options: PropTypes.object
};

export default UserChart;