package com.vladik.rest.api.factory;

import com.vladik.rest.api.dto.CategoryDto;
import com.vladik.rest.store.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoFactory {
    public CategoryDto makeCategoryDto(CategoryEntity category){
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
