package modsen.pizza.orderservice.message;

import modsen.pizza.orderservice.dto.OrderItemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "orderitems-service")
public interface OrderItemClient {
    @PostMapping("/api/v1/order-items/{id}")
    void createOrderItems(@PathVariable Long id, @RequestBody List<OrderItemRequest> items);
}
