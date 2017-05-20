/**
 * Created by jeffreychang on 5/2/17.
 */
import { LOGIN, LOGOUT } from 'constants';

export const userLogin = username => ({ type: LOGIN, username });
export const userLogout = () => ({ type: LOGOUT, username: null });
