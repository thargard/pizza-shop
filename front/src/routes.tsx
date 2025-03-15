import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./pages/register";
import Login from "./pages/login";
import ProtectedRoute from "./components/ProtectedRoute";
//import Dashboard from "./pages/dashboard";

export default function AppRoutes() {
    return (
        <Router>
            <Routes>
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                {/*<Route path="/dashboard" element={<ProtectedRoute><Dashboard /></ProtectedRoute>} />*/}
            </Routes>
        </Router>
    );
}