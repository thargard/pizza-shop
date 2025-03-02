package modsen.pizza.categoryservice.mapper;

import modsen.pizza.categoryservice.dto.CategoryDto;
import modsen.pizza.categoryservice.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public CategoryDto map(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<CategoryDto> mapList(List<Category> categories){
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

}
