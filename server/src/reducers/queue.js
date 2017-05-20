const queue = (state = { LoggedIn: false }, action) => {
  switch (action.type) {
    case 'LOG_IN':
      return {
        id: action.id,
        text: action.text,
        completed: false,
      };
    case 'LOG_OUT':
      if (state.id !== action.id) {
        return state;
      }

      return Object.assign({}, state, {
        completed: !state.completed,
      });

    default:
      return state;
  }
};
export default queue;