var path = require("path");
var webpack = require('webpack');

module.exports = {
    entry: [
        "./app/index.js",
        //'webpack-dev-server/client?http://0.0.0.0:9090',
        //'webpack/hot/only-dev-server'
    ],
    output: {
        path: path.resolve(__dirname, '../../../target/classes/static'),
        //publicPath: 'http://localhost:9090/assets/',
        filename: 'bundle.js'
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin()
    ],
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
    devtool: 'source-map',
    //devServer: {
    //    historyApiFallback: true
    //}
};