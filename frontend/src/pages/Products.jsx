import { useEffect, useState } from "react";
import { getProducts } from "../api";
import { useDispatch } from "react-redux";
import { addToCart } from "../store/cartSlice";

function Products() {
    const [products, setProducts] = useState([]);
    const dispatch = useDispatch();

    useEffect(() => {
        getProducts().then(setProducts);
    }, []);

    return (
        <div className="p-10">
            <h1 className="text-2xl font-bold">Меню</h1>
            <div className="grid grid-cols-3 gap-4">
                {products.map((product) => (
                    <div key={product.id} className="p-4 border rounded-lg">
                        <h2 className="text-xl">{product.name}</h2>
                        <p className="text-gray-500">{product.categoryName}</p>
                        <p className="font-bold">${product.price}</p>
                        <button
                            className="mt-2 bg-green-500 text-white px-4 py-2 rounded-lg"
                            onClick={() => dispatch(addToCart(product))}
                        >
                            В корзину
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Products;