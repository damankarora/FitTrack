import { legacy_createStore as createStore } from 'redux';
import RootReducer from './Reducer/RootReducer.js';

const Store = createStore(RootReducer);

export default Store;