import axios from "axios";
import {getAuthHeader, getCurrentUser, getUserInfo} from "./authService";
import {Product} from "../pages/products";

const API_URL = "http://localhost:8082/api/v1/orders";

interface OrderProducts{
    productId: number,
    quantity?: number
}

interface OrderRequest{
    items: OrderProducts[],
    totalPrice: number
}

export const createOrder = async (req: OrderRequest) => {
    const userId = getUserInfo().id;
    console.log(userId);
    console.log(req);
    const items = req.items
    const totalPrice = req.totalPrice;
    try {
        const response = await axios.post(API_URL, {userId, items, totalPrice}, {headers: getAuthHeader()});
        console.log("Заказ оформлен успешно! ", response.data);
        return response;
    } catch (error) {
        console.error("Ошибка при оформлении заказа!", error);
        return [];
    }
};

export const findOrdersByUserId = async (userId: number) => {
    try {
        const response = await axios.get(API_URL + `/all/${userId}`, {headers: getAuthHeader()});
        //console.log("Данные получены! ", response.data);
        return response.data;
    } catch (error) {
        console.error("Ошибка при получении данных с сервера!", error);
        return [];
    }
}