import axios from "axios";

const API_URL = "http://localhost:8082/api/v1/auth/";

export const register = async (username: string, email: string, password: string) => {
    return axios.post(API_URL + "register", {email, username, password});
}

export const login = async (username: string, password: string) => {
    logout();
    const response = await axios.post(API_URL + "token", {username, password});
    console.log("Ответ от сервера:", response.data);
    if (response.data){
        localStorage.setItem("user", JSON.stringify(response.data));
    } else {
        console.error("Ошибка: токен отсутствует в ответе");
    }
    return response.data;
}

export const getUserData = async () => {
    const user  = await axios.get(API_URL + "userFromToken", {params : {token: getCurrentUser()}});
    console.log(user.data.email);
    if (user.data){
        console.log("Пользователь пришел в целости и сохранности!")
        deleteUserInfo();
        localStorage.setItem("userdata", JSON.stringify(user.data));
    } else {
        console.error("Пользователь не пришел!:(")
    }
    return user.data
}

export const logout =  () => {
    localStorage.removeItem("user");
    deleteUserInfo()
}

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user") || "{}");
}

export const deleteUserInfo = () => {
    localStorage.removeItem("userdata");
}

export const getUserInfo = () => {
    return JSON.parse(localStorage.getItem("userdata") || "{}");
}

export const getAuthHeader = () => {
    const token = getCurrentUser();
    return token ? { Authorization: `Bearer ${token}` } : {}
}