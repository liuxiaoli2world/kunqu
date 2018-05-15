process.env.NODE_ENV = 'production'

require('babel-core/register')
var config = require('./platforms/common/config')
var webpack = require('webpack')
var path = require('path')
var UglifyJsPlugin = webpack.optimize.UglifyJsPlugin
var ExtractTextPlugin = require('extract-text-webpack-plugin')
var extractCSS = new ExtractTextPlugin('all.min.one.css')
var extractLESS = new ExtractTextPlugin('all.min.two.css')
var extractSCSS = new ExtractTextPlugin('all.min.three.css')

var fs = require('fs')
var nodeModules = fs.readdirSync('node_modules')
  .filter(function (i) {
    return ['.bin', '.npminstall'].indexOf(i) === -1
  })
var includes = [
  path.resolve(__dirname, 'app'),
  path.resolve(__dirname, 'platforms')
]

module.exports = [{
  name: 'browser side render',
  devtool: 'cheap-source-map',
  entry: ['./platforms/browser/index.js'],
  output: {
    path: config.clientOutputPath,
    filename: '[name].js',
    publicPath: config.contextPath+config.staticPath
  },
  module: {
 
    // noParse: function(content) {
    //   return /jquery|lodash/.test(content);
    // },
    rules:[
      {
        test: /\.jsx?$/,
        use:{
          loader: 'babel-loader',
          options: {
            plugins: [
              ["import", { "libraryName": "antd", "style": "css" }]
            ]
          }
        },
        exclude: /node_modules/,
        include: includes
        
      },
        {
          test: /\.css$/,
          use: extractCSS.extract({
            fallback: 'style-loader',
            use: [
              { loader: 'css-loader', options: { importLoaders: 1 } },
              { 
                loader:'postcss-loader',
                options: {
                  config: {
                    ctx: {
                      cssnext: true,
                      cssnano: true
                    }
                }
              }
            }
            ]})
        },
        {
          test: /\.less$/i,
          use: extractLESS.extract([ 'css-loader', 'less-loader' ])
        },
        {
          test: /\.scss$/,
          use: extractSCSS.extract({
            fallback: 'style-loader',
            //resolve-url-loader may be chained before sass-loader if necessary
            use: ['css-loader', 'sass-loader']
          })
        },
        { test: /\.woff2?$/, 
          use: {
            'loader':'url-loader',
            options:{
              limit:10000,
              minetype:'application/font-woff'
            }
          }
        },
        { test: /\.ttf$/, 
          use:{
          loader: 'url-loader',
          options:{
            'limit':10000,
            minetype:'application/octet-stream'
          }
          }
        },
        { test: /\.eot$/, use: 'file-loader' },
        { test: /\.svg$/, 
          use:{
            loader: 'url-loader',
            options:{
              limit:10000,
              minetype:'image/svg+xml' 
            }
          }
        },
        { test: /\.(png|jpg|jpeg|gif|webp)$/i,
          use:{
            loader: 'url-loader',
            options: {
              'limit':10000
             } 
          }
        },
        { test: /\.json$/, 
          use: 'json-loader' 
        },
        { test: /\.html?$/, 
          use:{
            loader: 'file-loader',
            options:{
              name:'[name].[ext]' 
            }
          }
        },
        {
            test: require.resolve('jquery'),
            use: [{
                loader: 'expose-loader',
                options: 'jQuery'
            },{
                loader: 'expose-loader',
                options: '$'
            }]
        }
    ]
  },
    resolve: {
      extensions: ['.js', '.jsx']
    },
    plugins: [
      extractCSS,
      extractLESS,
      extractSCSS,
      new webpack.optimize.CommonsChunkPlugin({
        name: "common",
        // (the commons chunk name)

        filename: "common.js",
        // (the filename of the commons chunk)
      }),
      new UglifyJsPlugin({
        compress: { warnings: false }
      }),
      new webpack.DefinePlugin({
        'process.env.NODE_ENV': JSON.stringify('production'),
        __SERVER__: false
      })
    ]

}, 
{
  name: 'server side render',
  devtool: 'cheap-source-map',
  entry: ['./platforms/server/index.js'],
  output: {
    path: config.serverOutputPath,
    filename: 'index.js',
    publicPath: config.contextPath+config.staticPath,
    libraryTarget: 'commonjs2'
  },
  target: 'node',
  node: {
    fs: 'empty',
    __dirname: true,
    __filename: true
  },
  externals: [
    function (context, request, callback) {
      var pathStart = request.split('/')[0]
      if (pathStart && (pathStart[0] === '!') || nodeModules.indexOf(pathStart) >= 0 && request !== 'webpack/hot/signal.js') {
        return callback(null, 'commonjs ' + request)
      }
      callback()
    }
  ],
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        exclude: /node_modules/,
        include: includes,
        use: {
          loader:'babel-loader',
           options: {
            plugins: [
              ["babel-plugin-transform-require-ignore", {
                "extensions": [".less", ".css"]
              }]
            ]
           }
        }
      }, {
        test: /\.(css|less)$/,
        use: 'null-loader'
      },
      { test: /\.woff2?$/, use: 'null-loader' },
      { test: /\.ttf$/, use: 'null-loader' },
      { test: /\.eot$/, use: 'null-loader' },
      { test: /\.svg$/, use: 'null-loader' },
      { test: /\.(png|jpg|jpeg|gif|webp)$/i, use:{
        loader: 'url-loader',
        options:{
          limit:10000
        }
       }
     },
      { test: /\.json$/, use: 'json-loader' },
      {
          test: require.resolve('jquery'),
          use: [{
              loader: 'expose-loader',
              options: 'jQuery'
          },{
              loader: 'expose-loader',
              options: '$'
          }]
      }
    ]
  },
  resolve: {
    extensions: [ '.js', '.jsx'],
    modules: ['node_modules']
  },
  plugins: [
    new UglifyJsPlugin({
      compress: { warnings: false }
    }),
    new webpack.DefinePlugin({
      'process.env.NODE_ENV': JSON.stringify('production'),
      __SERVER__: true
    })
  ]
}]
