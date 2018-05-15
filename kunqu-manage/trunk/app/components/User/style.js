import styled from "styled-components";

const iColor = 'rgba(140, 132, 117, 1)';
const iSize = `${12/14}rem`;
const iBtn = {
    'width':`${85/14}rem`,
    'height':`${32/14}rem`,
    'background':iColor,
    'border-radius':'2px',
    'color':'#ffffff',
    'border':'0 none',
    'font-size':iSize
}


const UserWrap = styled.div`
padding:${30/14}rem ${25/14}rem 0;
.panel-wrap{
    padding:0 ${10/14}rem;
    width: 20%;
    float: left;
    margin-bottom:${20/14}rem;
}
.panel{
    border:1px solid #cccccc;
    dl{
        height:${110/14}rem;
        padding-top:1rem;
        dt{
            // width:${132/3.08}%;
            float:left;
            img{
                width:${80/14}rem;
                height:${80/14}rem;
                border-radius:50%;
                margin:0 ${32/14}rem 0 ${20/14}rem;
            }
        }
        dd{
            float:left;
            &.name{
                line-height:${48/14}rem;
                font-size:${20/14}rem;
                color:#333333;
                width:52%;
                overflow:hidden;
                text-overflow:ellipsis;
                white-space:nowrap;
            }
            &.address{
                font-size:${iSize};
                color:#999999;
            }
        }
    }
    ul{
        width:100%;
        height:${50/14}rem;
        background:rgba(243, 244, 249, 1);
        padding:0 1rem;
        li{
            float:left;
            width:${1/0.03}%;
            text-align:center;
            line-height:1;
            font-size:${iSize};
            color:#333333;
            i{
                display:block;
                line-height:${30/14}rem;
            }
        }
    }
}
.ant-pagination{
    margin-right:0.6rem;
}
`;

const Box = styled.div`
background:#ffffff;
padding:0 ${34/14}rem;
.opinion-list{
    padding-bottom:${40/14}rem;
    .opinion-item{
        padding-top:${54/14}rem;
        border-bottom:1px dashed #cccccc;
        &:first-child{
            padding-top:${30/14}rem;
        } 
        dl{
            dt{
                float:left;
                img{
                    width:${56/14}rem;
                    height:${56/14}rem;
                    margin:0 ${30/14}rem 0 ${30/14}rem;
                }
            }
            dd{
                float:left;
                width:84%;
                &.date{
                    font-size:${iSize};
                    color:#999999;
                    line-height:1.4;
                    i{
                        font-size:1rem;
                        color:#8c8475;
                        margin-right:${22/14}rem;
                        font-weight:bold;
                    }
                }
                &.con{
                    line-height:1.5;
                    font-size:1rem;
                    color:#666666;
                    word-wrap:break-word;
                    word-break:break-all;
                    margin-top:2rem;
                    margin-bottom:1rem;
                }
            }
        }
        .reply-input{
            padding-left:${114/14}rem;
            width:${ 1034 / 16.24}%;
            textarea{
                height:${78/14}rem;
                resize:none;
                float:left;
                word-wrap:break-word;
                word-break:break-all;
                border-radius:2px;
                &:hover,&:focus{
                    border-color:${iColor};
                }
                &:focus{
                    box-shadow:0 0 0 2px rgba(140, 132, 117, 0.2)
                }
            }
            .ant-btn{
                display:block;
                width:${70/14}rem;
                height:${32/14}rem;
                padding:0;
                background:${iColor};
                color:#ffffff;
                border-radius:2px;
                border:0 none;
                float:right;
                margin-top:${10/14}rem;
            }
        }
        .reply-content{
            width:${ 920 / 16.24}%;
            height:78px;
            border:1px solid #cccccc;
            position:relative;
            background:#f2f2f2;
            margin-left:${114/14}rem;
            margin-top:${20/14}rem;
            padding:0 ${20/14}rem;
            font-size:1rem;
            color:${iColor};
            line-height:2;
            word-wrap:break-word;
            padding-top:3px;
            color:#999999;
            .arrow{
                position:absolute;
                top:-21px;
                left:50px;
                *{
                    display:block;
                    border-width:10px;
                    position:absolute;
                    border-style:dashed dashed solid dashed;
                    font-size:0;
                    line-height:0;
                }
                em{
                    border-color:transparent transparent #cccccc;
                }
                span{
                    border-color:transparent transparent #f2f2f2;
                    top:1px;
                }
            }
        }
        .action{
            float:right;
            line-height:${48/14}rem;
            margin-right:${154/14}rem;
            font-size:1rem;
            a{
                color:#a78c58;
                &:last-child{
                    display:inline-block;
                    padding-left:${8/14}rem;
                    border-left:2px solid #999999;
                    line-height:1;
                    margin-left:${8/14}rem;
                }
            }
            i{
                width:2px;
                height:${16/14}rem;
                background:#666666;
                margin:0 ${8/14}rem;
                display:inline-block;
            }
        }
    }
}

`;

export {UserWrap,Box};
