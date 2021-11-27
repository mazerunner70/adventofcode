import React, { Component } from 'react';
import {Box, CssBaseline, Toolbar, Typography, Grid} from '@mui/material'; 

export default function Topbar2( ) {
    return (
        <React.Fragment>
        <CssBaseline /> 
        <Grid container spacing={2}>
<Grid item xs={12}>
s=8
<div>line1</div>

</Grid>
<Grid item xs={12}>
xs=4
</Grid>
<Grid item xs={12}>
xs=6
<Box sx= {{
                     flexGrow: 1,
                        backgroundColor: 'grey.100',
                        // background: `url(${backgroundShape}) no-repeat`,
                        backgroundSize: 'cover',
                        backgroundPosition: '0 200px',
                       paddingBottom: 200
        }}>Hello World</Box>

</Grid>
</Grid>
    </React.Fragment>
    )
}