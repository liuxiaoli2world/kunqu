import styled from "styled-components";

const iHeight = {
    'height':`${32/14}rem`,
    'line-height':`${32/14}rem`
}

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

const Wrapper = styled.div`
.ant-tabs-bar{
    height:${60/14}rem;
    padding-top:${21/14}rem;
    padding-left:${34/14}rem;
    border-bottom:1px solid #cccccc;
    margin-bottom:0;
    background:#F3F4F9;
}
.ant-tabs.ant-tabs-card > .ant-tabs-bar .ant-tabs-nav-container{
    height:${39/14}rem;
}
.ant-tabs.ant-tabs-card > .ant-tabs-bar .ant-tabs-tab{
    padding:0;
    width:${120/14}rem;
    height:${38/14}rem;
    text-align:center;
    line-height:${38/14}rem;
    border-radius:0;
    margin-right:${20/14}rem;
    color:#333333;
    font-size:${16/14}rem;
    border-color:transparent;
    background:#F3F4F9;
    transition:none;
    margin:0;
}
.ant-tabs-nav-scroll{
    margin-left:0;
}
.ant-tabs.ant-tabs-card > .ant-tabs-bar .ant-tabs-tab-active{
    height:${39/14}rem;
    background:#ffffff;
    border-color:#cccccc;
}

    .ant-pagination-prev a:after{
        content:"\uE605";
        ${iHeight};
    }
    .ant-pagination-next a:after{
        content:"\uE604";
        ${iHeight};
    }
    .ant-pagination{
        float:right;
        margin:${18/14}rem ${35/14}rem;
    }
    .ant-pagination-item{
        min-width:${32/14}rem;
        ${iHeight};
        border-radius:0;
        margin-right:0;
        float:left;
        font-size:${12/14}rem;
        &:hover{
            border-color:${iColor};
            a{color:rgba(0, 0, 0, 0.65);}
        }
    }
    .ant-pagination-jump-prev, .ant-pagination-jump-next,.ant-pagination-prev, .ant-pagination-next{
        min-width:${32/14}rem;
        ${iHeight};
        border-radius:0;
        margin-right:0;
        float:left;
        &:hover{
            border-color:${iColor};
        }
    }
    
    .ant-pagination-options-quick-jumper{
        ${iHeight};
    }
    .ant-pagination-options-quick-jumper input{
        ${iHeight};
        border-radius:0;
        width:${40/14}rem;
        &:hover{
            border-color:${iColor};
        }
    }
    .ant-pagination-item-active{
        background:${iColor};
        border-color:${iColor};
        &:hover{
            a{color:#ffffff}
        }
        &:focus{
            background:${iColor};
            border-color:${iColor};
        }
    }
`;

