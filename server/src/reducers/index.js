import { combineReducers } from 'redux';

import app from './app';
import queue from './queue';

export default combineReducers({ app, queue });