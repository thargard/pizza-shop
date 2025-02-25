package modsen.pizza.productservice.dto;

import lombok.Data;

@Data
public class ProductDtoResponse {
    private String name;
    private double price;
    private String description;
}
