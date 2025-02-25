package modsen.pizza.categoryservice.service;

import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import modsen.pizza.categoryservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public List<Category> readAll(){
        return categoryRepository.findAll();
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
