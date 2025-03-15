import axios from "axios";

const API_URL = "http://localhost:8082/api/v1/auth/";

export const register = async (username: string, email: string, password: string) => {
    return axios.post(API_URL + "register", {email, username, password});
}

export const login = async (username: string, password: string) => {
    const response = await axios.post(API_URL + "token", {username, password});
    if (response.data.token){
        localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
}

export const logout =  () => {
    localStorage.removeItem("user");
}

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user") || "{}");
}