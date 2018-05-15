import styled from 'styled-components';

const Wrapper = styled.div `    
    width: 100%;
    height: calc(100vh - 80px);
    padding: 56px 95px 0 85px;
    background: #f4f7ff;
    .top {
        height: 300px;
        background: white;
        padding:75px 7.7%;
        margin-bottom: 60px;
        text-align: center;
        .ant-card-body{
            padding: 0;
        }
        .homeCard {
            width: 28%;
            overflow: hidden;
            float: left;
            color: white;
            font-size: 22px;
            vertical-align: middle;
            cursor: default;
            margin-right:7.3%;
            .left-content {
                float: left;
                display: inline-block;
                width: 60%;
                height: 150px;
                padding: 40px 0;
                span:first-child {
                    display: inline-block;                    
                    font-size: 16px;
                }
            }
            .right-content {
                float: left;
                width: 40%;
                height: 150px;
                vertical-align: middle;
                padding: 46px 0;
            }
        }
        .homeCard:first-child {
            .left-content {
                background: #60b8f6;
            }
            .right-content {
                background: #5bafe9;
            }
        }
        .homeCard:nth-child(2) {
            // margin: 0 100px;
            .left-content {
                background: #ce84de;
            }
            .right-content {
                background: #c37dd3;
            }
        }

        .homeCard:nth-child(3) {
            margin-right:0;
            .left-content {
                background: #55da8d;
            }
            .right-content {
                background: #51cf86;
            }
        }
    }
    .left-chart {
        float: left;
        height: 380px;
        width: 35%;
        background: white;
        // margin: 0 25px 10% 25px;
        // margin:0 1.5% 0% 1.5%;
        padding-top:40px;
    }
    .right-chart {
        float: left;
        height: 386px;
        width: 61%;
        background: white;
        // margin: 0 25px 10% 48px;
        margin-left:4%;
        // padding-top:6px;
        padding:10px 35px 37px 30px;
    }
    .left_legend{
        color:#666666;
        font-size:14px;
        position: absolute;
        left:82px;
        top:40px;
        span{
            display:inline-block;
            width:12px;
            height: 12px;
            margin-right:10px;
            margin-left:42px;
            &.pic{
                background-color: #fd9dca;
                margin-left:0;
            }   
            &.audio{
                background-color: #edc58c;
            }
            &.video{
                background-color: #6fe2ec;
            }
        }
    }
    .right_legend{
        color:#666666;
        font-size:14px;
        position: absolute;
        left:43%;
        top:36px;
        span{
            display:inline-block;
            width:12px;
            height: 12px;
            background:#6b74a5;
            margin-right:10px;
        }
    }
}    
`
export default Wrapper;