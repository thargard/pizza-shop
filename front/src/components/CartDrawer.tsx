import React from "react";
import { useCart } from "../services/cartContext";
import '../css/cart.css'
import {createOrder} from "../services/orderService";

interface CartDrawerProps {
    isOpen: boolean;
    onClose: () => void;
}

const CartDrawer: React.FC<CartDrawerProps> = ({ isOpen, onClose }) => {
    const { cart, removeFromCart } = useCart();

    const orderProducts = cart.map((item) => ({
        productId: item.id,
        quantity: item.quantity
    }));

    const handleCheckout = async () => {
        try {
            const response = await createOrder(orderProducts);
            console.log("Отправка заказа: ", response);
        } catch (error) {
            console.error("Ошибка при оформлении заказа!");
        }
    }

    const totalPrice = cart.reduce((sum, item) => sum + (item.quantity ? item.quantity * item.price : 0), 0);

    return (
        <div className={`cart-drawer ${isOpen ? "open" : ""}`}>
            <div className="cart-content">
                <button className="close-btn" onClick={onClose}>×</button>
                <h2>Корзина</h2>

                <div className="cart-items">
                    {cart.length === 0 ? (
                        <p>Ваша корзина пуста.</p>
                    ) : (
                        cart.map((item) => (
                            <div key={item.id} className="cart-item">
                                <h3>{item.name}</h3>
                                <p>{item.price} ₽</p>
                                <p>Количество: {item.quantity}</p>
                                <button onClick={() => removeFromCart(item.id)}>Удалить</button>
                            </div>
                        ))
                    )}
                </div>

                {cart.length > 0 && (
                    <>
                        <div className="cart-total">
                            <h3>Итого:</h3>
                            <p>{totalPrice} ₽</p>
                        </div>
                        <button className="checkout-btn" onClick={handleCheckout}>
                            Оформить заказ
                        </button>
                    </>

                )}
            </div>
        </div>
    );
};


export default CartDrawer;