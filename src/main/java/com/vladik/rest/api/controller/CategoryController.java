package com.vladik.rest.api.controller;


import com.vladik.rest.api.dto.CategoryDto;
import com.vladik.rest.api.service.CategoryService;
import com.vladik.rest.store.entities.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public CategoryDto create(@RequestBody CategoryEntity category){
        return categoryService.createCategory(category);
    }
}