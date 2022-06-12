import React from "react";
import Circle from "../Images/Circle.png";
import UpperLeftEclipse from "../Images/UpperLeftEclipse.png";
import UpperRightEclipse from "../Images/UpperRightEclipse.png";
import LowerLeftEclipse from "../Images/LowerLeftEclipse.png";
import Human from "../Images/Human.png";
import '../App.css'

function Login(){
    return(
        <>
            <div className="background">
                <img src={Circle} alt="circle" className="circle" />
                <img src={UpperLeftEclipse} alt="upperLeftEclipse" className="upperLeftEclipse" />
                <img src={UpperRightEclipse} alt="upperRightEclipse" className="upperRightEclipse" />
                <img src={LowerLeftEclipse} alt="lowerLeftEclipse" className="lowerLeftEclipse" />
                <img src={Human} alt="human" className="human" />
            </div>
            <div className="overlay"></div>
            <div className="loginNav">
                <div>Fit Track</div>
                <div>Home</div>
                <div>About Us</div>
                <div>Sign Up</div>
            </div>
            <div className="loginAreaOverlay"></div>
            <div className="loginArea">
                <div className="loginHeader">Log In</div>
                <input className="email" type="text" id="email" name="email" placeholder="Email"/><br/><br/>
                <input className="password" type="text" id="password" name="password" placeholder="Password"/><br/><br/>
                <button className="loginButton">Login</button>
                <div className="forgot">Forgot Pasword?</div>
                <div className="dontHave">Don't Have Account? <span>Sign Up</span></div>
            </div>
        </>
    )
}

export default Login;
