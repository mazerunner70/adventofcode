import React, { Component } from 'react';

import { Box, Grid, Theme, Switch, IconButton } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';

import TopBar2 from '../component/topbar/TopBar2';
import Sidebar3 from '../component/sidebar/Sidebar3';

// const styles = (theme: Theme) => createStyles({
//     rootFacet: {
//         width: '100%',
//         maxWidth: 390,
//         backgroundColor: theme.palette.background.paper,
//     },
//     root: {
//         flexGrow: 1,
//         backgroundColor: theme.palette.grey['100'],
//         // background: `url(${backgroundShape}) no-repeat`,
//         backgroundSize: 'cover',
//         backgroundPosition: '0 200px',
//         paddingBottom: 200,
//     },
// });

export interface Props {
    location: any;

}

export interface State {
    dataSource: string;
    tabValue: number;
}

class HomePage extends Component<Props, State> {
    constructor(props: Props) {
        super(props);
        this.state = {
            dataSource: '',
            tabValue: 0
        };
    };
    render() {
        const currentPath = this.props.location.pathname
        return (
            <React.Fragment>
                <CssBaseline /> 
                <Grid container spacing={2}>
  <Grid item xs={12}>
    s=8
    <div><TopBar2 /></div>
    
  </Grid>
  <Grid item xs={12}>
    xs=4
    <Sidebar3/> 
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

}

export default HomePage;