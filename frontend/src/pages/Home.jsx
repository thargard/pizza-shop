import { Link } from "react-router-dom"

function Home() {
    return (
        <div className="text-center p-10">
            <h1 className="text-3xl font-bold">Добро пожаловать в нашу пиццерию! 🍕</h1>
            <Link to="/products" className="mt-5 inline-block bg-red-500 text-white px-4 py-2 rounded-lg">
                Перейти к меню
            </Link>
        </div>
    );
}

export default Home;