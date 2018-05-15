
import React from 'react'
import { RouterContext } from 'react-router'
import { renderToString } from 'react-dom/server'
import config from '../../common/config'

export default async (ctx, next, renderProps) => {
  const route = renderProps.routes[renderProps.routes.length - 1]
  let prefetchTasks = []
  for (let component of renderProps.components) {
    if (component && component.fetch) {
      const _tasks = component.fetch();
      if (Array.isArray(_tasks)) {
        prefetchTasks = prefetchTasks.concat(_tasks)
      } else if (_tasks.then) {
        prefetchTasks.push(_tasks)
      }
    }
  }

  await Promise.all(prefetchTasks)
  await ctx.render('index', {
    title: config.title,
    dev: ctx.app.env === 'development',
    staticPath:  config.contextPath+config.staticPath,
    app: renderToString(
      <RouterContext {...renderProps} />
)
  })  
}
