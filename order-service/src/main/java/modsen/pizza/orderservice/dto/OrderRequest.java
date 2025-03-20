package modsen.pizza.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private List<OrderItemRequest> items;
    private double totalPrice;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        items.forEach((item) -> lines.add("{ ProductId = " + item.getProductId() + ", amount = " + item.getQuantity() + " }"));
        return "OrderRequest{" +
                "userId=" + userId +
                ",\n items= [\n" + String.join("\n", lines) +
                ",\n totalPrice=" + totalPrice +
                "\n]}";
    }
}
