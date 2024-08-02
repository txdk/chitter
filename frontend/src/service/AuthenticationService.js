import axios from "axios";
import { APIConstants } from "../constants/api";

const api = axios.create({
    baseURL: APIConstants.BACKEND_BASE_URL
});

const register = (userDetails) => api.post("/register", {
    username: userDetails.username,
    password: userDetails.password,
    role: "USER"
});

const login = (loginDetails) => api.post("/login", {
    username: loginDetails.username,
    password: loginDetails.password
})

export { register, login };