import {createContext, ReactNode, useContext, useState} from "react";
import {Product} from "../pages/products";

interface CartContextType {
    cart: Product[];
    addToCart: (product: Product) => void;
    removeFromCart: (id: number) => void;
}

interface CartProviderProps {
    children: ReactNode;
}

export const CartContext = createContext<CartContextType | undefined>(undefined);

export const useCart = () => {
    const context = useContext(CartContext);
    if (!context){
        throw new Error("useCart must be used within a CartProvider");
    }
    return context;
};

export const CartProvider : React.FC<CartProviderProps> = ({ children }) => {
    const [cart, setCart] = useState<Product[]>([]);

    const addToCart = ( product : Product ) => {
        setCart((prevCart) => {
            if(!prevCart) return [];
            const existingProduct = prevCart.find((item) => item.id === product.id)

            if(existingProduct) {
                return prevCart.map((item) =>
                    item.id === product.id ? {...item, quantity: (item.quantity || 1) + (product.quantity || 1)} : item
                );
            }
            return [...prevCart, {...product, quantity: product.quantity || 1 }];
        });
    };

    const removeFromCart = (id : number) => {
        setCart((prevCart) => prevCart.filter((item : Product) => item.id !== id));
    };

    return (
        <CartContext.Provider value={{ cart, addToCart, removeFromCart}}>{children}</CartContext.Provider>
    );
};