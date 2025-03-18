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

            <h2 className="text-3xl font-bold text-center mt-12 mb-6">Популярные пиццы</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
                {products.map((product) => (
                    <div key={product.id} className="bg-white rounded-2xl shadow-lg p-4 flex flex-col items-center">
                        <img src="/pizza.png" alt={product.name} className="w-48 h-48 object-cover rounded-lg mb-4" />
                        <h2 className="text-xl font-semibold">{product.name}</h2>
                        <p className="text-gray-600">{product.description}</p>
                        <p className="text-lg font-bold mt-2">{product.price} ₽</p>
                        <button className="mt-4 bg-orange-500 text-white px-4 py-2 rounded-lg hover:bg-orange-600">
                            Заказать
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Home;