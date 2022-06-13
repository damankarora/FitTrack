const initialState = true;
const toggleAboutSignUpState = (state = initialState, action) => {
    switch(action.type){
        case "TOOGLE NAVBAR STATE" : return !state;
        default : return state;
    }
}

export default toggleAboutSignUpState;