process.env.NODE_ENV = 'development'
var config = require('./platforms/common/config')
var webpack = require('webpack')
var path = require('path')

var includes = [
  path.resolve(__dirname, 'app'),
  path.resolve(__dirname, 'platforms')
]

module.exports = {
  name: 'backend dev hot middlware',
  entry: [
    // For old browsers
    'eventsource-polyfill',
    'webpack-hot-middleware/client?path=/__webpack_hmr&timeout=20000',
    './platforms/browser/index.js'
  ],
  output: {
    path: config.clientOutputPath,
    filename: '[name].js',
    chunkFilename: '[id].chunk.js',
    publicPath:config.contextPath+config.staticPath
  },
  resolve: {
    modules: ['node_modules', path.join(__dirname, '/node_modules')],
    extensions: [ '.js', '.jsx']
  },

  resolveLoader: {
    modules: ['node_modules', path.join(__dirname, '/node_modules')]
  },

  module: {
    rules: [
      {
          test: require.resolve('jquery'),
          use: [{
              loader: 'expose-loader',
              options: 'jQuery'
          },{
              loader: 'expose-loader',
              options: '$'
          }]
      },
      {
        test: /\.jsx|.js$/,
        exclude: /node_modules/,
        include: includes,
       use:{
          loader: 'babel-loader',
          options: {
            presets: ['react-hmre'],
            plugins: [
              ["inline-replace-variables", {
                "__SERVER__": false
              }],
              ["import", { "libraryName": "antd", "style": "css" }]
            ]
          }
       }
        
      }, {
        test: /\.css$/,
        use: ['style-loader','css-loader','postcss-loader']
      }, {
        test: /\.less$/,
         use: ['style-loader','css-loader','postcss-loader','less-loader']
      },{
        test: /\.scss$/,
         use: ['style-loader','css-loader','postcss-loader','sass-loader']
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
          options:{
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
      }
    ]
  },

  plugins: [
    new webpack.optimize.CommonsChunkPlugin({ 
      name: 'common', 
      filename: 'common.js' 
    }),
    new webpack.HotModuleReplacementPlugin()
  ]
}

