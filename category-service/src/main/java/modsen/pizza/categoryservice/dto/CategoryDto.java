package modsen.pizza.categoryservice.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
