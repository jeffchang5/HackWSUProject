import React, { Component } from 'react';

import Header from 'components/Header';
import Login from 'containers/Login';

import AppWrapper from './wrapper';

const pages = [
  { title: 'Sync', url: 'http://google.com' },
  { title: 'Home', url: 'http://google.com' },
  { title: 'About', url: 'http://google.com' },
  { title: 'Login', url: 'http://google.com' },
];

export default class Application extends Component {
  render() {
    return (
      <AppWrapper>
        <Header links={pages} />
        <Login />
      </AppWrapper>
    );
  }
}
