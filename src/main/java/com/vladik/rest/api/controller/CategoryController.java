package com.vladik.rest.api.controller;


import com.vladik.rest.api.dto.CategoryDto;
import com.vladik.rest.api.dto.DeleteDto;
import com.vladik.rest.api.service.CategoryService;
import com.vladik.rest.store.entities.CategoryEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private static final String CATEGORIES_DELETE_ID = "/delete/{id}";

    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CategoryEntity category){
        return categoryService.createCategory(category);
    }

    @DeleteMapping(CATEGORIES_DELETE_ID)
    public DeleteDto deleteAllCategories(@PathVariable Long id){
        return categoryService.deleteByIdCategory(id);
    }
}