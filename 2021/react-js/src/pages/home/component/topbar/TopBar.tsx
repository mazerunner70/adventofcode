import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Toolbar from '@material-ui/core/Toolbar';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import IconButton from '@material-ui/core/IconButton';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import SwipeableDrawer from '@material-ui/core/SwipeableDrawer';
import MenuIcon from '@material-ui/icons/Menu';
import AccountCircle from '@material-ui/icons/AccountCircleOutlined';
import HelpOutlineOutlined from '@material-ui/icons/HelpOutlineOutlined';
import NotificationsIcon from '@material-ui/icons/NotificationsOutlined';
import Badge from '@material-ui/core/Badge';
import Menu from './Menu';
import { withStyles } from '@material-ui/core/styles';
import { WithStyles, createStyles, Theme } from '@material-ui/core';

const logo = require('../../../../images/entellect.svg');

const styles = (theme: Theme) => createStyles({
  appBar: {
    position: 'relative',
    boxShadow: 'none',
    borderBottom: `1px solid ${theme.palette.grey['100']}`,
    backgroundColor: 'white',

  },
  inline: {
    display: 'inline'
  },
  flex: {
    display: 'flex',
    [theme.breakpoints.down('sm')]: {
      display: 'flex',
      justifyContent: 'space-evenly',
      alignItems: 'center'
    }
  },
  link: {
    textDecoration: 'none',
    color: 'inherit'
  },
  productLogo: {
    display: 'inline-block',
    borderLeft: `1px solid ${theme.palette.grey['A100']}`,
    marginLeft: 32,
    paddingLeft: 24
  },
  tagline: {
    display: 'inline-block',
    marginLeft: 10
  },
  iconContainer: {
    display: 'none',
    [theme.breakpoints.down('sm')]: {
      display: 'block'
    }
  },
  iconButton: {
    float: 'right'
  },
  tabContainer: {
    marginLeft: 32,
    [theme.breakpoints.down('sm')]: {
      display: 'none'
    }
  },
  tabItem: {
    minWidth: 'auto'
  },
  rightPanel: {
    display: 'flex',
    float: 'right',
    alignItems: 'right',
    [theme.breakpoints.down('sm')]: {
      display: 'none'
    }
  },
  grow: {
    flexGrow: 1,
  }
})

export interface Props  extends WithStyles<typeof styles> { 
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

    const { classes } = this.props;    

    return (
      <AppBar position="absolute" color="default" className={classes.appBar}>
        <Toolbar>
          <Grid container spacing={24} alignItems="baseline">
            <Grid item xs={12} container alignItems='baseline' className={classes.flex}>
              <div className={classes.inline}>
                <Typography variant="h6" color="inherit" noWrap>
                  <Link to='/' className={classes.link}>
                    <img width={20} src={logo} alt="no data" />
                    <span className={classes.tagline}>Advent of Code</span>
                  </Link>
                </Typography>
              </div>
              {!this.props.noTabs && (
                <React.Fragment>
                  <div className={classes.productLogo}>
                    <Typography>

                    </Typography>
                  </div>
                  <div className={classes.iconContainer}>
                    <IconButton onClick={this.mobileMenuOpen} className={classes.iconButton} color="inherit" aria-label="Menu">
                      <MenuIcon />
                    </IconButton>
                  </div>
                  <div className={classes.tabContainer}>
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
                  </div>
                  <div className={classes.grow} />
                  <div className={classes.rightPanel}>
                    <IconButton>                      
                        <HelpOutlineOutlined />
                    </IconButton>                   
                  </div>
                </React.Fragment>
              )}
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
    )
  }
}

export default withStyles(styles)(Topbar);