package com.vladik.rest.api.service;

import com.vladik.rest.api.dto.CategoryDto;
import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.store.entities.CategoryEntity;

public interface CategoryService {
    CategoryDto createCategory(CategoryEntity category);
    DeleteDto deleteByIdCategory(Long id);
}