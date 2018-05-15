
import { get, post, formPost } from '../../utils/request';

export function getList(params) {
  return new Promise((resolve, reject) => {
    let url = `remote/api/kunqu/classify/queryByTag?queryType=${params.queryType}&pageNum=${params.pageNum}&pageSize=${params.pageSize}`;
    url = params && params.keyword ? url + `&keyword=${params.keyword}` : url;
    post(url).then((respData) => {
      resolve(respData);
    });
  });
}

export function getAboutList(params) {
  return new Promise((resolve, reject) => {
    get(`remote/api/kunqu/dramaabout/queryDramaAndAbout?pageNum=1&pageSize=6&dramaId=${params.id}`).then((respData) => {
      resolve(respData);
    });
  });
}

export function batchAddAbout(params) {
  return new Promise((resolve, reject) => {
    formPost('remote/api/kunqu/dramaabout/batchAddAbout', params).then((respData) => {
      resolve(respData);
    });
  });
}