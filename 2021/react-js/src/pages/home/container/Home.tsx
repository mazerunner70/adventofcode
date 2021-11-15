import React, { Component, setGlobal } from 'reactn';


import { withStyles, WithStyles } from '@material-ui/core/styles';
import { createStyles, Theme, Switch, IconButton } from '@material-ui/core';

const styles = (theme: Theme) => createStyles({
    rootFacet: {
        width: '100%',
        maxWidth: 390,
        backgroundColor: theme.palette.background.paper,
    },
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.grey['100'],
        // background: `url(${backgroundShape}) no-repeat`,
        backgroundSize: 'cover',
        backgroundPosition: '0 200px',
        paddingBottom: 200,
    },
});

export interface Props extends WithStyles<typeof styles> {
    location: any;

}

export interface State {
    dataSource: string;
    tabValue: number;
}

class HomePage extends Component<Props, State> {
    constructor(props: Props) {
        super();
        this.state = {
            dataSource: '',
            tabValue: 0
        };
    };
    render() {
        const { classes } = this.props;
        return (
            <React.Fragment>
                <div className={classes.root}>Hello World</div>
            </React.Fragment>
        )
    }

}

export default withStyles(styles)(HomePage);