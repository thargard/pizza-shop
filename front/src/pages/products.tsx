import { useEffect, useState } from "react";
import { fetchProducts} from "../services/productsService";
import ProductCard from "../components/ProductCard";

export interface Product {
    id: number,
    name: string,
    description: string,
    price: number,
    categoryName: string
}

interface GroupedProducts {
    [key : string] : any[]
}

const Products = () => {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        const loadProducts = async () => {
            const data = await fetchProducts();
            setProducts(data);
        };
        loadProducts();
    }, []);

    const groupedProducts = products.reduce((acc: GroupedProducts, product) => {
        if (!acc[product.categoryName]) acc[product.categoryName] = [];
        acc[product.categoryName].push(product);
        return acc;
    }, {});

    return (
        <div className="container mx-auto p-6">
            <h1 className="text-4xl font-bold mb-8 text-center">Наше меню</h1>

            {Object.keys(groupedProducts).map((category) => (
                <div key={category} className="mb-12">
                    <h2 className="text-3xl font-bold mt-10 mb-6 capitalize text-gray-800">
                        {category === "Пиццы" ? "Сытные пиццы" : category}
                    </h2>

                    <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
                        {groupedProducts[category].map((product) => (
                            <ProductCard key={product.id} product = { product } />
                        ))}
                    </div>

                </div>
            ))}
        </div>
        /*<div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold text-center mb-6">Наши пиццы</h1>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
                {products.map((product) => (
                    <div key={product.id} className="bg-white rounded-2xl shadow-lg p-4 flex flex-col items-center">
                        <img src="/pizza.png" alt={product.name} className="w-48 h-48 object-cover rounded-lg mb-4"/>
                        <h2 className="text-xl font-semibold">{product.name}</h2>
                        <p className="text-gray-600">{product.description}</p>
                        <p className="text-lg font-bold mt-2">{product.price} ₽</p>
                        <button className="mt-4 bg-orange-500 text-white px-4 py-2 rounded-lg hover:bg-orange-600">
                            Добавить в корзину
                        </button>
                    </div>
                ))}
            </div>
        </div>*/
    );
}

export default Products;