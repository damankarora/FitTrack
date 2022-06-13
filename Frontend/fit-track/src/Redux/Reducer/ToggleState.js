const initialState = true;
const toggleLoginState = (state = initialState, action) => {
    switch(action.type){
        case "TOOGLE STATE" : return !state;
        default : return state;
    }
}

export default toggleLoginState;