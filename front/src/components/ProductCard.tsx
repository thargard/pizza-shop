import {Product} from "../pages/products";
import styles from "./ProductCard.module.css"
import {Component} from "react";
import Loader from "./Loader";

class ProductCard extends Component<{ product: Product }> {
    render() {
        let {product} = this.props;
        return (
            <div className={styles["productCard"]}>
                <Loader />

                {/* Название */}
                <h2 className={styles["product-name"]}>{product.name}</h2>

                {/* Описание */}
                <p className={styles["product-description"]}>{product.description}</p>

                {/* Цена */}
                <p className={styles["product-price"]}>от {product.price} руб.</p>

                {/* Кнопка выбора */}
                <button className={styles["product-button"]}>Выбрать</button>
            </div>
        );
    }
}

export default ProductCard;