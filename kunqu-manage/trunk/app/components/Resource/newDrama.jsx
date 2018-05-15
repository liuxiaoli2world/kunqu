import React, { Component } from 'react';
import { Link, hashHistory } from 'react-router';
import styled from 'styled-components';
import { get, post } from '../../utils/request.js';
import {
  Form, Input, Select, Radio,
  Button, Upload, Icon, Row, Col
} from 'antd';
const FormItem = Form.Item;
const Option = Select.Option;
const RadioButton = Radio.Button;
const RadioGroup = Radio.Group;

const Wrapper = styled.div`
    .form {
        width: 1200px;
    }
`;

class MyForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showCover: false
    }
  }
  componentDidMount() {

  }

  onTypeSelect = (value, option) => {
    let show;
    if (value === 'video') {
      show = true;
    } else {
      show = false;
    }
    this.setState({
      type: value,
      showRecommond: show
    });
  }

  cancel = (e) => {
  }

  imgFile = (e) => {
    let showCover;
    if (e.fileList.length > 0) {
      showCover = true;
    } else {
      showCover = false;
    }
    this.setState({ showCover: showCover })
    if (Array.isArray(e)) {
      return e;
    }

    return e && e.fileList;
  }
  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: { span: 2 },
      wrapperCol: { span: 8 },
    };

    const isDisabled = this.props.id ? true : false;

    return (
      <Wrapper>
        <Form onSubmit={this.handleSubmit} className='form'>
          <FormItem
            {...formItemLayout}
            label="剧典名称"
          >
            {getFieldDecorator('title', {
              rules: [{ required: true, message: '请输入标题!' }, {
                max: 30, message: "标题不能超过30字，请重新输入！"
              }]
            })(
              <Input />
              )}
          </FormItem>
          <Row gutter={5}>
            <Col span={2}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
                label="分类标签"
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select placeholder={'剧目'}>

                  </Select>
                  )}
              </FormItem>
            </Col>

            <Col span={2}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select placeholder={'作者'}>

                  </Select>
                  )}
              </FormItem>
            </Col>
            <Col span={2}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select placeholder={'年代'}>

                  </Select>
                  )}
              </FormItem>
            </Col>
            <Col span={2}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select placeholder={'角色'}>

                  </Select>
                  )}
              </FormItem>
            </Col>
          </Row>
          <Row gutter={5}>
            <Col span={4}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
                label="目录"
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select>

                  </Select>
                  )}
              </FormItem>
            </Col>

            <Col span={4}>
              <FormItem
                {...formItemLayout}
                wrapperCol={{ span: 22 }}
              >
                {getFieldDecorator('cmenuId', {
                  rules: [
                    { required: true, message: '请选择一个分类!' },
                  ],
                })(
                  <Select>

                  </Select>
                  )}
              </FormItem>
            </Col>
          </Row>
          <FormItem
            {...formItemLayout}
            label="首页推荐"
          >
            {getFieldDecorator('isDownload', {
              rules: [{ required: true, message: '请选择!' }],
              initialValue: 0
            })(
              <RadioGroup>
                <Radio value={1}>是</Radio>
                <Radio value={0}>否</Radio>
              </RadioGroup>
              )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="列表推荐"
          >
            {getFieldDecorator('isDownload', {
              rules: [{ required: true, message: '请选择!' }],
              initialValue: 0
            })(
              <RadioGroup>
                <Radio value={1}>是</Radio>
                <Radio value={0}>否</Radio>
              </RadioGroup>
              )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="最热剧典"
          >
            {getFieldDecorator('isDownload', {
              rules: [{ required: true, message: '请选择!' }],
              initialValue: 0
            })(
              <RadioGroup>
                <Radio value={1}>是</Radio>
                <Radio value={0}>否</Radio>
              </RadioGroup>
              )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="折子戏"
          >
            {getFieldDecorator('isDownload', {
              rules: [{ required: true, message: '请选择!' }],
              initialValue: 0
            })(
              <RadioGroup>
                <Radio value={1}>是</Radio>
                <Radio value={0}>否</Radio>
              </RadioGroup>
              )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="封面"
          >
            {getFieldDecorator('imgList', {
              valuePropName: 'fileList',
              getValueFromEvent: this.imgFile,
              rules: [{ required: true, message: '请上传封面!' }]
            })(
              <Upload name="file" action={''} listType="picture" accept=".jpg, .png, .gif">
                <Button disabled={this.state.showCover}>
                  <Icon type="upload" /> 上传图片
                </Button>
              </Upload>
              )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="简介"
          >
            {getFieldDecorator('multiDesc', {
              rules: [{ required: false }, {
                max: 300, message: "资源简介不能超过300字，请重新输入！"
              }]
            })(
              <Input type="textarea" rows={5} autosize={true} />
              )}
          </FormItem>
          <FormItem
            wrapperCol={{ span: 4, offset: 2 }}
            className={'editButtons'}
          >
            <Button type="primary" htmlType="submit" className="save-btn">保存</Button>
            <Button type="default" className="cancel-btn" onClick={this.cancel}>取消</Button>
          </FormItem>
        </Form>
      </Wrapper>
    );
  }
}


class NewDrama extends React.Component {
  render() {
    const WrappedForm = Form.create()(MyForm);
    return (
      <WrappedForm id={this.props.id} info={this.props.info} />
    )
  }
}

export default NewDrama;