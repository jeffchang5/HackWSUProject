import React from 'react';
import { expect } from 'chai';
import { shallow, mount, render } from 'enzyme';
import Application from 'containers/Application';
import Header from 'components/Header';

describe('A suite', () => {
  it('contains spec with an expectation', () =>
    expect(shallow(<Application />).contains(<Header />)).to.equal(true));

  it('contains spec with an expectation', () =>
    expect(shallow(<Application />).is('.foo')).to.equal(true));

  it('contains spec with an expectation', () =>
    expect(mount(<Application />).find('.foo').length).to.equal(1));
});
