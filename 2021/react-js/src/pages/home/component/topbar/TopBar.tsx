import React, { Component } from 'react';
import { Link } from 'react-router-dom';


import { Box, Button, Divider, IconButton, AppBar, SwipeableDrawer } from '@mui/material';
import { List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import { Tabs, Tab } from '@mui/material';
import {CssBaseline, Toolbar, Typography, Grid} from '@mui/material'; 
import {Inbox, Mail, Menu as MenuIcon, HelpOutlineOutlined} from '@mui/icons-material';
import { SxProps } from '@mui/system';
import Menu from './Menu';

// const logo = require('../../../../images/logo192.png');
const logo = require('../../../../images/entellect.svg');

// const styles = (theme: Theme) => createStyles({
//   appBar: {
//     position: 'relative',
//     boxShadow: 'none',
//     borderBottom: `1px solid ${theme.palette.grey['100']}`,
//     backgroundColor: 'white',
//   },
//   inline: {
//     display: 'inline'
//   },
//   flex: {
//     display: 'flex',
//     [theme.breakpoints.down('sm')]: {
//       display: 'flex',
//       justifyContent: 'space-evenly',
//       alignItems: 'center'
//     }
//   },
//   link,
//   productLogo: {
//     display: 'inline-block',
//     borderLeft: `1px solid ${theme.palette.grey['A100']}`,
//     marginLeft: 32,
//     paddingLeft: 24
//   },
//   tagline: {
//     display: 'inline-block',
//     marginLeft: 10
//   },
//   iconContainer: {
//     display: 'none',
//     [theme.breakpoints.down('sm')]: {
//       display: 'block'
//     }
//   },
//   iconButton: {
//     float: 'right'
//   },
//   tabContainer: {
//     marginLeft: 32,
//     [theme.breakpoints.down('sm')]: {
//       display: 'none'
//     }
//   },
//   tabItem: {
//     minWidth: 'auto'
//   },
//   rightPanel: {
//     display: 'flex',
//     float: 'right',
//     alignItems: 'right',
//     [theme.breakpoints.down('sm')]: {
//       display: 'none'
//     }
//   },
//   grow: {
//     flexGrow: 1,
//   }
// })

export interface Props { 
  currentPath: any | '/';
  noTabs?: boolean | false;
  location?: {search: any | ''};
  
}

class Topbar extends Component<Props> {

  state = {
    value: 0,
    menuDrawer: false
  };

  handleChange = (event: any, value: any) => {
    this.setState({ value });
  };

  mobileMenuOpen = (event: any) => {
    this.setState({ menuDrawer: true });
  }

  mobileMenuClose = (event: any) => {
    this.setState({ menuDrawer: false });
  }

  componentDidMount() {
    window.scrollTo(0, 0);
  }

  current = () => {
    if (this.props.currentPath === '/') {
      return 0
    }
    if (this.props.currentPath === '/monitoring') {
      return 1
    }
    if (this.props.currentPath === '/dedup') {
      return 2
    }
    if (this.props.currentPath === '/counter') {
      return 3
    }
    if (this.props.currentPath === '/upload') {
      return 4
    }
    if (this.props.currentPath === '/cards') {
      return 5
    }

  }
   handleOpen = () => {
    // setOpen(true);
  };

   handleClose = () => {
    // setOpen(false);
  };

  generateTo(path: String) {
    var x = { pathname: path};
    var result = 'to = ' + x;
   return result;
  }

  generateTo1(path: String) {
    var x = { path};
    var result = 'to = ' + path;
   return result;
  }


  render() {
    const heroText: SxProps = {     
        display: 'inline-block',
        borderLeft: '1px solid grey.100',
        marginLeft: 32,
        paddingLeft: 24
        };
    return (
        <>
        {/* <Box>HelloTopbar</Box>
        <AppBar position="static">
      <Toolbar>
        <IconButton
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={heroText}
        >
          <MenuIcon />
        </IconButton>
        <Typography variant="h6" sx={heroText}>
          Title
        </Typography>
        <Button color="inherit" onClick={handleOpen}>
          Signup
        </Button>
      </Toolbar>
      <ModalDialog open={open} handleClose={handleClose} />
    </AppBar> */}

        {/* <AppBar position="absolute" color="default" sx={{ position: 'relative',
      boxShadow: 'none',
      borderBottom: `1px solid grey.100}`,
      backgroundColor: 'white' }}>
        <Toolbar>
          <Grid container spacing={24} alignItems="baseline">
            <Grid item xs={12} container alignItems='baseline' sx={{
                sm: {
                    display: 'flex',
                    justifyContent: 'space-evenly',
                    alignItems: 'center'
                }
            }}>
                          <div >
                <Typography variant="h6" color="inherit" noWrap sx={{ display: 'inline' }}>
                    <Box  sx={heroText}>
                  <Link to='/'>
                    <img width={20} src={logo} />
                    <Box component="span" sx={{
                        display: 'inline-block',
                        marginLeft: 10
                    }}>Advent of Code</Box>
                  </Link>
                  </Box>
                </Typography>
              </div>
              {!this.props.noTabs && (
                <React.Fragment>
                  <Box sx={heroText}>
                    <Typography>

                    </Typography>
                  </Box>
                  <Box sx={{     
                      display: 'none',
                        sm: {
                            display: 'block'
                        }
                    }}>
                    <IconButton onClick={this.mobileMenuOpen} sx={{float: 'right'}} color="inherit" aria-label="Menu">
                      <MenuIcon />
                    </IconButton>
                  </Box>
                  <Box sx={{     marginLeft: 32,
                        sm: {
                           display: 'none'
                        }
                    }}>
                    <SwipeableDrawer anchor="right" open={this.state.menuDrawer} onClose={this.mobileMenuClose} onOpen={f=>f}>
                      <AppBar title="Menu" />
                      <List>
                      {Menu.map((item, index) => (
                          <ListItem component={Link as any} to={item.pathname} button key={index}><ListItemText primary={item.label}>Tree</ListItemText></ListItem>))}
                        {/* {Menu.map((item, index) => (
                          <ListItem  component={Link as any} {...this.generateTo(item.pathname)} to={item.pathname} button key={index}>
                            <ListItemText primary={item.label} />
                          </ListItem>
                        ))} 
                      </List>
                    </SwipeableDrawer>
                                </Box>
                  
                  </React.Fragment>)}
            </Grid>
            </Grid>
        </Toolbar>


      </AppBar> */}
      {/* <AppBar position="absolute" color="default" sx={{ position: 'relative',
      boxShadow: 'none',
      borderBottom: `1px solid grey.100}`,
      backgroundColor: 'white' }}>
        <Toolbar>
          <Grid container spacing={24} alignItems="baseline">
            <Grid item xs={12} container alignItems='baseline' sx={{
                sm: {
                    display: 'flex',
                    justifyContent: 'space-evenly',
                    alignItems: 'center'
                }
            }}>
              <div >
                <Typography variant="h6" color="inherit" noWrap sx={{ display: 'inline' }}>
                    <Box  sx={heroText}>
                  <Link to='/'>
                    <img width={20} src={logo} alt="no data" />
                    <Box component="span" sx={{
                        display: 'inline-block',
                        marginLeft: 10
                    }}>Advent of Code</Box>
                  </Link>
                  </Box>
                </Typography>
              </div>
              {!this.props.noTabs && (
                <React.Fragment>
                  <Box sx={heroText}>
                    <Typography>

                    </Typography>
                  </Box>
                  <Box sx={{     
                      display: 'none',
                        sm: {
                            display: 'block'
                        }
                    }}>
                    <IconButton onClick={this.mobileMenuOpen} sx={{float: 'right'}} color="inherit" aria-label="Menu">
                      <MenuIcon />
                    </IconButton>
                  </Box>
                  <Box sx={{     marginLeft: 32,
                        sm: {
                           display: 'none'
                        }
                    }}>
                    <SwipeableDrawer anchor="right" open={this.state.menuDrawer} onClose={this.mobileMenuClose} onOpen={f=>f}>
                      <AppBar title="Menu" />
                      <List>
                        {Menu.map((item, index) => (
                          <ListItem  component={Link as any} {...this.generateTo(item.pathname)} to={item.pathname} button key={index}>
                            <ListItemText primary={item.label} />
                          </ListItem>
                        ))}
                      </List>
                    </SwipeableDrawer>
                    <Tabs
                      value={this.current() || this.state.value}
                      indicatorColor="primary"
                      textColor="primary"
                      onChange={this.handleChange}
                    >
                      {Menu.map((item, index) => (
                        <Tab  id={item.label} component={Link as any} {...this.generateTo(item.pathname)} to={item.pathname}  label={item.label} />
                      ))}
                    </Tabs>
                  </Box>
                  <Box sx={{flexGrow: 1}}/>
                  <Box sx={{     display: 'flex',
                    float: 'right',
                    alignItems: 'right',
                    sm: {
                       display: 'none'
                    }}}>
                    <IconButton>                      
                        <HelpOutlineOutlined />
                    </IconButton>                   
                  </Box>
                </React.Fragment>
              )}
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar> */}
      </>
    )
  }
}

export default Topbar;