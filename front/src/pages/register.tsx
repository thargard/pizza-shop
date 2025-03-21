import {useState} from "react";
import {register} from "../services/authService";
import {Link, useNavigate} from "react-router-dom";
import "../css/auth.css"


export default function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleRegister = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await register(username, email, password);
            alert("Регистрация успешна! Теперь войдите в аккаунт.")
            navigate("/login")
        } catch (error){
            alert("Ошибка регистрации!")
        }
    };

    return (
        <div className="auth-container">
            <h2>Регистрация</h2>
            <form onSubmit={handleRegister} className="auth-form">
                <input type="text" placeholder="Имя" value={username} onChange={(e) => setUsername(e.target.value)}
                       required/>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}
                       required/>
                <input type="password" placeholder="Пароль" value={password}
                       onChange={(e) => setPassword(e.target.value)} required/>
                <button type="submit">Зарегистрироваться</button>
            </form>
            <p className="register-link">
                Уже есть аккаунт? <Link to="/login">Войти</Link>
            </p>
        </div>
    );
}