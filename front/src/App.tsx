import React from 'react';
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import './App.css';
//import AppRoutes from "./routes";
import {CartProvider} from "./services/cartContext";
import Login from "./pages/login";
import Register from "./pages/register";
import Products from "./pages/products";
import ProtectedRoute from "./components/ProtectedRoute";
import CartPage from "./pages/cart";
import Home from "./pages/Home";
import Logout from "./pages/logout";

function App() {
  return (
      <CartProvider>
          <Router>
              <nav>
                  <Link to="/">Главная</Link>
                  <Link to="/products">Товары</Link>
                  <Link to="/cart">Корзина</Link>
                  <Link to="/login">Войти</Link>
                  <Link to="/register">Зарегистрироваться</Link>
                  <Link to="/logout">Выйти</Link>
              </nav>
              <Routes>
                  <Route path="/" element={<Home />}/>
                  <Route path="/login" element={<Login />}/>
                  <Route path="/register" element={<Register />}/>
                  <Route path="/products" element={<ProtectedRoute><Products /></ProtectedRoute>}/>
                  <Route path="/cart" element={<ProtectedRoute><CartPage /></ProtectedRoute>}/>
                  <Route path="/logout" element={<ProtectedRoute><Logout /></ProtectedRoute>}/>
              </Routes>
          </Router>
      </CartProvider>
    /*<div>
      <h1 className="pizzashop-title">Пиццерия</h1>
      <AppRoutes />
    </div>*/
  );
}

export default App;
