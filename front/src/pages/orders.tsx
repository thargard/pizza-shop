import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {Product} from "./products";
import "../css/orders.css"
import {payForOrder} from "../services/orderService";

interface OrderItem {
    productId: number;
    quantity: number;
}

export interface Order {
    id: number;
    userId: number;
    totalPrice: number;
    paid: boolean;
    createdAt: string;
    items: OrderItem[];
}

interface ComplexOrder extends Order {
    products: Product[]
}

interface OrdersPageProps {
    orders?: Order[];
    products?: Product[];
}

const OrdersPage: React.FC<OrdersPageProps> = ({ orders, products }: OrdersPageProps) => {
    //const [orders, setOrders] = useState<Order[]>([]);
    //const [products, setProducts] = useState<Product[]>([]);
    const [complexOrders, setComplexOrders] = useState<ComplexOrder[]>([]);
    const navigate = useNavigate();

    /*useEffect(() => {
        const loadOrders = async () => {
            const response =  await findOrdersByUserId(getUserInfo().id);
            setOrders(response);
        };

        const loadProducts = async () => {
            const data = await fetchProducts();
            setProducts(data);
        }
        loadProducts();
        loadOrders();
    }, []);*/

    useEffect(() => {
        if (!orders!.length || !products!.length) return;

        const newComplexOrders: ComplexOrder[] = orders!.map((order) => {
            const prods: Product[] = order.items.map((smallPr) => {
                const product = products!.find((p) => p.id === smallPr.productId);
                if (!product) return null;
                return {...product, quantity: smallPr.quantity };
            }).filter(Boolean) as Product[];

            return {
                ...order,
                products: prods,
            };
        });

        setComplexOrders(newComplexOrders);
    }, [orders, products]);

    console.log("Сложный заказ - ", complexOrders)

    const handlePayment = async (orderId: number) => {
        try {
            const data = await payForOrder(orderId);
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <div className="orders-container">
            <h1 className="orders-title">Мои заказы</h1>
            {complexOrders.length === 0 ? (
                <p className="no-orders">Вы еще не сделали ни одного заказа.</p>
            ) : (
                <div className="orders-list">
                    {complexOrders.map((order) => (
                        <div key={order.id} className="order-card">
                            <h3 className="order-header">Заказ #{order.id}</h3>
                            <p className="order-total">Дата: {new Date(order.createdAt).toLocaleDateString()}</p>
                            <p className={`order-status ${order.paid ? 'paid' : 'unpaid'}`}>
                                {order.paid ? "Оплачен" : "Ожидает оплаты"}
                            </p>
                            <ul className="order-items">
                                {order.products.map((item, index) => (
                                    <li key={index} className="order-item">
                                        <span>{item.name} (x{item.quantity})</span>
                                        <span>{(item.quantity ? item.price * item.quantity : 0)} ₽</span>
                                    </li>
                                ))}
                            </ul>
                            <p className="order-total">Итого: {order.totalPrice} ₽</p>
                            {!order.paid && (
                                <button className="pay-button" onClick={() => handlePayment(order.id)}>
                                    Оплатить
                                </button>
                            )}
                        </div>
                    ))}
                </div>
            )}
            <button onClick={() => navigate("/products")}>← Вернуться в магазин</button>
        </div>
    );
};

export default OrdersPage;