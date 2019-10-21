package com.ics.project.repositories;

import com.ics.project.models.Category;
import com.ics.project.models.Movie;
import com.ics.project.models.User;
import com.ics.project.utils.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dr H
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitleOrTitleAndDescriptionContaining(String title, String title2, String description);

    Movie findByTitle(String title);

    List<Movie> findMoviesByCategoriesAndMovieType(Category category, MovieType movieType);

    Movie findByIdAndUser(Long id, User user);
}
