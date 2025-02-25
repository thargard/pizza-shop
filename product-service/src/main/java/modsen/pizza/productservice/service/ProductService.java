package modsen.pizza.productservice.service;

import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.dto.ProductTestDto;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.message.CategoryClient;
import modsen.pizza.productservice.dto.CategoryDto;
import modsen.pizza.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryClient categoryClient;

    public Product create(ProductDtoRequest dto){
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        return productRepository.save(p);
        /*return productRepository.save(Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build());*/
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

    public ProductTestDto getProductWithCategory(Long id){
        CategoryDto dto = categoryClient.getCategoryById(id);
        Product product = productRepository.getByCategoryId(id);


        ProductTestDto test = new ProductTestDto(product.getName(), product.getPrice(), dto.getName());
        System.out.println(test.toString());
        return test;
    }
}
