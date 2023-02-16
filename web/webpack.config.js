const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),
  ],
  optimization: {
    usedExports: true
  },
  entry: {
    index: path.resolve(__dirname, 'src', 'pages', 'index.js'),
    startingWeights : path.resolve(__dirname, 'src', 'pages', 'startingWeights.js'),
    upcomingWorkouts : path.resolve(__dirname, 'src', 'pages', 'upcomingWorkouts.js'),
    redirect: path.resolve(__dirname, 'src', 'pages', 'redirect.js'),
    currentWorkout: path.resolve(__dirname, 'src', 'pages', 'currentWorkout'),
    workoutHistory: path.resolve(__dirname, 'src', 'pages', 'workoutHistory'),
    squatProgress: path.resolve(__dirname, 'src', 'pages', 'squatProgress'),
    benchPressProgress: path.resolve(__dirname, 'src', 'pages', 'benchPressProgress'),
    barbellRowProgress: path.resolve(__dirname, 'src', 'pages', 'barbellRowProgress'),
    overheadPressProgress: path.resolve(__dirname, 'src', 'pages', 'overheadPressProgress'),
    deadliftProgress: path.resolve(__dirname, 'src', 'pages', 'deadliftProgress')
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
