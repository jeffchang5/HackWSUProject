import { Map } from 'immutable';
import { LOG_IN, LOG_OUT } from 'constants';

const initialState = Map({
  isLoggedIn: false,
  currentUser: null,
});

const app = (state = initialState, action) => {
  switch (action.type) {
    case LOG_IN:
      return state.update('isLoggedIn', true);
    case LOG_OUT:
      return state.update('isLoggedIn', false);
    default:
      return state;
  }
};
export default app;