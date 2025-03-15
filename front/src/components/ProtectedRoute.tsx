import { Navigate } from "react-router-dom";
import { getCurrentUser } from "../services/authService";
import {JSX} from "react";

export default function ProtectedRoute({ children }: { children: JSX.Element }) {
    return getCurrentUser().token ? children : <Navigate to="/login" />;
}