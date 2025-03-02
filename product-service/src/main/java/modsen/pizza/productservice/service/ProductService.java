package modsen.pizza.productservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.dto.ProductTestDto;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.message.CategoryClient;
import modsen.pizza.productservice.dto.CategoryDto;
import modsen.pizza.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        p.setCategoryid(dto.getCategoryid());
        return productRepository.save(p);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product update(Product product){
        return productRepository.save(product);
    }

    public void delete(Long id){
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product with id " + id + " not found!");
        }
    }

    public List<ProductTestDto> getProductWithCategory(Long id){
        CategoryDto dto = categoryClient.getCategoryById(id);
        List<Product> products = productRepository.getByCategoriesId(id);

        List<ProductTestDto> productTestDtos = new ArrayList<>();

        for (Product product: products){
            ProductTestDto productTestDto = new ProductTestDto(product.getName(), product.getPrice(), dto.getName());
            productTestDtos.add(productTestDto);
        }

        return productTestDtos;
    }
}
