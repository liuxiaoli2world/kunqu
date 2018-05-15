import React from "react";
import {Link} from "react-router";
import {New} from "./style.js";
import {Form,Input,Button,Radio,Upload,Modal,message} from "antd";
import {get,post} from "../../utils/request.js";
import {SubEmiter,Emiter} from "../../utils/index.js";

const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const { TextArea } = Input;


class FormWrap extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            detail : {}
        }
        let _this = this;
        this.upLoadconfig = {
            name: 'file',
            action: 'http://192.168.89.200:100/fs/picture/uploadFile',
            listType : 'picture-card',
            showUploadList : { showPreviewIcon: false, showRemoveIcon: false },
            beforeUpload(file, fileList) {
              if(file.type.indexOf('image/')>=0) {
                  return true;
              }else{
                  message.error("请上传jpg、png、gif图片文件！")
                  return false;
              }
            },
            onChange(info) {
                if (info.file.status !== 'uploading') {
                        const res = info.file.response;
                        if (res.meta.success) {
                            let {detail} = _this.state;
                            detail.image = res.data;
                            _this.setState({
                                detail
                            })
                        }
                }
                if (info.file.status === 'done') {
                        message.success(`${info.file.name} 文件上传成功！`);
                } else if (info.file.status === 'error') {
                        message.error(`${info.file.name} 文件上传失败！`);
                }
                
            },
        };
    }

    componentWillReceiveProps(nextProps) {
       
    }
    

    componentDidMount(){
        if(this.props.type == 'edit'){
            get(`remote/api/kunqu/ceremony/query/${this.props.id}`)
            .then(res=>{
                if(res&&res.meta&&res.meta.success){
                    let {detail} = this.state;
                    this.props.form.setFieldsValue({
                        intro : res.data.ceremonyDesc,
                        name : res.data.ceremonyName,
                        isRecommand : res.data.isIndexRecommend + '',
                        cover : [{uid : '-1' , url : res.data.coverImg}],
                    })
                    detail.image = res.data.coverImg;
                    this.setState({
                        ceremonyId : res.data.ceremonyId,
                        detail
                    })
                }
            }).catch(e=>{
                console.log(e)
            })
        } 
    }
    


    //保存
    handleSubmit = (e)=>{
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
          if (!err) {
              let record = {
                    "ceremonyDesc": values.intro,
                    "ceremonyName": values.name,
                    "coverImg": this.state.detail.image,
                    "isIndexRecommend": parseInt(values.isRecommand),
              }
              if(this.props.type == 'edit'){
                    record.ceremonyId = this.state.ceremonyId;
                    post(`remote/api/kunqu/ceremony/modify`,record)
                    .then(res=>{
                        if(res&&res.meta&&res.meta.success){
                            message.success("修改成功");
                            this.props.handleCallback({type : 'remove'});
                            Emiter.emit('handleAdd',{'isAdd' : true});
                        }
                    }).catch(e=>{console.log(e)})
              }else{
                    post(`remote/api/kunqu/ceremony/add`,record)
                    .then(res=>{
                        if(res&&res.meta&&res.meta.success){
                            message.success("构建成功");
                            this.props.handleCallback({type : 'remove'});
                            Emiter.emit('handleAdd',{'isAdd' : true});
                        }
                    }).catch(e=>{console.log(e)})
              }
             
          }
        });
    }

    normFile = (e) => {
        // console.log('Upload event:', e);
        if (Array.isArray(e)) {
          return e;
        }
        return e && e.fileList;
    }
    //取消
    handleCancel = ()=>{
        this.props.handleCallback({type : 'remove'})
    }


    render(){
        const { getFieldDecorator } = this.props.form;
        let {detail} = this.state;
        return (
            <Form onSubmit={this.handleSubmit}>
                <FormItem label="专题名称">
                    {getFieldDecorator('name', {
                        rules: [
                            {required: true, message: '专题名称不能为空!',}
                        ],
                    })(
                        <Input />
                    )}
                </FormItem>
                <FormItem label="首页推荐">
                    {getFieldDecorator('isRecommand', {
                        rules: [
                            {required: true}
                        ],
                        initialValue : '0'
                    })(
                        <RadioGroup>
                            <Radio value="1">是</Radio>
                            <Radio value="0">否</Radio>
                        </RadioGroup>
                    )}
                </FormItem>
                <FormItem label="封面">
                    <div className="preview"></div>
                        <div className='clearfix left'>
                            <div className='right-panel'>
                                <p>请上传建议图片尺寸为是300*400的jpg、png、gif图片</p>
                                {getFieldDecorator('cover', {
                                    rules: [
                                        {
                                            required : true,message : "封面不能为空"
                                        }
                                    ],
                                    getValueFromEvent: this.normFile,
                                    valuePropName: 'fileList',
                                })(
                                    <Upload {...this.upLoadconfig}>
                                        <Button>上传图片</Button>
                                    </Upload>
                                )}
                        </div>
                    </div>
                </FormItem>
                <FormItem label="专题介绍">
                    {getFieldDecorator('intro', {
                        rules: [
                            {required: true, message: '专题介绍不能为空!',}
                        ],
                    })(
                        <TextArea />
                    )}
                </FormItem>
                <FormItem>
                    <Button type="primary" htmlType="submit">保存</Button>
                    <Button onClick={this.handleCancel.bind(this)}>取消</Button>
                </FormItem>
            </Form>
        )
    }
}

const NewForm = Form.create()(FormWrap);

class NewKnowledge extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            info : {}
        }
    }

    handleCallback = (param) =>{
        this.props.parentCallback(param)
    }

    componentDidMount() {
        
    }
    

    render(){
        return (
            <New>
                {
                    this.props.id  ? 
                    <NewForm handleCallback={this.handleCallback.bind(this)} id ={this.props.id} type='edit'/> : <NewForm handleCallback={this.handleCallback.bind(this)} type='add'/>
                }
                
            </New>
        )
    }
}

export default NewKnowledge;