export default function request (method, url, body) { 
      method = method.toUpperCase();
      var contentType = 'application/json';
      if (method === 'GET') {
          body = undefined;
      }else if(method == 'FORM-POST'){
        let str = '';
        for (let prop in body) {
          str += `${prop}=${body[prop]}&`;
        }
        body = str.substr(0, str.length-1);
        contentType = 'application/x-www-form-urlencoded';
        method = 'POST';
      } else {
          body = body && JSON.stringify(body);
      }
      return fetch(url, {
        method,
        headers: {
          'Content-Type': contentType,
          'Accept': 'application/json',
        },
        body
      })
        .then((res) => {
          if (res.status === 401) {
            return Promise.reject('Unauthorized.');
          } else {
            return res.json();
          }
        });
    }
    
    export const get = url => request('GET', url);
    export const post = (url, body) => request('POST', url, body);
    export const put = (url, body) => request('PUT', url, body);
    export const del = (url, body) => request('DELETE', url, body);
    export const formPost = (url, body) => request('FORM-POST', url, body);
    