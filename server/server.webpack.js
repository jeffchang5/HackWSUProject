const path = require('path');

module.exports = {
  entry: path.resolve('./server/src/index.js'),
  output: {
    filename: 'bundle.js',
    path: path.resolve('./server/dist'),
  },
  devtool: 'eval',
  target: 'node',
  resolve: {
    modules: [
      path.resolve('./server/src'),
      path.resolve('./node_modules'),
    ],
    extensions: ['.js', '.json'],
  },
  module: {
    rules: [
      { test: /\.js$/, enforce: 'pre', loader: 'eslint-loader', options: { emitWarning: true } },
      { test: /\.js?$/, loader: 'babel-loader' },
      { enforce: 'pre', test: /\.js$/, loader: 'source-map-loader' },
    ],
  },
};
