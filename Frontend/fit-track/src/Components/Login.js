import React from "react";
import LoginForm from "./LoginForm";
import SignUpForm from "./SignUpForm";
import Human from "../Images/Human.png";
import '../App.css'
import {useSelector} from "react-redux";

function Login(){
    const myState = useSelector((state) => state.toggleLoginState);
    return(
        <>
            <div className="background">
                <img src={Human} alt="human" className={` ${myState ? 'human' : 'humanLeft'}`}/>
            </div>
            {myState?<LoginForm />:<SignUpForm />}
        </>
    )
}

export default Login;