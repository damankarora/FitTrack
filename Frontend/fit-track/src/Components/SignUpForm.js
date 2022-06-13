import React from "react";
import '../App.css'
import {useDispatch} from "react-redux";
import {toggleState} from "../Redux/Action/Action.js"

function SignUpForm(){
    const dispatch = useDispatch();
    return(
        <>
            <div className="loginAreaOverlay"></div>
            <div className="loginArea">
                <div className="loginHeader">Sign Up</div>
                <input className="signUpName" type="text" id="name" name="name" placeholder="Name"/><br/><br/>
                <input className="signUpEmail" type="text" id="email" name="email" placeholder="Email"/><br/><br/>
                <input className="signUpPassword" type="password" id="password" name="password" placeholder="Password"/><br/><br/>
                <button className="signUpButton">Sign Up</button>
                <div className="alreadyHave">Already Have Account? <span onClick={() => dispatch(toggleState())}>Login In</span></div>
            </div>
        </>
    )
}

export default SignUpForm;