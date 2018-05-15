import path from 'path'
import views from 'koa-views'
import json from 'koa-json'
import logger from 'koa-logger'
import koaStatic from 'koa-static-plus'
import koaOnError from 'koa-onerror'
import convert from 'koa-convert'
import Bodyparser from 'koa-bodyparser'
import router from './routes'
import config from '../common/config'
import  proxy from 'koa-proxy2';
const bodyparser = Bodyparser()
const templatePath = path.join(__dirname, './templates')

var prefix = config.contextPath+'remote'
export default (app) => {
  // reg middlewares
  app.use(convert(bodyparser))
  app.use(convert(json()))
  app.use(convert(logger()))
  app.use(async (ctx,next)=>{
    if(ctx.path.startsWith(config.contextPath+"remote/api/")){
      ctx.path = ctx.path.substring(config.contextPath.length-1);
    }else if(ctx.path.startsWith(config.contextPath+"remote/fs/")){
      ctx.path = ctx.path.substring(config.contextPath.length-1);
     
    }
    console.error(ctx)
    await next(ctx,next);
  })
  app.use(proxy({
    proxy_rules: [
      {
        proxy_location: /remote\/api/,
        proxy_pass: config.proxyUrl,
        proxy_micro_service: true
      },
      {
        proxy_location: /remote\/fs/,
        proxy_pass: config.fsServerUrl,
        proxy_micro_service: true
      }
    ]
  }));
  app.use(async (ctx,next)=>{
   console.error("不走代理")
    await next(ctx,next);
  })
  var staticPath = path.join(config.contextPath,config.staticPath);
  staticPath = staticPath.replace(/\\/g,"/")
  var lIndex = staticPath.lastIndexOf("\/");
  if(lIndex+1 == staticPath.length){
    staticPath = staticPath.substring(0,lIndex);
  }
  // static serve
  app.use(convert(koaStatic(config.clientOutputPath,{
    pathPrefix:staticPath
  })))
 
  // template ejs
  app.use(views(templatePath, { extension: 'ejs' }))

  // router dispatcher
 
  app.use(router)

  // 500 error
  koaOnError(app, { template: templatePath + '/500.ejs' })

  // logger
  if (app.env === 'development') {
    app.use(async (ctx, next) => {
      const start = new Date()
      await next()
      const ms = new Date() - start
      console.log(`${ctx.method} ${ctx.url} - ${ms}ms`)
    })
  }

  // 404
  app.use(async (ctx) => {
    ctx.status = 404
    await ctx.render('404')
  })
}
