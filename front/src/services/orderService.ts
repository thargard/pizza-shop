import axios from "axios";
import {getUserInfo} from "./authService";
import {Product} from "../pages/products";
import {OrderProducts} from "../pages/cart";

const API_URL = "http://localhost:8082/api/v1/orders";

export  const createOrder = async (products: OrderProducts[]) => {
    const userId = getUserInfo().id;
    try {
        const response = await axios.post(API_URL, {userId, products});
    } catch (error) {
        console.error("Ошибка при оформлении заказа! ", error);
        return [];
    }
    return [];
};