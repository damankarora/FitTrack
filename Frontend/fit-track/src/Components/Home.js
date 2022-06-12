import React from "react";
import SvgComponent from "./SvgComponent";
import Data from "./Data";
import { BiUser,BiChevronDown } from "react-icons/bi";
import FitFood  from '../Images/FitFood.png'
import HumanStanding from '../Images/HumanStanding.png'
import '../App.css'

function Home(){
    return(
        <>
        <div className="navbar">
            <div>Fit Track</div>
            <div>Home</div>
            <div>About Us</div>
            <div>Daman <BiUser className="userIcon"/> <BiChevronDown className="downArrow"/></div>
        </div>
        <div className="homeContainer">
            <div className="activity">
                <div className="graphicalData inline"><Data /></div>
                <img src={FitFood} alt="food" className="foodImage inline" />
                <img src={HumanStanding} alt="human" className="humanStanding inline" />
                <div className="clearBoth"></div>
            </div>
            <div>
                <div className="header">User Details</div>
                <div className="useractivity">
                    <div>Name : <span>Damnapreet Gill</span></div>
                    <div>Gender : <span>Male</span></div>
                    <div>Height : <span>5'11''</span></div>
                    <div>Weight : <span>67 Kg</span></div>
                </div>
            </div>
            <div>
            <div className="card">
            </div>
            </div>
            <div className="activityLog">
                <div className="header">Activity Log</div>
                <div className="userProgress">
                    <div><SvgComponent /> <span className="log">900 Of 1800 cal</span></div>
                    <div><SvgComponent /> <span className="log">2 Of 8 Glasses</span></div>
                    <div><SvgComponent /> <span className="log">10,000 Of 10,000 Steps</span></div>
                    <div><SvgComponent /> <span className="log">130 Of 360 Cal</span></div>
                    <div><SvgComponent /> <span className="log">62 Kg Set Weight Goal</span></div>
                </div>
            </div>
        </div>
        </>
    )
}

export default Home;