package modsen.pizza.orderitemsservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDto {
    @NotNull(message = "Order id cannot be empty!")
    private Long orderId;
    @NotNull(message = "Product id cannot be empty!")
    private Long productId;
    @NotNull(message = "Amount cannot be empty!")
    @Min(value = 1, message = "Amount cannot be 0 or negative!")
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
