package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Category;
import com.ics.project.repositories.CategoryRepository;
import com.ics.project.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dr H
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * Find all existing categories
     *
     * @return List of categories
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    /**
     * Find category by its ID
     * @param id Long
     * @return category
     * @throws ResourceNotFoundException if not found
     */
    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        Utils.print(id.toString());
        return categoryRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.createWith(id, "Category"));
    }

    /**
     * Find category by its name instead
     * @param category String
     * @return category
     */
    @Override
    public Category getCategoryByName(String category) {
        return categoryRepo.findByNameContaining(category);
    }
}
