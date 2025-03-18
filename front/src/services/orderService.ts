import axios from "axios";
import {getAuthHeader, getCurrentUser, getUserInfo} from "./authService";
import {Product} from "../pages/products";

const API_URL = "http://localhost:8082/api/v1/orders";

interface OrderProducts{
    productId: number,
    quantity?: number
}

export const createOrder = async (items: OrderProducts[]) => {
    const userId = getUserInfo().id;
    console.log(userId);
    console.log(items);
    try {
        const response = await axios.post(API_URL, {userId, items}, {headers: getAuthHeader()});
        console.log("Заказ оформлен успешно! ", response.data);
        return response;
    } catch (error) {
        console.error("Ошибка при оформлении заказа!", error);
        return [];
    }
};