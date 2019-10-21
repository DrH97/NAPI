package com.ics.project.repositories;

import com.ics.project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dr H
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNameContaining(String category);
}
