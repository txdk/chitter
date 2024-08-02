export default function postReducer(state, action) {
    switch (action.type) {
        case "create": {
            return {...state, posts: [...state.posts, action.payload]};
        }
        case "sync": {
            return {...state, posts: action.payload};
        }
        default:
            return state;
    }
}