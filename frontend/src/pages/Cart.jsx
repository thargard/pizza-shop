import { useSelector, useDispatch } from "react-redux";
import { removeFromCart } from "../store/cartSlice";

function Cart() {
    const cart = useSelector((state) => state.cart);
    const dispatch = useDispatch();

    return (
        <div className="p-10">
            <h1 className="text-2xl font-bold">Корзина</h1>
            {cart.length === 0 ? <p>Корзина пуста</p> : cart.map((item) => (
                <div key={item.id} className="flex justify-between p-4 border rounded-lg">
                    <p>{item.name} - ${item.price}</p>
                    <button className="bg-red-500 text-white px-4 py-2" onClick={() => dispatch(removeFromCart(item.id))}>
                        Удалить
                    </button>
                </div>
            ))}
        </div>
    );
}

export default Cart;