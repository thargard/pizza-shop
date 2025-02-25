package modsen.pizza.categoryservice.controllers;

import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import modsen.pizza.categoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDto dto){
        return new ResponseEntity<>(categoryService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> readAll(){
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}
