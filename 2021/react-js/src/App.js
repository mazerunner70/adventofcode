import React, { Component } from 'react';
// import { ThemeProvider, createTheme } from '@mui/system';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import './App.less';
import Routes from './routes'
import { Link } from 'react-router-dom';
import { blue, indigo } from '@mui/material/colors'


const theme = createTheme({
  toggle: {
    thumbOnColor: '#ffff00',
    trackOnColor: '#ff0000'
  },
  palette: {
    secondary: {
      main: '#ff0000' //'#e17400'
    },
    primary: {
      main: indigo[500]
    }
  },
  typography: {
    // Use the system font instead of the default Roboto font.
    fontFamily: [
      '"Lato"',
      'sans-serif'
    ].join(',')
  }
});


class App extends Component {
  render() {
    return (
      <div>
        <ThemeProvider theme={theme}>
          <Routes />
        </ThemeProvider>

        <div style={{
          position: 'fixed',
          bottom: '0',
          left: '0',
          padding: '5px',
          color: 'rgba(0, 0, 0, 0.54)'
        }}> {process.env.REACT_APP_VERSION} </div>
      </div>
    );
  }
}

export default App;
