package modsen.pizza.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDtoRequest {
    @NotBlank(message = "Product name cannot be empty!")
    private String name;
    @NotNull(message = "Price cannot be null!")
    @Min(value = 0, message = "Price cannot be negative!")
    private double price;
    private String description;
    @NotNull(message = "Category id cannot be null!")
    private Long categoryid;

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
