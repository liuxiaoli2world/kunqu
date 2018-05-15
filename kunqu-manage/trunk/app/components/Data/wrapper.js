import styled from 'styled-components';

const Wrapper = styled.div ` 
    .ant-tabs.ant-tabs-card{
        .ant-tabs-content{
            margin-top: -16px;
            .ant-tabs-tabpane{
                background: #fff;
                padding: 20px 37px;
            }
        }
        .ant-tabs-bar{
            border-color: #ccc;
            padding-left: 34px;
            padding-top: 20px;
            background: #f3f4f9;
            .ant-tabs-nav-container{
                height: 41px;
                line-height: 2;
                margin-bottom: -1px;
            }
            .ant-tabs-tab{
                border-color: transparent;
                background: transparent;
                font-size: 16px;
                color: #333;
            }
            .ant-tabs-tab-active{
                color: #333;
                border-color: #ccc;
                border-radius: 0;
                background: #fff;
            }
        }
    }
    .titleBar{
        border-bottom: 1px solid #ccc;
        padding-bottom: 4px;
        .line{
            display: inline-block;
            width: 4px;
            height: 20px;
            background-color: rgba(140, 132, 117, 1);
            border-radius: 2px 2px 2px 2px;
        }
        .title{
            font-size: 16px;
            color: #333;
            display: inline-block;
            transform: translate(10px,-4px);
        }
    }
    .resource{
        position: relative;
        height: 380px;
        margin: 20px 0;
        border: 1px solid #ccc;
        .res-chart{
            height: 380px;
        }
    }
    .resOption{
        position: absolute;
        right: 74px;
        top: 12px;
        height: 34px;
        line-height: 34px;
        background: #f3f4f9;
        padding: 0 20px;
    }
    .video{
        .left-chart,.right-chart{
            width: calc(50% - 20px);
            height: 410px;
            border: 1px solid #ccc;
            margin: 20px 0;
            position: relative;
            &.left-chart{
                margin-right: 40px;
            }
        }
        .left-part,.right-part{
            height: 400px;
        }
    }
    .userData{
        .right-chart {
            height: 380px;
            width: 50%;
            background: white;
            border: 1px solid #ccc;
            padding:0 35px 37px 30px;
            margin: 20px 0;
        }
    }
    .gender,.province{
        position: relative;
        width: calc(50% - 20px);
        height: 410px;
        border: 1px solid #ccc;
        margin: 20px 0;
        &.gender{
            margin-right: 40px;
        }
    }
    .gender-part{
        height: 300px;
    }
    .prov-part{
        height: 350px;
    }
    .genderTitle,.provTitle{
        font-size: 16px;
        color: #333;
        text-align: center;
        margin-top: 43px;
    }
    .provTitle{
        margin-top: 32px;
    }
    .genderOption{
        position: absolute;
        right: 20%;
        top: 40%;
    }
    .male,.female,.other,.view-count,.user-count{
        display: inline-block;
        width: 10px;
        height: 10px;
        margin-right: 20px;
    }
    .male{
        background: #68dced;
    }
    .female{
        background: #fc8a87;
    }
    .other{
        background: #faec76;
    }
    .view-count{
        background: #ff87b1;
        margin-right: 10px;
    }
    .user-count{
        background: #ffd65b;
        margin-right: 10px;
        margin-left: 20px;
    }
    .clickTitle,.commentTitle{
        position: absolute;
        text-align: center;
        color: #333;
        width: 100%;
        top: 30px;
    }
`

export default Wrapper;