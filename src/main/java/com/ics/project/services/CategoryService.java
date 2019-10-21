package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Category;

import java.util.List;

/**
 * @author Dr H
 */
public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id) throws ResourceNotFoundException;

    Category getCategoryByName(String category);

}
