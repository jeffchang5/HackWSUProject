// Karma configuration
// Generated on Sun Apr 30 2017 17:54:23 GMT-0400 (EDT)
const webpackConfig = require('./webpack.karma.js');

module.exports = function (config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['mocha'],


    // list of files / patterns to load in the browser
    files: [
      { pattern: 'tests/**/*spec.js', included: true },

    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor


    // tests results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['spec'],
    // Configuration for the spec reporter.
    specReporter: {
      maxLogLines: 5,
      suppressErrorSummary: true,
      suppressFailed: false,
      suppressPassed: false,
      suppressSkipped: true,
      showSpecTiming: false,
    },


    // web server port
    port: 8080,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    /* possible values: config.LOG_DISABLE || config.LOG_ERROR
     || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG */

    logLevel: config.LOG_INFO,

    webpack: webpackConfig,

    preprocessors: {
      './tests/**/*spec.js': ['webpack', 'sourcemap'],
    },

    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'], // You may use 'ChromeCanary' or 'Chromium' as well


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Concurrency level
    // how many browser should be started simultaneous
    concurrency: Infinity,
    plugins: [
      'karma-chrome-launcher',
      'karma-mocha',
      'karma-webpack',
      'karma-sourcemap-loader',
      'karma-spec-reporter',
    ],
  });
}
