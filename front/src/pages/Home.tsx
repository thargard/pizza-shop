import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { fetchProducts } from "../services/productsService"

interface Product {
    id: number,
    name: string,
    description: string,
    price: number,
    categoryName: string
}

const Home = () => {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        const loadProducts = async () => {
            const data = await fetchProducts();
            setProducts(data.slice(0, 4)); // Показываем только 4 топовых пиццы
        };
        loadProducts();
    }, []);

    return (
        <div className="container mx-auto p-6">
            <div className="bg-orange-500 text-white text-center py-20 rounded-xl shadow-lg">
                <h1 className="text-5xl font-bold">Добро пожаловать в нашу пиццерию! 🍕</h1>
                <p className="mt-4 text-lg">Лучшие пиццы в городе – быстро и вкусно!</p>
                <div className="mt-6">
                    <Link to="/products" className="bg-white text-orange-500 px-6 py-3 rounded-lg text-lg font-semibold shadow-md hover:bg-gray-200">
                        Перейти к меню
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Home;