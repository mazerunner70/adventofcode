import React, { Component } from 'react';
import { Redirect, Route, HashRouter, Switch } from 'react-router-dom'
import HomePage from './pages/home/container/Home';
// import Monitoring2 from './pages/monitoring2/container/Monitoring2';
// import Counter from './pages/counter/Counter';
// import Dedup from './pages/dedup/Dedup';

 class Routes extends Component {
  render() {
    return (

    <div>
    <HashRouter>
        <Switch>
        <Route  path='/' exact={true}  component={ HomePage } />
        {/* <Route  path='/monitoring' exact={true}  component={ Monitoring2 } />
        <Route  path='/dedup' exact={true}  component={ Dedup } />
        <Route  path='/counter' exact={true}  component={ Counter } /> */}
        <Redirect to="/" />
        </Switch>
    </HashRouter>
    </div>
    );
  }
}

export default Routes;