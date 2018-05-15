var rucksack = require('rucksack-css')
var autoprefixer = require('autoprefixer')
var cssnano = require('cssnano')
var cssnext = require("postcss-cssnext")

module.exports = ({ file, options, env }) => ({
  parser: file.extname === '.sss' ? 'sugarss' : false,
  plugins: {
    // 'postcss-import': { root: file.dirname },
    'postcss-cssnext': env == 'production' ? cssnext : false,
    'rucksack':rucksack(),
    'autoprefixer': env == 'production' ?  autoprefixer({
      browsers: ['last 2 versions', 'Firefox ESR', '> 1%', 'ie >= 8']
    }) : false,
    'cssnano': env === 'production' ? cssnano : false
  }
})