import {useCart} from "../services/cartContext";
import {Product} from "./products";
import {useState} from "react";
import {createOrder} from "../services/orderService";
import {useNavigate} from "react-router-dom";

export interface OrderProducts {
    productId: number,
    quantity: number
}

const CartPage = () => {
    const { cart, removeFromCart} = useCart();
    const navigate = useNavigate()

    /*const handler = async (e : React.FormEvent) => {
        e.preventDefault()
        try {
            //я вообще не уверен насчет того что это работает
            cart.forEach((product) => {
                let op = new OrderProducts();
                op.productId = product.product.id;
                op.amount = product.quantity; // короче в корзине добавить параметр кол-ва и здесь присваивать походу
                products.push()
            });

            await createOrder(products);
            cart.forEach(() => {cart.pop()}) //? Типо надо удалить из корзины все
            navigate("/");
        } catch (error) {
            console.error(error);
        }
    };*/

    return (
        <div>
            <h1 className="cart-title">Корзина</h1>
            {cart.length === 0 ? (
                <p>Ваша корзина пуста.</p>
            ) : (
                cart.map((item: Product) => (
                    <div key={item.id} className="cart-item">
                        <h3>{item.name}</h3>
                        <p>{item.price} ₽</p>
                        <p>{item.quantity}</p>
                        <button onClick={() => removeFromCart(item.id)}>Удалить</button>
                    </div>
                ))
            )}
            {/*<form onSubmit={handler}>
                <button type="submit">Заказать</button>
            </form>*/}
        </div>
    );
};

export default CartPage;