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
    );
}

export default Products;