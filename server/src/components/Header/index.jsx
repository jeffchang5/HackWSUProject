// @flow
import React from 'react';
import Navigation from './menu';
import Wrapper from './wrapper';

export default ({ links }: [{ title: string, url: string }]) => (
  <Wrapper>
    <Navigation links={links} />
  </Wrapper>
);
