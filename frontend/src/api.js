import axios from "axios"

const API_URL = "http://localhost:52706/api/v1"

export const getProducts = async () => {
    const response = await axios.get(`${API_URL}/products`);
    return response.data;
}

export const getCategories = async () => {
    const response = await axios.get(`${API_URL}/categories`);
    return response.data;
};