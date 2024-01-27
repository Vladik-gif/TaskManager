package com.vladik.rest.api.service.impl;

import com.vladik.rest.api.dto.CategoryDto;
import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.factory.CategoryDtoFactory;
import com.vladik.rest.api.factory.DeleteDtoFactory;
import com.vladik.rest.api.service.CategoryService;
import com.vladik.rest.store.entities.CategoryEntity;
import com.vladik.rest.store.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoFactory categoryDtoFactory;
    private final DeleteDtoFactory deleteDtoFactory;

    @Override
    public CategoryDto createCategory(CategoryEntity category) {
        return categoryDtoFactory.makeCategoryDto(categoryRepository.save(category));
    }
    @Override
    public DeleteDto deleteByIdCategory(Long id) {
        categoryRepository.deleteById(id);

        return deleteDtoFactory.makeDeleteDto(true);
    }
}