const  ListWrap = styled.div`
    .search-part{
        font-size:${iSize};
        color:#666666;
        padding:${30/14}rem ${35/14}rem;
        line-height:${30/14}rem;
        &>span{
            float:left;
        }
        .ant-input{
            width: ${200/14}rem;
            height: ${32/14}rem;
            border-radius: 2px;
            border: solid 1px rgba(204, 204, 204, 1);
            float:left;
            &:focus{
                border:1px solid ${iColor};
                box-shadow:0 0 0 2px rgba(140, 132, 117, 0.2);
            }
        }
        .ant-btn{
            width: ${60/14}rem;
            ${iHeight};
            background-color: ${iColor};
            border-radius: 2px;
            float:left;
            border:0 none;
            margin-left:1rem;
            color:#ffffff;
            padding:0;
        }
        .add{
            width:${90/14}rem;
            float:right;
            i{
                font-weight:bold;
                font-size:2rem;
                float:left;
                margin-top:-0.2rem;
                margin-left:1rem;
            }
            span{
                float:left;
                margin-left:0.2rem;
            }
        }
    }
    .custom-image{
        height:${186/14}rem;
    }
    .custom-image img {
        display: block;
        width:100%;
        height:100%;
    }
    .custom-card {
        padding-top:${12/14}rem;
        font-size:${iSize};
        color:#666666;
        h3{
            line-height:${20/14}rem;
            font-size:1rem;
            color:#333333;
        }
        p{
            padding:${4/14}rem 0;
            line-height:1.5;
            width:100%;
            overflow:hidden;
            text-overflow:ellipsis;
            white-space:nowrap;
        }
    }
    .sourceNum{
        line-height:1;
        padding:${7/14}rem 0 ${16/14}rem;
        width:100%;
        span{
            display:inline-block;
            width:${104/2.78}%;
            &:last-child{
                width:${70/2.78}%;
            }
        }
    }
    .fot-action{
        height: ${68/14}rem;
	    background-color: #F8F8F8;
        border-radius: 2px;
        width:100%;
        padding:${18/14}rem 0 0 ${34/3.06}%;
        div{
            float:left;
            width:${71/2.74}%;
            margin-right:${13/2.74}%;
            position:relative;
            .ant-btn{
                width:100%;
            }
            ul{
                position:absolute;
                left:0;
                top: ${32/14}rem;
                border:1px solid #cccccc;
                width:100%;
                display:none;
                li{
                    width:100%;
                    height: ${32/14}rem;
                    line-height: ${30/14}rem;
                    text-align:center;
                    border-top:1px solid #cccccc;
                    background:rgba(238, 238, 238, 1);
                    color:#666666;
                    cursor:pointer;
                    &:first-child{border-top:0 none;}
                    &:hover{
                        background-color: rgba(253, 247, 236, 1);
                        color: #8c8475;
                    }
                }
            }
            &:hover{
                ul{display:block;}
            }
        }
        .ant-btn{
            width:${71/2.74}%;
            height: ${32/14}rem;
            background-color: rgba(238, 238, 238, 1);
            border-radius: 2px;
            border: solid 1px rgba(204, 204, 204, 1);
            color:#666666;
            margin-right:${13/2.74}%;
            padding:0;
            font-size:${12/14}rem;
            &:hover{
                background-color: rgba(253, 247, 236, 1);
                border: solid 1px rgba(140, 132, 117, 1);
                color: #8c8475;
            }
        }
    }
    .list{
        padding:0 ${25/14}rem;
    }
    .item-wrap{
        padding-left: ${10/14}rem;
        padding-right: ${10/14}rem;
        width: 20%;
        float: left;
        margin-bottom:${20/14}rem;
    }
    .item{
        background:#ffffff;
        border:1px solid #cccccc;
        border-radius:2px;
        font-size:${12/14}rem;
        position:relative;
        transition:all .3s;
    }
    .item-body{
        padding:${15/14}rem;
        padding-bottom:0;
        zoom:1;
        &:before{
            content:'';
            display:table;
            box-sizing:border-box;
        }
    }
`

