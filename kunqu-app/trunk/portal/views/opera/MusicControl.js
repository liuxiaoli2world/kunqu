import React from "react";
import ReactDOM from 'react-dom'; 
import config from "config";
import {Link,hashHistory} from "react-router";
import {Carousel,Tabs,Menu,Slider,Icon} from "antd";
import { autobind } from 'core-decorators';
import {resizeEvent} from 'util/carousel-helper';
import {get,post} from "util/request.js";

const TabPane = Tabs.TabPane;
const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

	
export default class Player extends React.Component{
	//初始化状态
	
	constructor(props){
		super(props);	
		this.state = {
			currentTrackLen: this.props.operaLen, //歌单歌曲数
		    currentTrackIndex: this.props.curIndex, //当前播放的歌曲索引，默认加载第一首歌
		    currentTime: 0, //当前歌曲播放的时间
		    currentTotalTime: 0, //当前歌曲的总时间
		    playStatus: true, //true为播放状态，false为暂停状态
			volumeValue: 20
		}	
	}
	//更新播放状态
	updatePlayStatus(){
		let audio = document.getElementById('audio');
		if(this.state.playStatus){
            audio.play();
			$(".audio-btn").attr("src",require('assets/images/audio-pause.png'))
        }else{
            audio.pause();
			$(".audio-btn").attr("src",require('assets/images/audio-play.png'))
        }
        //更新当前歌曲总时间
        //this.setState({currentTotalTime: this.props.tracks[this.state.currentTrackIndex].duration / 1000});
		this.setCurrentTotalTime();
	}
	setCurrentTotalTime(){
			let audio = document.getElementById('audio');
			let audioDuration = audio.duration;
			if(isNaN(audioDuration)){
				audioDuration = 0;
			}
			this.setState({
				currentTotalTime:audioDuration 
			})
		}
	//播放事件处理
	play = () =>{
		//这里有setState是异步的，需要在回调中执行
		this.setState({playStatus:!this.state.playStatus}, ()=>{
			this.updatePlayStatus();
		});
	}

	//上一曲事件处理
	previous = () =>{
		if(this.state.currentTrackIndex - 1 < 0){
            this.setState({currentTrackIndex:this.state.currentTrackLen-1},()=>{
            	this.updatePlayStatus();
            });
        }else{
            this.setState({currentTrackIndex:--this.state.currentTrackIndex},()=>{
            	this.updatePlayStatus();
            });
        }        
	}

	//下一曲事件处理
	next = () =>{
		if(this.state.currentTrackIndex + 1 >=  this.props.tracks&&this.props.tracks.length){
			this.setState({currentTrackIndex:0},()=>{
            	this.updatePlayStatus();
            });
        }else{
			 this.setState({currentTrackIndex:++this.state.currentTrackIndex},()=>{
            this.updatePlayStatus();
            });            
        }
	}
	//DOM加载完
	componentDidMount(){	
		
		// this.updatePlayStatus();
		this.timer = setInterval(()=>{
			let audio = document.getElementById('audio');
			this.setState({currentTime:audio&&audio.currentTime,currentTotalTime:audio&&audio.duration},()=>{
				var that = this;
				if(~~this.state.currentTime == ~~this.state.currentTotalTime){
					//that.next()
		        }
			});
		}, 300);
		this.setCurrentVolume()
	}
	preventDefaultImageDrag(){
		document.getElementsByTagName('img')[0].onmousedown = function(e){  
			e.preventDefault()  
		};  
	}
	onChildChanged = (index) => {
		this.setState({currentTrackIndex:index},()=>{
            this.updatePlayStatus();
        });
	}
	handleChange = (v) => {
		this.setState({      
		  volumeValue: v,
		});
		this.setCurrentVolume()
	}
	setCurrentVolume(){
		$("#audio").get(0).volume = this.state.volumeValue/100
	}
	componentWillUnmount () {
    	clearInterval(this.timer)
  	}
	render() {		
		
		return (
			<div className="container2">
				{/* 播放器名称  */}
				<div className="overflow-hidden music-cont container2">
					<OperaDesc className="fl" specialName={this.props.specialName} specialDesc={this.props.specialDesc} source={this.props.source}/>
					<OperaTable className="fl" operaList={this.props.tracks} callbackParent={this.onChildChanged} currentTrackIndex={this.state.currentTrackIndex}/>
					<OperaLyric className="fl" songName={this.props.tracks&&this.props.tracks.length&&this.props.tracks[this.state.currentTrackIndex].operaName} singer={this.props.tracks&&this.props.tracks.length&&this.props.tracks[this.state.currentTrackIndex].singer}/>
				</div>			

				<div className="audio-cont">
					<div className="container2">
						{/* 播放进度条  */}
						<Controls isPlay={this.state.playStatus} onPlay={this.play} onPrevious={this.previous} onNext={this.next} />
						<Progress progress={this.state.currentTime / this.state.currentTotalTime * 100 + '%'} songName={this.props.tracks&&this.props.tracks.length&&this.props.tracks[this.state.currentTrackIndex].operaName}/>
						{/* 播放时间   */}
						<Time currentTime={this.state.currentTime} currentTotalTime={this.state.currentTotalTime} className="time"/>
						{/*音量大小调节*/}
						<div className = "volume-div">
							<img src={require('assets/images/volume-icon.png')} className="volume-icon"/>
							<div className="volume-progress"><Slider {...this.props} onChange={this.handleChange} value={this.state.volumeValue} /></div>				
						</div>
						{/* 音频控件  */}
						<audio id="audio" src={this.props.tracks&&this.props.tracks.length&&this.props.tracks[this.state.currentTrackIndex].songUrl} autoPlay="true"></audio>
					</div>
				</div>
			</div>
		);
	}
};

