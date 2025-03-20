import {useEffect, useState} from "react";
import {getUserData, login} from "../services/authService";
import {Link, useNavigate} from "react-router-dom";
import "../css/auth.css"

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await login(username, password);
            navigate("/");
        } catch (error) {
            alert("Ошибка входа!");
        }
    };

    return (
        <div className="auth-container">
            <h2>Вход</h2>
            <form onSubmit={handleLogin} className="auth-form">
                <input type="text" placeholder="Имя" value={username} onChange={(e) => setUsername(e.target.value)}
                       required/>
                <input type="password" placeholder="Пароль" value={password}
                       onChange={(e) => setPassword(e.target.value)} required/>
                <button type="submit">Войти</button>
            </form>
            <p className="register-link">
                Нет аккаунта? <Link to="/register">Зарегистрироваться</Link>
            </p>
        </div>
    );
}