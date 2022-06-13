import React from "react";
import '../App.css'
import {useDispatch} from "react-redux";
import {toggleState} from "../Redux/Action/Action.js"

function LoginForm(){
    const dispatch = useDispatch();
    return(
        <>
            <div className="loginAreaOverlay"></div>
            <div className="loginArea">
                <div className="loginHeader">Log In</div>
                <input className="email" type="text" id="email" name="email" placeholder="Email"/><br/><br/>
                <input className="password" type="password" id="password" name="password" placeholder="Password"/><br/><br/>
                <button className="loginButton">Login</button>
                <div className="forgot">Forgot Pasword?</div>
                <div className="dontHave">Don't Have Account? <span onClick={() => dispatch(toggleState())}>Sign Up</span></div>
            </div>
        </>
    )
}

export default LoginForm;