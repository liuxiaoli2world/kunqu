import React, { Component } from 'react'
import HomeCard from './HomeCard'
import UserChart from './UserChart'
import { get } from '../../utils/request'

import Wrapper from './wrapper.js';
// 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts');
//var zrender = require('zrender');
// 引入柱状图
require('echarts/lib/chart/bar');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      videoNum: 0,
      audioNum: 0,
      articleNum: 0,
      dataX: [],
      dataY: []
    };
  }

  componentDidMount() {
    get(`remote/api/kunqu/dir/querySourceCount`)
      .then((res) => {
        if(res&&res.meta&&res.meta.success){
          this.setState({
            videoNum: res.data.find((item) => item.sourceType == 'drama').count-0,
            audioNum: res.data.find((item) => item.sourceType == 'special').count-0,
            articleNum: res.data.find((item) => item.sourceType == 'article').count-0,
          },() => {
            this.drawLeftChart()
          })
        }
      })
      get('remote/api/kunqu/user/queryDaysAgo')
        .then((res) => {
          if(res&&res.meta&&res.meta.success){
            let dataX = [], dataY = [];
            const data = res.data;
            for (let i = data.length-1; i >=0; i--) {
              const item = data[i];
              dataX.push(item.daysAgo);
              dataY.push(item.count-0);
            }
            this.setState({
              dataX,
              dataY
            })
          }
        })
  }

  componentWillUnmount() {
    window.removeEventListener('resize', true);
  }

  drawLeftChart() {
    // 基于准备好的dom，初始化echarts实例
    const {videoNum,audioNum,articleNum} = this.state;
    let leftChart = echarts.init(document.getElementById('leftChart'));
    window.addEventListener('resize', leftChart.resize);
    // 绘制图表
    leftChart.setOption({
      backgroundColor: 'white',
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: ['剧典数量', '曲典数量', '文章数量']
      },
      calculable: true,
      xAxis: [
        {
          type: 'category',
          data: [],
          barGap: '1.5',
          axisLine: {
            lineStyle: {
              color: '#ccc'
            }
          },

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
          axisLabel: {
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
      series: [
        {
          name: '剧典数量',
          type: 'bar',
          data: [videoNum],
          color: ['#fd9dca'],
          barWidth: 50,
          //barGap: '3'
        },
        {
          name: '曲典数量',
          type: 'bar',
          data: [audioNum],
          color: ['#edc58c'],
          barWidth: 50,
          barGap: '1.5'
        },
        {
          name: '文章数量',
          type: 'bar',
          data: [articleNum],
          color: ['#6fe2ec'],
          barWidth: 50,
          //barGap: '3'
        }
      ]
    });
  }

  render() {
    console.log(this.state.dataY)
    return (
      <Wrapper>
        <div className="top">
          <HomeCard count={this.state.videoNum || 0} title='剧典数量' desc='video' imgUrl={require('../../Images/video.png')} />
          <HomeCard count={this.state.audioNum || 0} title='曲典数量' desc='audio' imgUrl={require('../../Images/audio.png')} />
          <HomeCard count={this.state.articleNum || 0} title="文章数量" desc='Article' imgUrl={require('../../Images/article.png')} />
        </div>
        <div style={{ position: "relative" }}>
          <div id="leftChart" className="left-chart"></div>
          <div className="left_legend">
            <span className="pic"></span>剧典数量<span className="audio"></span>曲典数量<span className="video"></span>文章数量
					</div>
        </div>
        <div style={{ position: "relative" }}>
          <UserChart
            options={{
              dataX: this.state.dataX,
              dataY: this.state.dataY
            }}
          />
          <div className="right_legend">
            <span className="progress"></span>一周用户增长量
					</div>
        </div>
      </Wrapper>
    );
  }
}