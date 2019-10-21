package com.ics.project.controllers;

import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Category;
import com.ics.project.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expose all categories endpoints and route them accordingly
 *
 * @author Dr H
 */
@RestController
@RequestMapping("categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "{id}")
    Category getCategoryById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return categoryService.getCategoryById(id);
    }
}
