export default function postReducer(state, action) {
    switch (action.type) {
        case "create": {
            return {...state, posts: [...state.posts, action.payload]};
        }
        case "sync": {
            return {...state, posts: action.payload};
        }
        case "edit": {
            return {
                ...state,
                posts: state.posts.map((post) => 
                    post.id === action.payload.id ? action.payload : post
                )
            }
        }
        default:
            return state;
    }
}