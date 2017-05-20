// @flow
import Immutable from 'immutable';
import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import Application from 'containers/Application';
import rootReducer from 'reducers';

const store = createStore(rootReducer,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(
    { serialize: { immutable: Immutable } }));

render(
  <Provider store={store}>
    <Application />
  </Provider>,
document.getElementById('app'));

