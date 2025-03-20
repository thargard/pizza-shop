import axios from "axios";
import {getAuthHeader} from "./authService";
import {Product} from "../pages/products";

const API_URL = "http://localhost:8082/api/v1/products/items";

export const fetchProducts = async () => {
    try {
        const response = await axios.get(API_URL, { headers : getAuthHeader() });

        console.log("products from service - ", response.data);
        return response.data.sort((a: Product, b: Product) => {
            if (a.categoryName === "Пиццы" && b.categoryName !== "Пиццы") return -1;
            if (b.categoryName === "Пиццы" && a.categoryName !== "Пиццы") return 1;
            return 0;
        });
    } catch (error) {
        console.error("Ошибка при загрузке продуктов: ", error);
        return [];
    }
};