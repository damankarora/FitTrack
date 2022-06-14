const initialState = false;
const toggleLogOutState = (state = initialState, action) => {
    switch(action.type){
        case "TOOGLE LOGOUT STATE" : return !state;
        default : return state;
    }
}

export default toggleLogOutState;