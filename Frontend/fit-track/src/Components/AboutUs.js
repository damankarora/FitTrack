import React from "react";
import AboutHuman from "../Images/AboutHuman.png"
import '../App.css'
import {useSelector} from "react-redux";

function AboutUs(){
    const toggleHome = useSelector((state) => state.toggleAboutUSHomeState);
    return(
        <>
            <div className="aboutUs">
                <h3 className={` ${toggleHome ? 'aboutHeader' : 'aboutHeaderTop'}`}>About Us</h3>
                <div className="aboutUsContent">
                    Being healthy means not only away from diseases but it also means the complete feeling of physical, mental and social well being. Maintaining health and fitness is not a simple task however; not so tough too. We need to involve ourselves into some daily physical activities together with the healthy, fresh and timely food which can only provide us long-term health and fitness benefits. We burn extra and unnecessary calories to our body through physical exercises. Daily physical exercises keep kids so active and put them on a path of better physical and mental health.

                    Physical activities along with the proper nutrition are really very beneficial to the people of all ages from any background and abilities. Being involved in the daily physical exercises is a good strategy to defeat obesity. According to the statistics, it is found that around one-third of the U.S. adult citizens are obese and almost 17% of children and adolescents are obese. There are many people suffering from the diabetes and other chronic health problems such as heart disease, high blood pressure, cancer, asthma, overweight, etc.

                    We can be fit just by incorporating some physical activities including healthy eating to our daily life for 30 minutes (for adults) and 60 minutes (for children). Regular physical activities help us to get long-term health benefits through:
                </div>
                <img src={AboutHuman} alt="aboutHuman" className="aboutHuman" />
            </div>
        </>
    )
}

export default AboutUs;