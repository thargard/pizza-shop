package modsen.pizza.orderitemsservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    //@NotNull(message = "Id cannot be null!")
    private Long id;
    //@Column(name = "order_id")
    //@NotNull(message = "Order id cannot be empty!")
    private Long orderId;
    //@Column(name = "product_id")
    //@NotNull(message = "Product id cannot be empty!")
    private Long productId;
    //@Column(name = "amount")
    //@NotNull(message = "Amount cannot be empty!")
    @Min(value = 1, message = "Amount cannot be 0 or negative!")
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int amount) {
        this.quantity = amount;
    }
}
