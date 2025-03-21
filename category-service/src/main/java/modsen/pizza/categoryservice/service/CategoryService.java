package modsen.pizza.categoryservice.service;

import jakarta.persistence.EntityNotFoundException;
import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import modsen.pizza.categoryservice.exceptions.CustomExceptionMessages;
import modsen.pizza.categoryservice.kafka.CategoryEventProducer;
import modsen.pizza.categoryservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryEventProducer categoryEventProducer;

    public Category create(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }

    public List<Category> readAll(){
        return categoryRepository.findAll();
    }

    public Page<Category> readAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            categoryEventProducer.sendCategoryDeleteEvent(id);
        } else {
            throw new EntityNotFoundException(CustomExceptionMessages.CATEGORY_NOT_FOUND + id);
        }
    }
}
