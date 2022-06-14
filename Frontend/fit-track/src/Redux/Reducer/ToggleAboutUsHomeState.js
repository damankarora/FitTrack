const initialState = true;
const toggleAboutUSHomeState = (state = initialState, action) => {
    switch(action.type){
        case "TOOGLE HOME STATE" : return !state;
        default : return state;
    }
}

export default toggleAboutUSHomeState;