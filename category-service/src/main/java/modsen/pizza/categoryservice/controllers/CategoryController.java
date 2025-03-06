package modsen.pizza.categoryservice.controllers;

import jakarta.validation.Valid;
import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import modsen.pizza.categoryservice.mapper.CategoryMapper;
import modsen.pizza.categoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto dto){
        return new ResponseEntity<>(categoryMapper.map(categoryService.create(dto)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> readAll(){
        return new ResponseEntity<>(categoryMapper.mapList(categoryService.readAll()), HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<Page<CategoryDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size){
        Page<Category> categoryPage = categoryService.readAll(PageRequest.of(page, size));
        List<CategoryDto> categoryDtos = categoryMapper.mapList(categoryPage.getContent());

        Page<CategoryDto> response = new PageImpl<>(categoryDtos, categoryPage.getPageable(), categoryPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> readById(@PathVariable Long id){
        return new ResponseEntity<>(categoryMapper.map(categoryService.findById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> update(@Valid @RequestBody Category category){
        return new ResponseEntity<>(categoryMapper.map(categoryService.update(category)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id){
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}
