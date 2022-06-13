import toggleLoginState from "./ToggleState";
import toggleAboutSignUpState from "./ToggleNavbarState";
import { combineReducers } from "redux";

const RootReducer = combineReducers({
    toggleLoginState,
    toggleAboutSignUpState
});

export default RootReducer;