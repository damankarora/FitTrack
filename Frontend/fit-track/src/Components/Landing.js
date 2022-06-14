import React from "react";
import Circle from "../Images/Circle.png";
import UpperLeftEclipse from "../Images/UpperLeftEclipse.png";
import UpperRightEclipse from "../Images/UpperRightEclipse.png";
import LowerLeftEclipse from "../Images/LowerLeftEclipse.png";
import Login from "./Login";
import AboutUs from "./AboutUs";
import '../App.css'
import { toggleNavbarState } from "../Redux/Action/Action.js";
import {useSelector,useDispatch} from "react-redux";
import swal from 'sweetalert';

function Landing(){

    const navbarState = useSelector((state) => state.toggleAboutSignUpState);
    console.log(navbarState);
    const dispatch = useDispatch();

    const  loginAlert = () =>{
        if(navbarState === false){ 
            dispatch(toggleNavbarState());  
            swal({
                title: "Login Please",
                button: "Okay",
            });
        }
        else{
            swal({
                title: "Login Please",
                button: "Okay",
            });
        }
    }

    const  aboutUsStateHandler = () =>{
        if(navbarState === true){
            dispatch(toggleNavbarState());
        }
    }

    const stateHandler = () =>{
        if(navbarState === false){
            dispatch(toggleNavbarState());
        }
    }
    return(
        <>
            <div className="background">
                <img src={Circle} alt="circle" className="circle" />
                <img src={UpperLeftEclipse} alt="upperLeftEclipse" className="upperLeftEclipse" />
                <img src={UpperRightEclipse} alt="upperRightEclipse" className="upperRightEclipse" />
                <img src={LowerLeftEclipse} alt="lowerLeftEclipse" className="lowerLeftEclipse" />
            </div>
            <div className="loginNav">
                <div onClick={() => window.location.reload()}>Fit Track</div>
                <div onClick={loginAlert}>Home</div>
                <div onClick={aboutUsStateHandler}>About Us</div>
                <div onClick={stateHandler}>Log In</div>
            </div>
            <div className="overlay"></div>
            {navbarState?<Login />:<AboutUs />}
        </>
    )
}

export default Landing;
