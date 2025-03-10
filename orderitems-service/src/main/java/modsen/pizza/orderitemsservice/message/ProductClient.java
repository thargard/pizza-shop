package modsen.pizza.orderitemsservice.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/v1/products/items")
    List<ComplexProductDto> getProducts();

    @GetMapping("/api/v1/products/get/{id}")
    OrderProductDto getProductById(@PathVariable Long id);
}
