import {Product} from "../pages/products";
import '../css/productCard.css'
import Loader from "./Loader";
import {useCart} from "../services/cartContext";
import NumberInput from "./NumberInput";
import {useState} from "react";

interface ProductCardProps {
    product: Product
}

const ProductCard: React.FC<{product : Product }> = ({ product }: ProductCardProps) => {
    const { addToCart } = useCart();
    const [quantity, setQuantity] = useState(1);

    const orderProduct = {...product, quantity}

    return (
        <div className="product-card">
            <Loader />
            <h2 className="product-name">{product.name}</h2>
            <p className="product-description">{product.description}</p>
            <p className="product-price">{product.price} ₽</p>
            <NumberInput value={quantity} setValue={setQuantity}/>
            <button onClick={() => addToCart(orderProduct)} className="product-button">Добавить в корзину</button>
        </div>
    );
};

export default ProductCard;