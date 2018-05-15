
var path = require('path')

const rootPath = path.join(__dirname, '../../..')
export default {
  rootPath,
  contextPath:'/',
  staticPath: 'static/',//
  proxyUrl : 'http://192.168.89.251:7020',
  fsServerUrl:'http://192.168.89.200:100',
  clientOutputPath: path.join(rootPath,'output','client'),
  serverOutputPath:path.join(rootPath,'output','server'),
  port: 3000
}
