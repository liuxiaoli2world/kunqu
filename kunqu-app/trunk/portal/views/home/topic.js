import React, {
    Component
} from 'react';
import {
    Link,
    hashHistory
} from "react-router";

class Topic extends Component {
    componentDidMount() {
        window.scrollTo(0,0);
    }
    render() {
        const id = this.props.params.id;

        return ( 
            <div>
                <div className = "container" >
                    <div className = "crumb" >
                        当前位置： <Link to = "/drama"> 剧典 </Link><span>></span> 剧典推荐 
                    </div> 
                </div> 
                {id == 1 &&
                    <div className="collection">
                        <img src={require('assets/images/topic1.png')} alt="" width="1534" height="552" useMap="#Map"/>
                        <map name="Map">
                            <area style={{outline:0}} shape="rect" coords="2,0,314,42" href="#/opera/detail/102" />
                            <area style={{outline:0}} shape="rect" coords="1,48,314,92" href="#/opera/detail/101" />
                            <area style={{outline:0}} shape="rect" coords="1,98,315,151" href="#/opera/detail/100" />
                            <area style={{outline:0}} shape="rect" coords="244,245,480,289" href="#/drama/detail/85" />
                            <area style={{outline:0}} shape="rect" coords="244,292,481,347" href="#/drama/detail/14" />
                            <area style={{outline:0}} shape="rect" coords="244,351,481,399" href="#/drama/detail/140" />
                            <area style={{outline:0}} shape="rect" coords="244,401,481,455" href="#/drama/detail/19" />
                            <area style={{outline:0}} shape="rect" coords="244,458,481,508" href="#/drama/detail/28" />
                            <area style={{outline:0}} shape="rect" coords="245,510,481,557" href="#/drama/detail/42" />
                            <area style={{outline:0}} shape="rect" coords="1116,-4,1438,44" href="#/article/detail/101" />
                            <area style={{outline:0}} shape="rect" coords="1116,48,1439,93" href="#/article/detail/112" />
                            <area style={{outline:0}} shape="rect" coords="1117,97,1440,145" href="#/article/detail/114" />
                            <area style={{outline:0}} shape="rect" coords="1210,254,1534,304" href="#/article/detail/114" />
                            <area style={{outline:0}} shape="rect" coords="1210,306,1533,357" href="#/drama/detail/85" />
                            <area style={{outline:0}} shape="rect" coords="1210,360,1535,416" href="#/drama/detail/268" />
                            <area style={{outline:0}} shape="rect" coords="1211,419,1533,472" href="#/article/detail/101" />
                            <area style={{outline:0}} shape="rect" coords="1212,476,1532,522" href="#/opera/detail/100" />
                        </map>
                    </div> 
                }
                {id == 2 &&
                    <div className="collection">
                        <img src={require('assets/images/topic2.png')} alt="" width="1183" height="478" useMap="#Map"/>
                        <map name="Map">
                            <area style={{outline:0}} shape="rect" coords="952,202,1182,243" href="#/drama/detail/52" />
                            <area style={{outline:0}} shape="rect" coords="952,253,1182,294" href="#/drama/detail/53" />
                            <area style={{outline:0}} shape="rect" coords="952,302,1182,343" href="#/drama/detail/188" />
                            <area style={{outline:0}} shape="rect" coords="952,353,1182,394" href="#/drama/detail/191" />
                            <area style={{outline:0}} shape="rect" coords="952,404,1182,445" href="#/drama/detail/192" />
                            <area style={{outline:0}} shape="rect" coords="0,438,230,479" href="#/drama/detail/86" />
                            <area style={{outline:0}} shape="rect" coords="0,389,230,430" href="#/drama/detail/91" />
                            <area style={{outline:0}} shape="rect" coords="1,337,231,378" href="#/drama/detail/61" />
                            <area style={{outline:0}} shape="rect" coords="0,290,230,331" href="#/drama/detail/53" />
                            <area style={{outline:0}} shape="rect" coords="0,239,230,280" href="#/drama/detail/52" />
                        </map>
                    </div> 
                }
                {id == 3 &&
                    <div className="collection">
                        <img src={require('assets/images/topic3.png')} alt="" width="1550" height="672" useMap="#Map"/>
                        <map name="Map">
                            <area style={{outline:0}} shape="rect" coords="1241,395,1548,436" href="#/article/detail/127" />
                            <area style={{outline:0}} shape="rect" coords="1241,446,1548,487" href="#/drama/detail/236" />
                            <area style={{outline:0}} shape="rect" coords="1241,500,1548,541" href="#/drama/detail/244" />
                            <area style={{outline:0}} shape="rect" coords="1241,551,1548,592" href="#/article/detail/128" />
                            <area style={{outline:0}} shape="rect" coords="1241,600,1548,641" href="#/opera/detail/107" />
                            <area style={{outline:0}} shape="rect" coords="1141,1,1386,33" href="#/article/detail/102" />
                            <area style={{outline:0}} shape="rect" coords="1141,54,1386,86" href="#/article/detail/168" />
                            <area style={{outline:0}} shape="rect" coords="1141,100,1386,132" href="#/article/detail/169" />
                            <area style={{outline:0}} shape="rect" coords="1141,153,1386,185" href="#/article/detail/129" />
                            <area style={{outline:0}} shape="rect" coords="1141,205,1386,237" href="#/article/detail/150" />
                            <area style={{outline:0}} shape="rect" coords="1141,253,1386,285" href="#/article/detail/161" />
                            <area style={{outline:0}} shape="rect" coords="340,386,508,424" href="#/drama/detail/72" />
                            <area style={{outline:0}} shape="rect" coords="340,438,508,476" href="#/drama/detail/80" />
                            <area style={{outline:0}} shape="rect" coords="340,485,508,523" href="#/drama/detail/75" />
                            <area style={{outline:0}} shape="rect" coords="340,536,508,574" href="#/drama/detail/74" />
                            <area style={{outline:0}} shape="rect" coords="340,590,508,628" href="#/drama/detail/77" />
                            <area style={{outline:0}} shape="rect" coords="340,637,508,675" href="#/drama/detail/78" />
                            <area style={{outline:0}} shape="rect" coords="-1,53,327,91" href="#/opera/detail/110" />
                            <area style={{outline:0}} shape="rect" coords="-1,102,327,140" href="#/opera/detail/109" />
                            <area style={{outline:0}} shape="rect" coords="-1,153,327,191" href="#/opera/detail/108" />
                            <area style={{outline:0}} shape="rect" coords="0,204,328,242" href="#/opera/detail/106" />
                            <area style={{outline:0}} shape="rect" coords="0,251,328,289" href="#/opera/detail/107" />
                        </map>
                    </div>
                }
                {id == 4 &&
                    <div className="collection">
                        <img src={require('assets/images/topic4.png')}  alt="" width="1265" height="478" useMap="#Map"/>
                        <map name="Map">
                            <area style={{outline:0}} shape="rect" coords="0,235,260,280" href="#/drama/detail/89" />
                            <area style={{outline:0}} shape="rect" coords="1,288,261,333" href="#/drama/detail/90" />
                            <area style={{outline:0}} shape="rect" coords="0,338,260,383" href="#/drama/detail/87" />
                            <area style={{outline:0}} shape="rect" coords="0,388,260,433" href="#/drama/detail/275" />
                            <area style={{outline:0}} shape="rect" coords="0,438,260,483" href="#/drama/detail/281" />
                            <area style={{outline:0}} shape="rect" coords="1004,194,1264,239" href="#/drama/detail/89" />
                            <area style={{outline:0}} shape="rect" coords="1004,246,1264,291" href="#/drama/detail/90" />
                            <area style={{outline:0}} shape="rect" coords="1004,297,1264,342" href="#/drama/detail/87" />
                            <area style={{outline:0}} shape="rect" coords="1004,347,1264,392" href="#/drama/detail/167" />
                            <area style={{outline:0}} shape="rect" coords="1004,400,1264,445" href="#/drama/detail/160" />
                        </map>    
                    </div>
                }
            </div>
        );
    }
}

export default Topic;