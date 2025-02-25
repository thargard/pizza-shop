package modsen.pizza.categoryservice.controllers;

import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import modsen.pizza.categoryservice.mapper.CategoryMapper;
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
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto){
        return new ResponseEntity<>(categoryMapper.map(categoryService.create(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> readAll(){
        return new ResponseEntity<>(categoryMapper.mapList(categoryService.readAll()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@RequestBody Category category){
        return new ResponseEntity<>(categoryMapper.map(categoryService.update(category)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}
