import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./pages/register";
import Login from "./pages/login";
import ProtectedRoute from "./components/ProtectedRoute";
import Products from "./pages/products";
import Home from "./pages/Home";

export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<ProtectedRoute><Home /></ProtectedRoute>}/>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="products" element={<ProtectedRoute><Products /></ProtectedRoute>} />
            </Routes>
        </Router>
    );
}