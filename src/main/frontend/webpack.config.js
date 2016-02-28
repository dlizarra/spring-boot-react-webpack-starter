module.exports = {
  entry: [
	"./app/index.js"
  ],
  output: {
    path: '../../../target/classes/static',
    publicPath: '/',
    filename: 'bundle.js'
  },
  module: {
  	loaders: [{
  	  exclude: /node_modules/,
  	  loader: 'babel'  		
  	}, {
      test: /\.css$/, // Only .css files
      loader: 'style!css' // Run both loaders
    }]
  },
  resolve: {
  	extensions: ['', '.js', '.jsx']
  },
  devServer: {
  	historyApiFallback: true,
  	contentBase: './'
  }
};