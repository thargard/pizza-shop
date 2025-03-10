package modsen.pizza.productservice.message;

import modsen.pizza.productservice.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping("/api/v1/categories")
    List<CategoryDto> getCategories();
}