const New = styled.div`
.ant-upload-list-picture-card .ant-upload-list-item{
    width:${108/14}rem;
    height:${80/14}rem;
    padding:0;
    position:absolute;
    top:0;
    left:0;
}
.ant-upload.ant-upload-select-picture-card{
    width:auto;
    height:${32/14}rem;
    margin-top:2rem;
    margin-bottom:0;
    border:0 none;
    &>.ant-upload{
        padding:0;
    }
}
.ant-upload-list-picture-card .ant-upload-list-item:hover .ant-upload-list-item-info:before{
    opacity : 0;
}
.ant-input{
    width:400px;
    ${iHeight};
    border: 1px solid #cccccc;
    border-radius:2px;
    &:focus,&:hover{
        border:1px  solid ${iColor};
        
    }
    &:focus{
        box-shadow:0 0 0 2px rgba(140, 132, 117, 0.2);
    }
}
textarea.ant-input{
    height:${90/14}rem;
    resize:none;
    font-size:1rem;
    line-height:1.5;
}
.ant-form-item{
    margin-top:${30/14}rem;
    margin-bottom:0;
    .preview{
        width:${108/14}rem;
        height:${80/14}rem;
        border: solid 1px #cccccc;
        float:left;
        img{
            width:100%;
            height:100%;
            max-width:100%;
        }
    }
    .right-panel{
        float:left;
        margin-left:${34/14}rem;
        p{
            line-height:1.5;
        }
        .ant-upload{
            .ant-btn{
                padding:0;
                ${iBtn};
                font-size:${iSize};
            }
        }
    }
    &:nth-child(3){
        .ant-form-item-label{
            line-height:1;
        }
        .ant-form-explain{
            position:absolute;
            left:0;
            bottom:0;
        }
    }
    &:nth-child(4){
        margin-top:${20/14}rem;
    }
    &:nth-child(2){
        .ant-form-item-label{
            line-height:1;
        }
        .ant-form-item-control{
            line-height:1;
        }
    }
    &:last-child{
        margin-left:${110/14}rem;
        .ant-btn{
            ${iBtn};
            &:last-child{
                background:#ffffff;
                color:${iColor};
                border:1px solid #cccccc;
                margin-left:${20/14}rem;
            }
        }
    }
}
.ant-form-item-label{
    float:left;
    width:${110/14}rem;
    label{
        color:#666666;
        font-size:1rem;
    }
}
.ant-form-item-required:before{
    font-size:${12/14}rem;
}
.ant-form-item-control-wrapper{
    float:left;
}
.ant-form-explain{
    height:0;
}
`;

const RelationWrapper = styled.div`
width:100%;
height:100%;
position:fixed;
left:0;
top:0;
background:rgba(0,0,0,.3);
z-index:999;
.container{
    width:${1039/14}rem;
    // height:${838/14}rem;
    height:${760/14}rem;
    background:rgba(242, 242, 242, 1);
    position:absolute;
    left:29%;
    top:12%;
    .ant-pagination{
        margin:0;
        color:#333333;
    }
}
.footer{
    padding:0 ${38/14}rem;
    .ant-btn{
        ${iBtn};
        &:nth-child(2){
            margin-left:${20/14}rem;
            background:#ffffff;
            color:#666666;
            border:1px solid #cccccc;
        }
    }
}
.search-type{
    font-size:${iSize};
    color:#666666;
    line-height:${32/14}rem;
    padding:${32/14}rem ${38/14}rem;
    .ant-select-selection{
        border-radius:2px;
        border-color:${iColor};
        font-size:${iSize};
    }
    .ant-select-selection--single{
        height:${32/14}rem;
    }
    .ant-select-selection__rendered{
        line-height:${30/14}rem;
    }
}
.source-list{
    padding:0 ${28/14}rem;
    .source-item-wrap{
        padding:0 ${10/14}rem;
        width:${1/0.03}%;
        float:left;
        margin-bottom:${20/14}rem;
    }
    .source-item{
        border:1px solid rgba(204, 204, 204, 1);
        background:#ffffff;
        padding:${15/14}rem ${15/14}rem 0;
        position:relative;
        &.ischosed{
            border-color:${iColor};
        }
        &>div{
            img{
                width:100%;
                max-width:100%;
                height:${186/14}rem;
            }
        }
        h4{
            font-size:1rem;
            color:#333333;
            line-height:1.5;
            margin-top:${iSize};
            word-wrap:break-word;
        }
        p{
            font-size:${iSize};
            color:#666666;
            line-height:1;
            margin-top:${16/14}rem;
            margin-bottom:2rem;
            width:100%;
            overflow:hidden;
            text-overflow:ellipsis;
            white-space:nowrap;
        }
        .tag{
            position:absolute;
            right:0;
            top:0;
            width:${50/14}rem;
            height:${50/14}rem;
        }
    }
}
`;

export {ListWrap,Wrapper,New,RelationWrapper};