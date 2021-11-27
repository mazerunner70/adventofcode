import React, { Component } from 'react';
import { Link } from 'react-router-dom';


import { Box, Button, Divider, IconButton, AppBar, SwipeableDrawer } from '@mui/material';
import { ToggleButtonGroup, ToggleButton, ListItemIcon, ListItemText } from '@mui/material';
import { Tabs, Tab } from '@mui/material';
import {CssBaseline, Toolbar, Typography, Grid} from '@mui/material'; 
import {Inbox, Mail, Menu as MenuIcon, HelpOutlineOutlined} from '@mui/icons-material';
import { SxProps } from '@mui/system';
import Menu from './Menu';

// const logo = require('../../../../images/logo192.png');
const logo = require('../../../../images/entellect.svg');

export default function Topbar2( ) {
  const [selectedIndex, setSelectedIndex] = React.useState("2020");
  function handleToggleChange(index: React.SetStateAction<string>) {
    console.log(index)
    if (index !== null)
      setSelectedIndex(index);
  }
  return (
    <AppBar position="static">
  <Toolbar variant="dense" sx={{backgroundColor: 'orange'}}>
    <IconButton edge="start" color="inherit" aria-label="menu" sx={{ mr: 2 }}>
      <MenuIcon />
    </IconButton>
    <Box>
    <Typography variant="h6" color="inherit" component="div" sx={{
                        display: 'inline-block',
                        marginLeft: 5, marginRight: 5
                    }}>
      Advent of Code
    </Typography>
    </Box>
    <Box>

    </Box>
    <ToggleButtonGroup onChange={(e, value) => handleToggleChange(value)} value={selectedIndex}
id="date-select" exclusive={true} size="small" >
                {Menu.map((item, index) => (
                <ToggleButton value={item.label} >
                <Typography variant="h6" color="white" component="div" sx={{
                                                display: 'inline-block',
                                                marginLeft: 5, marginRight:5
                                            }}>
                        {item.label}
                        </Typography></ToggleButton>))    }
</ToggleButtonGroup>

  </Toolbar>
</AppBar>
  )
}
