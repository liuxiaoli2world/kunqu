import { hashHistory } from 'react-router';
import config from '../../config/index.js';
require('./jquery.xdomainrequest.min.js');

export default function request (method, url, body) {
  const baseUrl = config.baseUrl;
  url = baseUrl+url;
  method = method.toUpperCase();
  let contentType = 'application/json';
  if (method === 'GET') {
    body = undefined;
  }else if(method === 'FORM-POST'){
      let str = '';
      for(let prop in body){
          str += `${prop}=${body[prop]}&`;
      }
      body = str.substr(0,str.length-1);
      contentType = 'application/x-www-form-urlencoded';
      method = "POST";
  } else {
    body = body && JSON.stringify(body);
  }
  if(window.XDomainRequest){ //for IE8,IE9
    jQuery.support.cors = true;
    return $.ajax({
        url:url,
        data:body,
        type:method,
        dataType:"json",   
        contentType:"text/plain",
        headers:{
          'Access-Token': sessionStorage.getItem('access_token') || ''
        }
      })
      .then((res,status,xhr) => {
        if (xhr.status === 401) {
          hashHistory.push('/login');
          return Promise.reject('Unauthorized.');
        } else {
          const token = xhr.getResponseHeader('access-token');
          if (token) {
            sessionStorage.setItem('access_token', token);
          }
          return res;
        }
      }).catch((res) =>{console.log(res)})
  }else{
    return fetch(url, {
      method,
      headers: {
        'Content-Type': contentType,
        'Accept': 'application/json',
        'Access-Token': sessionStorage.getItem('access_token') || ''
      },
      body
    })
      .then((res) => {
        if (res.status === 401) {
          hashHistory.push('/login');
          return Promise.reject('Unauthorized.');
        } else {
          const token = res.headers.get('access-token');
          if (token) {
            sessionStorage.setItem('access_token', token);
          }
          return res.json();
        }
      })
      .catch((res) => {
        console.log(res);
      })
  }
}

export const get = url => request('GET', url);
export const post = (url, body) => request('POST', url, body);
export const put = (url, body) => request('PUT', url, body);
export const del = (url, body) => request('DELETE', url, body);
export const formPost = (url, body) => request('FORM-POST', url, body);