package modsen.pizza.productservice.service;

import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product create(ProductDtoRequest dto){
        return productRepository.save(Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build());
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
