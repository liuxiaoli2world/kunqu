import React from 'react';
import {SubEmiter,Emiter} from 'util/index.js';

export default class MusicButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      song : '',
      isPlaying: 1,
      songIndex: 0,
      timeInfo: '0:00',
      time: 0,
      isTimeupdate: 1,
      per:0
    };
  }

  _Play(audio) {
    Emiter.emit("isPlaying",{"isPlay":false})
    this.setState({'isPlaying': 0}, () => {
      audio.play();
    });
  }
  _Pause(audio) {
    Emiter.emit("isPlaying",{"isPlay":true})
    audio.pause();
    this.setState({'isPlaying': 1});
  }

  _Prev(songLen, cb) { // 这里的回调是因为setState是异步的
    this.setState({'songIndex': ++this.state.songIndex}, cb);
    if(this.state.songIndex > songLen) {
      this.setState({'songIndex': 0}, cb);
    }
  }

  _Next(songLen, cb) {
    this.setState({'songIndex': --this.state.songIndex}, cb);
    if(this.state.songIndex < 0) {
      this.setState({'songIndex': songLen}, cb);
    }
  }

  _currSong(index) {
    this.setState({'song': this.props.data[index].url,'time':0});
  }

  _endedPlay(songLen, cb) {
    if(+this.state.songIndex === songLen) {
      this.setState({'songIndex': 0}, cb);
    } else {
      this.setState({'songIndex': ++this.state.songIndex}, cb);
    }
  }

  onPlayBtn() {
    let audioNode = this.refs.audio;
    if(this.state.isPlaying) {
      this._Play(audioNode);
    } else {
      this._Pause(audioNode);
    }
  }

  onNextBtn() {
    let [audioNode, songLen] = [this.refs.audio, this.props.data.length-1];
    this._Next(songLen, () => {
      this._currSong(this.state.songIndex);
      this._Play(audioNode);
    });
  }

  onPrevBtn() {
    let [audioNode, songLen] = [this.refs.audio, this.props.data.length-1];
    this._Prev(songLen, () => {
      this._currSong(this.state.songIndex);
      this._Play(audioNode);
    });
  }
  
  componentDidMount() {
    const reg = new RegExp("(^|&)sl=([^&]*)(&|$)", "i");
    const duration = this.props.data[this.state.songIndex].songUrl.match(reg)[2];
    this.setState({
      duration: duration
    })
    let [audioNode, songLen] = [this.refs.audio, this.props.data.length-1];
    audioNode.addEventListener('ended', () => {
      this._endedPlay(songLen, () => {
        this._currSong(this.state.songIndex);
        this._Play(audioNode);
      });
    }, false);
    let onTimeupdate = () => {
      let [remainTime, currentTimeMin, currentTimeSec, currentTimeInfo] = [];
      if(!isNaN(audioNode.duration)) {
        currentTimeMin = parseInt(audioNode.currentTime/60);
        currentTimeSec = this.addZero(parseInt(audioNode.currentTime%60));
        currentTimeInfo = currentTimeMin + ':' + currentTimeSec;
        this.setState({'timeInfo': currentTimeInfo,'time':parseInt(audioNode.currentTime)});
      }
    };
    audioNode.addEventListener('timeupdate', onTimeupdate, false);

    this.cleanup = () => {
      audioNode.removeEventListener('timeupdate', onTimeupdate, false);
    };
  }

  componentWillUnmount () {
    this.cleanup();
  }

  addZero(sec) {
    if(sec < 10) {
      return '0'+sec;
    }else{
      return sec
    }
  }
  
  touchMove(e){
    e.preventDefault();
    e.stopPropagation();
    let audioNode = this.refs.audio;
    this._Pause(audioNode);
    const width = this.refs.progress.offsetWidth;
    const pageX = e.changedTouches[0].pageX;
    const per = ((pageX / width - 0.08) > 1 ? 1 : (pageX / width - 0.08) < 0 ? 0 : (pageX / width - 0.08));
    this.setState({
      time : this.state.duration * per
    })
  }

  touchEnd(e) {
    let audioNode = this.refs.audio;
    const width = this.refs.progress.offsetWidth;
    const pageX = e.changedTouches[0].pageX;
    const per = ((pageX / width - 0.08) > 1 ? 0.95 : (pageX / width - 0.08) < 0 ? 0 : (pageX / width - 0.08));
    const time = this.state.duration * per;
    this.setState({
      time : time
    })
    audioNode.currentTime = time;
    this._Play(audioNode);
  }

  render() {
    const {songIndex,time,isPlaying,timeInfo,per} = this.state;
    const {data,desc} = this.props;
    const reg = new RegExp("(^|&)sl=([^&]*)(&|$)", "i");
    const duration = data[songIndex].songUrl.match(reg)[2];
    const durationMin = parseInt((duration-time)/60);
    const durationSec = this.addZero(parseInt((duration-time)%60));
    let percent = (time>=duration) ? '100%' : ((time / duration * 10000) / 100.00 + "%");
    let  durationInfo = (time>=duration) ? `-0:00`:`-${durationMin}:${durationSec}`
    return (
      <article className="musicContent">
        <audio ref="audio" src={data.length>0 ? data[songIndex].songUrl : ''} />
        {this.props.data.length>0 ? 
        <div className="info">
            <div className="title">{data[songIndex].operaName}</div>
            <div className="desc">{desc}</div>
        </div>
        : null}
        <div className="musicHeader">
          <div className="control">
            <div className="time">
              <div className="timeInfo">{timeInfo}</div>
              <div className="durationInfo">{durationInfo}</div>
              <div className="progress" ref="progress">
                <div className="progressw" style={{width:`${percent}`}}>
                  <span className="out" onTouchMove={this.touchMove.bind(this)} onTouchEnd={this.touchEnd.bind(this)}></span>
                  <span className="outin"></span>
                  <span className="in" ></span>
                </div> 
              </div>
              
            </div>
            <div className="buttons">
                <div className="prev button" onClick={this.onNextBtn.bind(this)}>
                    <img src={require('assets/images/prev.png')} alt=""/>
                </div>
                <div className="play button" onClick={this.onPlayBtn.bind(this)}>
                    {isPlaying ?
                        <img src={require('assets/images/play.png')} alt=""/>
                       :<img src={require('assets/images/pause.png')} alt=""/>
                    }
                </div>
                <div className="next button" onClick={this.onPrevBtn.bind(this)}>
                    <img src={require('assets/images/next.png')} alt=""/>
                </div>
            </div>
          </div>  
        </div>
      </article>
    )
  }
}