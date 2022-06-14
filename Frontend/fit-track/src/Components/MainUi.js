import React from "react";
import '../App.css'
import { ImSpoonKnife } from "react-icons/im";
import { GiGlassShot } from "react-icons/gi";
import { IoFootsteps } from "react-icons/io5";
import { FaRunning, FaWeight} from "react-icons/fa";
import FitFood  from '../Images/FitFood.png'
import HumanStanding from '../Images/HumanStanding.png'
import Data from "./Data";
import { CircularProgressbarWithChildren, buildStyles, CircularProgressbar } from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';

function MainUi(){
    return(
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
                    <div className="progressbarSize">
                        <CircularProgressbarWithChildren value={30} styles={buildStyles({pathColor: "#f0525a",trailColor: "#fff"})}><ImSpoonKnife className="insideCircle" /></CircularProgressbarWithChildren>
                        <span className="circle"></span>
                    </div>
                    <div className="log">900 Of 1800 cal</div>
                    <div className="progressbarSize">
                        <CircularProgressbarWithChildren value={30} styles={buildStyles({pathColor: "#ffce00",trailColor: "#fff"})}><GiGlassShot className="insideCircle"/></CircularProgressbarWithChildren>
                        <span className="circle one "></span>
                    </div>
                    <div className="log">2 Of 8 Glasses</div>
                    <div className="progressbarSize">
                        <CircularProgressbarWithChildren value={30} styles={buildStyles({pathColor: "#0000FF",trailColor: "#fff"})}><IoFootsteps className="insideCircle"/></CircularProgressbarWithChildren>
                        <span className="circle two"></span>
                    </div>
                    <div className="log">10,000 Of 10,000 Steps</div>
                    <div className="progressbarSize">
                        <CircularProgressbarWithChildren value={30} styles={buildStyles({pathColor: "#367aa4",trailColor: "#fff"})}><FaRunning className="insideCircle"/></CircularProgressbarWithChildren>
                        <span className="circle three"></span>
                    </div>
                    <div className="log">130 Of 360 Cal</div>
                    <div className="progressbarSize">
                        <CircularProgressbarWithChildren value={30} styles={buildStyles({pathColor: "#272727",trailColor: "#fff"})}><FaWeight className="insideCircle"/></CircularProgressbarWithChildren>
                        <span className="circle four"></span>
                    </div>
                    <div className="log">62 Kg Set Weight Goal</div>
                </div>
            </div>
        </div>
    )
}

export default MainUi;