class TrackInfo extends React.Component{

	render() {
		return(
		<div>
		<div className="albumPic" style={{'backgroundImage':'url('+ this.props.track.album.picUrl +')'}}></div>
		<div className='trackInfo'>
 			<div className="name">{this.props.track.name}</div>
            <div className="artist">{this.props.track.artists[0].name}</div>
            <div className="album">{this.props.track.album.name}</div>			
        </div>
        </div>
		);
	}
};

class Progress extends React.Component{
	render(){
		return 	(
			<div className="progress-div fl overflow-hidden">
				<img src={require('assets/images/audio-head.png')} className="audio-img fl"/>
				<div className="song-name">{this.props.songName}</div>
				<div className="progress-cont">
					<div className="progress fl" style={{'width':this.props.progress}}></div>
					<img src={require('assets/images/audio-progress-icon.png')} className="audio-progress-icon" style={{'left':this.props.progress}}/>
				</div>
			</div>
		)
	}
};

class Controls extends React.Component{

	render(){
		let className;
		if(this.props.isPlay == true){
			className = 'icon-pause';
		}else{
			className = 'icon-play';
		}
		return (
			<div className="btns fl overflow-hidden controls ">
				<img src={require('assets/images/audio-prev.png')} className="audio-prev fl icon-previous" onClick={this.props.onPrevious}/>
				<img src={require('assets/images/audio-pause.png')} className="audio-btn fl play" onClick={this.props.onPlay}/>
				<img src={require('assets/images/audio-next.png')} className="audio-next fl icon-next" onClick={this.props.onNext}/>
			</div>		
		)
	}
};
class Volume extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
		  sliderValue: this.props.value,
		};
	}
	handleChange = (v) => {
		this.setState({      
		  sliderValue: v,
		});
		this.props.callbackParent(v)
	}
	render(){
		return(
			<div className = "volume-div">
				<img src={require('assets/images/volume-icon.png')} className="volume-icon"/>
				<div className="volume-progress"><Slider {...this.props} onChange={this.handleChange} value={this.state.sliderValue} /></div>				
			</div>
		)		
	}
}
class Time extends React.Component{
	timeConvert = (timestamp) => {
	    var minutes = Math.floor(timestamp / 60);
	    var seconds = Math.floor(timestamp - (minutes * 60));

	    if(seconds < 10) {
	      seconds = '0' + seconds;
	    }
		if(isNaN(timestamp)){
			return "00:00"
		}
	    timestamp = minutes + ':' + seconds;
	    return timestamp;
	}
	render() {
		return(
      	<div className="time">
            <div className="current">{this.timeConvert(this.props.currentTime)}</div>/
            <div className="total">{this.timeConvert(this.props.currentTotalTime)}</div>
        </div>			
		);
	}
};

class OperaDesc extends React.Component{
	render(){
		return(
			<div className="opera-desc fl">
				<img src={require('assets/images/opera-img.png')} className="opera-img"/>
				<div className="opera-title">
					{this.props.specialName}
				</div>
				<div className="opera-extra-info">
					{this.props.source}
				</div>
				<div className="opera-cont">							
					{this.props.specialDesc}
				</div>
				{/*<div className="see-detail-btn">
					<Link to={{pathname:`/list/${this.props.dirId}`}}>浏览详情 ></Link>
				</div>*/}
			</div>
		)
	}
}

class OperaLyric extends React.Component{

	render(){
		return(
			<div className="lyric fl">
				<div className="lyric fl">
				<img src={require('assets/images/lyric-img.png')} className="lyric-img"/>
				<div className="lyric-title">
					{`《${this.props.songName}》`}
				</div>
				<div className="lyric-extra-info">
					{/*`演唱：${this.props.singer}`*/}
				</div>
				<div className="opera-cont">							
					暂无歌词
				</div>
			</div>
			</div>
		)
	}
}

class OperaTable extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
		  currentTrackIndex: this.props.currentTrackIndex,
		};
	}
	handleClick = (index,event) => {
		this.props.callbackParent(index);
		this.setState({
			currentTrackIndex : index
		})
	}
	componentWillReceiveProps(){
		this.setState({
			currentTrackIndex : this.props.currentTrackIndex
		})
	}
	renderPlayIcon(i,that){
		if(i == that.state.currentTrackIndex){			
			return (<img src={require('assets/images/gif.gif')} className="playing-index"/>)
		}else{
			return i + 1;
		}
	}
	renderClassName(i,that){
		if(i == that.state.currentTrackIndex){			
			return "current"
		}else{
			return "";
		}
	}
	render(){
		var that = this;
		if(this.props.operaList&&this.props.operaList.length!=0){
			var operaList = this.props.operaList.map(function(item,index){
				let cur = index + 1;
				return(
					<tr key={index} onClick={that.handleClick.bind(that,index)} className = {that.renderClassName(index,that)}>
						<td style={{textAlign:"center"}}>{that.renderPlayIcon(index,that)}</td>
						<td>{item.operaName}</td>
						<td>{item.singer}</td>
					</tr>
				)
			})
		}
		return(
			<div className="opera-table fl">
				<table className="">
					<thead>
						<tr>
							<th width="36px" style={{textAlign:"center"}}></th>
							<th width="374px">节目</th>
							<th width="75px">来源</th>
						</tr>
					</thead>
					<tbody>
						{operaList}
					</tbody>
				</table>
			</div>
		)
	}
}
