import { useEffect, useState } from "react";
import { fetchProducts} from "../services/productsService";
import ProductCard from "../components/ProductCard";
import "../css/products.css"

export interface Product {
    id: number,
    name: string,
    description: string,
    price: number,
    categoryName: string,
    quantity?: number
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
            <h1 className="menu">Наше меню</h1>

            {Object.keys(groupedProducts).map((category) => (
                <div key={category} className="mb-12">
                    <h2 className="product-category">
                        {category === "Пиццы" ? "Сытные пиццы" : category}
                    </h2>

                    <div className="product-grid">
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