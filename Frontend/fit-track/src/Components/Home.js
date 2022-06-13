import React from "react";
import SvgComponent from "./SvgComponent";
import Data from "./Data";
import { BiUser,BiChevronDown } from "react-icons/bi";
import { ImSpoonKnife } from "react-icons/im";
import { GiGlassShot } from "react-icons/gi";
import { IoFootsteps } from "react-icons/io5";
import { FaRunning, FaWeight} from "react-icons/fa";
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
                    <div><SvgComponent stroke="#f0525a"/><ImSpoonKnife className="insideCircle"/></div>
                    <div className="log">900 Of 1800 cal</div>
                    <div><SvgComponent stroke="#ffce00"/><GiGlassShot className="insideCircle"/></div>
                    <div className="log">2 Of 8 Glasses</div>
                    <div><SvgComponent stroke="#4166F5"/><IoFootsteps className="insideCircle"/></div>
                    <div className="log">10,000 Of 10,000 Steps</div>
                    <div><SvgComponent stroke="#367aa4"/><FaRunning className="insideCircle"/></div>
                    <div className="log">130 Of 360 Cal</div>
                    <div><SvgComponent stroke="#272727"/><FaWeight className="insideCircle"/></div>
                    <div className="log">62 Kg Set Weight Goal</div>
                </div>
            </div>
        </div>
        </>
    )
}

export default Home;