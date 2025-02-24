package modsen.pizza.productservice.controllers;

import modsen.pizza.productservice.dto.ProductDtoRequest;
import modsen.pizza.productservice.dto.ProductDtoResponse;
import modsen.pizza.productservice.entity.Product;
import modsen.pizza.productservice.mapper.ProductMapper;
import modsen.pizza.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    private ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDtoResponse> create(@RequestBody ProductDtoRequest dto){
        return new ResponseEntity<>(productMapper.mapProduct(productService.create(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> readAll(){
        return new ResponseEntity<>(productMapper.mapProductList(productService.getAll()), HttpStatus.OK);
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
}
