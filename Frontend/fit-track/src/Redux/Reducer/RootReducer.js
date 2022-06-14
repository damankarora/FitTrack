import toggleLoginState from "./ToggleState";
import toggleAboutSignUpState from "./ToggleNavbarState";
import toggleLogOutState from "./ToggleLogOutState";
import toggleAboutUSHomeState from "./ToggleAboutUsHomeState";
import { combineReducers } from "redux";

const RootReducer = combineReducers({
    toggleLoginState,
    toggleAboutSignUpState,
    toggleLogOutState,
    toggleAboutUSHomeState
});

export default RootReducer;