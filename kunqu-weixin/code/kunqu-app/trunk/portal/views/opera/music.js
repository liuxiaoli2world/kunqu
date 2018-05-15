import React from 'react';
import MusicButton from './MusicButton';
import {get,post} from "util/request.js";
import "./detail.scss";
import {SubEmiter,Emiter} from 'util/index.js';
export default class Music extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            index : 0,
            surplusTime : '0:00',
            operaList: [],
            isPlay: true,
            cover:''
        };
    }

    isPlay(param) {
        const rota = this.refs.rotation;
        if(param.isPlay){
            rota.style.animationPlayState = 'paused';
        }else{
            rota.style.animationPlayState = 'running';
        }
        this.setState({
            isPlay: param.isPlay
        })
    }

    componentDidMount(){
        const rota = this.refs.rotation;
        rota.style.animationPlayState = 'paused';
        get(`kunqu/special/query/${this.props.params.id}`)
          .then((data) => {
            if(data.meta.success){
                this.setStorage();
                const index = data.data.imageList.findIndex((val) => val.imageScene=='wx_01');
                rota.style.background = 'data.data.imageList[index].imageUrl';
                rota.style.backgroundSize = '100%';
                this.setState({
                    operaList: data.data.operaList,
                    cover: data.data.imageList[index].imageUrl,
                    desc: data.data.specialDesc
                })
            }
        })
    }

    setStorage() {
        const id = this.props.params.id;
        if(localStorage.special){
            let arr = localStorage.getItem('special');
            let special = [
                id,
                ...arr.split(',')
            ];
            let s = new Set(special);
            special = [...s].slice(0,12).join(',');
            localStorage.setItem('special',special)
        }else{
            localStorage.setItem('special',id);
        }
    }
    render () {
        const {operaList,index,surplusTime,cover,desc,isPlay} = this.state;
        const height = document.body.clientHeight;
        return (
            <article className="music" style={{height:height,background:'#eaeaea'}}>
                <SubEmiter eventName="isPlaying" listener= {this.isPlay.bind(this)}></SubEmiter>
                <article className="musicContent">
                    <div className="cover" ref='cover'>
                        <div ref='rotation' className={`imgBox animate`} style={cover ? {background:`url(${cover})`,backgroundSize: 'auto 100%'} : {}}>
                        {/*<img src={cover} alt="" className={`${isPlay?'Rotation':'Rotation-run'}`}/>*/}
                        </div>
                    </div>
                    {operaList.length>0 ? <MusicButton data={operaList} desc={desc} /> : null}
                </article>
            </article>
        );
    }
}