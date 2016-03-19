var path = require('path');
var webpack = require('webpack');

module.exports = {
    entry: [
        './app/index.js'
    ],
    output: {
        path: path.resolve(__dirname, '../../../target/classes/static'),
        publicPath: '',
        filename: 'bundle.js'
    },
    module: {
        loaders: [{
            exclude: /node_modules/,
            loader: 'babel'
        }, {
            test: /\.css$/,
            loader: 'style!css'
        }]
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    devServer: {
        port: 9090,
        proxy: {
            '/*': {
                target: 'http://localhost:8080',
                secure: false,
                prependPath: false
            }
        },
        publicPath: 'http://localhost:9090/',
        historyApiFallback: true
    },
    devtool: 'source-map'
};