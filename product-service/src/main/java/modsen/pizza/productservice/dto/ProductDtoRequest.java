package modsen.pizza.productservice.dto;

import lombok.Data;

@Data
public class ProductDtoRequest {
    private String name;
    private double price;
    private String description;
}
