package modsen.pizza.orderservice.message;

import modsen.pizza.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "new-service")
public interface UserClient {
    @GetMapping("/api/v1/users/{id}")
    UserDto getUserById(@PathVariable Long id);
}
