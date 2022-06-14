import React from "react";
import '../App.css'
import {useDispatch} from "react-redux";
import {toggleState} from "../Redux/Action/Action.js"

function SignUpForm(){
    const dispatch = useDispatch();
    return(
        <>
            <div className="signUpAreaOverlay"></div>
            <div className="signUpArea">
                <div className="signUpHeader">Sign Up</div>
                <input className="signUpName" type="text" id="name" name="name" placeholder="Name"/><br/><br/>
                <input className="signUpEmail" type="text" id="email" name="email" placeholder="Email"/><br/><br/>
                <div className="signUpGender">
                    <label>Gender :</label>
                    <input type="radio" id="html" name="fav_language" value="HTML" />
                    <label for="html">Male</label>
                    <input type="radio" id="css" name="fav_language" value="CSS" />
                    <label for="css">Female</label><br></br>
                </div>
                <input className="signUpHeight" type="number" id="height" name="height" placeholder="Height"/><br/><br/>
                <input className="signUpWeight" type="number" id="weight" name="weight" placeholder="Weight"/><br/><br/>
                <input className="signUpPassword" type="password" id="password" name="password" placeholder="Password"/><br/><br/>
                <input className="signUpConfirmPassword" type="password" id="password" name="password" placeholder="Confirm Password"/><br/><br/>
                <button className="signUpButton">Sign Up</button>
                <div className="alreadyHave">Already Have Account? <span onClick={() => dispatch(toggleState())}>Login In</span></div>
            </div>
        </>
    )
}

export default SignUpForm;