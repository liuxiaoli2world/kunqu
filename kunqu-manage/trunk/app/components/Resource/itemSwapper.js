import styled from 'styled-components';

/**
 * 单个项目样式
 */
const ItemSwapper = styled.div`
    clear:both;
    width: 308px;
    background: #fff;
    display: inline-block;
    margin: 10px;
    border: 1px solid #ccc;
    overflow: hidden;
    position: relative;
    .selected-image {
        position: absolute;
        right: 0;
        width: 50px;
        height: 50px;
    }
    .show-content {
        padding: 15px;
        clear: both;
    }
    .image {
        display: block;
        height: 180px;
        width: 100%;
        border: 1px solid #ccc;
    }
    .title {
        font-size: 14px;
        line-height: 21px;
        height: 42px;
        color: #333;
        margin: 10px 0;
    }
    .content {
        font-size: 12px;
        line-height: 18px;
        color: #666;
        height: 36px;
        margin-bottom: 14px;
    }
    .count {
        font-size: 12px;
        color: #666;
        height: 15px;
    }
    .buttons {
        background: #f8f8f8;
        height: 70px;
        line-height: 70px;
        text-align: center;
        .btn {
            font-size: 12px;
            background: #eee;
            color: #666;
            width: 70px;
            height: 32px;
            border: 1px solid #ccc;
            border-radius: 0px;
            margin: 0 10px;
            padding: 0;
            &:hover {
                color: #8c8475;
                border: 1px solid #8c8475;
                background: #fdf7ec;
            }
        }
    }
`

export default ItemSwapper;