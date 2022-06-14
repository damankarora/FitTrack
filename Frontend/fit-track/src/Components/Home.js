import React from "react";
import { BiUser,BiChevronDown } from "react-icons/bi";
import '../App.css'
import {useSelector,useDispatch} from "react-redux";
import { hideShowState } from "../Redux/Action/Action";
import { toggleHomeState } from "../Redux/Action/Action";
import LogOut from "./LogOut";
import MainUi from "./MainUi";
import AboutUs from "./AboutUs";

function Home(){

    const logOutState = useSelector((state) => state.toggleLogOutState);
    const toggleHome = useSelector((state) => state.toggleAboutUSHomeState);
    const dispatch = useDispatch();

    const  hideShowHandler = () =>{
        dispatch(hideShowState());
    }

    return(
        <>
        <div className="navbar">
            <div onClick={() => window.location.reload()}>Fit Track</div>
            <div onClick={() => dispatch(toggleHomeState())}>Home</div>
            <div onClick={() => dispatch(toggleHomeState())}>About Us</div>
            <div>Daman <BiUser className="userIcon"/> <BiChevronDown className="downArrow" onClick={hideShowHandler}/></div>
        </div>
        <div>
            {logOutState?<LogOut />:''}
        </div>
        {toggleHome?<MainUi />:<AboutUs />}
        </>
    )
}

export default Home;