package modsen.pizza.productservice.mapper;

import modsen.pizza.productservice.dto.ProductDtoResponse;
import modsen.pizza.productservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public ProductDtoResponse mapProduct(Product product) {
        ProductDtoResponse productDtoResponse = new ProductDtoResponse();
        productDtoResponse.setName(product.getName());
        productDtoResponse.setDescription(product.getDescription());
        productDtoResponse.setPrice(product.getPrice());
        return productDtoResponse;
    }

    public List<ProductDtoResponse> mapProductList(List<Product> products) {
        List<ProductDtoResponse> productDtoResponses = new ArrayList<>();
        for (Product product : products) {
            ProductDtoResponse productDtoResponse = new ProductDtoResponse();
            productDtoResponse.setName(product.getName());
            productDtoResponse.setDescription(product.getDescription());
            productDtoResponse.setPrice(product.getPrice());
            productDtoResponses.add(productDtoResponse);
        }
        return productDtoResponses;
    }
}
