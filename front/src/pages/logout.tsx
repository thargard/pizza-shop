import {logout} from "../services/authService";
import {useNavigate} from "react-router-dom";


export default function Logout(){
    const navigate = useNavigate();

    const handleLogout = (e: React.FormEvent) => {
        e.preventDefault()
        try {
            logout()
            console.log("Выход из акаунта!")
            navigate("/login");
        } catch (error) {
            console.error("Не удалось выйти! ", error);
        }
    };

    return (
        <div>
            <h2>Точно хотите выйти?</h2>
            <form onSubmit={handleLogout}>
                <button type="submit">Выйти</button>
            </form>
        </div>
    );
}