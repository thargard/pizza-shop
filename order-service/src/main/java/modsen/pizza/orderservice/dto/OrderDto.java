package modsen.pizza.orderservice.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
