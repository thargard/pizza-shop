import React, {useEffect, useState} from 'react';
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import './App.css';
import {CartProvider} from "./services/cartContext";
import Login from "./pages/login";
import Register from "./pages/register";
import Products, {Product} from "./pages/products";
import ProtectedRoute from "./components/ProtectedRoute";
import Home from "./pages/Home";
import Logout from "./pages/logout";
import CartDrawer from "./components/CartDrawer";
import OrdersPage, {Order} from "./pages/orders";
import {fetchProducts} from "./services/productsService";
import {findOrdersByUserId} from "./services/orderService";
import {getUserInfo} from "./services/authService";


function App() {
    const [isCartOpen, setCartOpen] = useState(false);
    const [orders, setOrders] = useState<Order[]>([]);
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        fetchOrders();
        fetchGlobalProducts();
    }, []);

    const fetchOrders = async () => {
        const data = await findOrdersByUserId(getUserInfo().id);
        setOrders(data);
    };

    const fetchGlobalProducts = async () => {
        const data = await fetchProducts();
        setProducts(data);
    };

  return (
      <CartProvider>
          <Router>
              <nav>
                  <Link to="/">Главная</Link>
                  <Link to="/products">Товары</Link>
                  <button onClick={() => setCartOpen(true)}>🛒 Корзина</button>
                  <Link to="/orders">История заказов</Link>
                  <Link to="/login">Войти</Link>
                  <Link to="/register">Зарегистрироваться</Link>
                  <Link to="/logout">Выйти</Link>
              </nav>
              <Routes>
                  <Route path="/" element={<Home/>}/>
                  <Route path="/login" element={<Login/>}/>
                  <Route path="/register" element={<Register/>}/>
                  <Route path="/products" element={<ProtectedRoute><Products/></ProtectedRoute>}/>
                  <Route path="/orders" element={<ProtectedRoute><OrdersPage orders={orders} products={products}/></ProtectedRoute>}/>
                  <Route path="/logout" element={<ProtectedRoute><Logout/></ProtectedRoute>}/>
              </Routes>
          </Router>
          <CartDrawer isOpen={isCartOpen} onClose={() => setCartOpen(false)}/>
      </CartProvider>
  );
}

export default App;
