import React, { Component } from 'react';
import PropTypes from 'prop-types';
import UserChart from '../Home/UserChart';

var echarts = require('echarts/lib/echarts');
//var zrender = require('zrender');
// 引入柱状图
require('echarts/lib/chart/bar');
require('echarts/lib/chart/pie');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

class UserData extends Component {
    componentDidMount() {
        this.drawGenderChart();
        this.drawProvChart();
    }
    
    drawGenderChart(){
        let genderChart = echarts.init(document.getElementById('genderChart'));
        window.addEventListener('resize', genderChart.resize);
        
        genderChart.setOption({
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男','女','其他']
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '70%',
                    center: ['40%', '50%'],
                    data:[
                        {value:335, name:'女'},
                        {value:310, name:'其他'},
                        {value:234, name:'男'}
                    ],
                    label: {
                        normal: {
                            position: 'inner',
                            formatter: '{d}%'
                        }
                    },
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                   
                }
            ],
            color: ['#fc8a87','#faec76','#68dced']
        });
    }

    drawProvChart = () => {
        let provChart = echarts.init(document.getElementById('provChart'));
        window.addEventListener('resize', provChart.resize);
        provChart.setOption({
			backgroundColor: 'white',
			tooltip: {
				trigger: 'axis'
            },
            grid: {
                left: '5%',
                right: '5%',
                top: '15px',
                bottom: '20px',
                containLabel: true,
                show: true
            },
            color: ['#63cff2'],
			xAxis: [
				{
					type: 'category',
					data: ['合肥', '上海', '武汉', '苏州', '北京'],
					axisLine: {
						lineStyle: {
							color: '#ccc'
						}
                    },
                    axisTick: {
                        alignWithLabel: true
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#333'
                        }
                    }
				}
			],
			yAxis: [
				{
					type: 'value',
					axisLine: {
						lineStyle: {
							color: '#ccc'
						}
					},
					splitLine: {
						lineStyle: {
							color: "#f2f2f2"
						}
					},
					axisLabel : {
						formatter : function(value,index){
							if(!Number.isInteger(value)){
								return "";
							}else{
								return value;
							}
                        },
                        textStyle: {
                            color: '#333'
                        }
					}
				}
			],
			series: [
				{
                    name:'地域分布',
                    type:'bar',
                    barWidth: '20',
                    data:[10, 52, 200, 334, 390]
				}
			]
		})
    }

    componentWillUnmount() {
        window.removeEventListener('resize',true);
    }
    
    render() {
        return (
            <div className="userData">
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">用户数量统计</span>
                    </div>
                    <div>
    					<UserChart
                            options={{
							    dataX:['10月27日','10月28日','10月29日','10月30日','10月31日','11月1日','11月2日'],
							    dataY:[10,15,20,22,25,32,40]
						    }}
                        />
				    </div>
                </div>
                <div>
                    <div className="titleBar">
                        <span className="line"></span>
                        <span className="title">用户分析</span>
                    </div>
                    <div className="clearfix">
                        <div className="gender left">
                            <div className="genderTitle">男女比例分布</div>
                            <div id={`genderChart`} className="gender-part"></div>
                            <div className="genderOption">
                                <div>
                                    <span className="male"></span>男
                                </div>
                                <div>
                                    <span className="female"></span>女
                                </div>
                                <div>
                                    <span className="other"></span>其他
                                </div>
                            </div>
                        </div>
                        <div className="province left">
                            <div className="provTitle">地域分布TOP5</div>
                            <div id={`provChart`} className="prov-part"></div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

UserData.propTypes = {

};

export default UserData;