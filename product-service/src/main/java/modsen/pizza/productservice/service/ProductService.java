package modsen.pizza.productservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.dto.ProductTestDto;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.message.CategoryClient;
import modsen.pizza.productservice.dto.CategoryDto;
import modsen.pizza.productservice.message.CategoryName;
import modsen.pizza.productservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

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

    public Product findById(Long id){
        if (productRepository.existsById(id)){
            return productRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Product with id " + id + " not found!");
        }
    }

    public List<Product> findByCategory(CategoryName category){
        List<CategoryDto> dtos = categoryClient.getCategories();

        List<Product> products = new ArrayList<>();
        for (CategoryDto dto: dtos){
            if (dto.getName().equalsIgnoreCase(category.getCategoryName())){
                for (Product product: getAll()){
                    if (dto.getId().equals(product.getCategoryid())){
                        products.add(product);
                    }
                }
            }
        }
        return products;
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

    public List<ProductTestDto> findAll(){
        List<CategoryDto> dtos = categoryClient.getCategories();
        List<Product> products = productRepository.findAll();

        List<ProductTestDto> productTestDtos = new ArrayList<>();

        for (Product product: products){
            ProductTestDto productTestDto = modelMapper.map(product, ProductTestDto.class);
            //new ProductTestDto(product.getId(), product.getName(), product.getPrice(), dto.getName());
            for (CategoryDto dto: dtos){
                if (product.getCategoryid().equals(dto.getId())){
                    productTestDto.setCategoryName(dto.getName());
                    break;
                }
            }
            productTestDtos.add(productTestDto);
        }
        return productTestDtos;
    }
}
