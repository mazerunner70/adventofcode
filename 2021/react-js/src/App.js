import React, { Component } from 'react';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import './App.less';
import Routes from './routes'
import { Link } from 'react-router-dom';
import { blue, indigo } from '@material-ui/core/colors'


const theme = createMuiTheme({
  palette: {
    secondary: {
      main: '#e17400'
    },
    primary: {
      main: indigo[700]
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
        <MuiThemeProvider theme={theme}>
          <Routes />
        </MuiThemeProvider>

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
