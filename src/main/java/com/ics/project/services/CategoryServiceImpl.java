package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Category;
import com.ics.project.repositories.CategoryRepository;
import com.ics.project.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        Utils.print(id.toString());
        return categoryRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.createWith(id, "Category"));
    }
}
