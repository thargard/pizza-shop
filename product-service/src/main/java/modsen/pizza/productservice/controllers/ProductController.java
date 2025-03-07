package modsen.pizza.productservice.controllers;

import jakarta.validation.Valid;
import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.dto.ProductDtoResponse;
import modsen.pizza.productservice.dto.ProductTestDto;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.mapper.ProductMapper;
import modsen.pizza.productservice.message.CategoryName;
import modsen.pizza.productservice.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> create(@Valid @RequestBody ProductDtoRequest dto){
        return new ResponseEntity<>(productMapper.mapProduct(productService.create(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> readAll(){
        return new ResponseEntity<>(productMapper.mapProductList(productService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDtoResponse> readById(@PathVariable Long id){
        return new ResponseEntity<>(modelMapper.map(productService.findById(id), ProductDtoResponse.class), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductDtoResponse>> readByCategory(@RequestBody CategoryName category){
        List<Product> prods = productService.findByCategory(category);
        List<ProductDtoResponse> dtos = productMapper.mapProductList(prods);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDtoResponse> update(@RequestBody Product product){
        return new ResponseEntity<>(productMapper.mapProduct(productService.update(product)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        productService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/items")
    public ResponseEntity<List<ProductTestDto>> getProduct(){
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
