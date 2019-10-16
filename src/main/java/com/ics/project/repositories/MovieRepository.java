package com.ics.project.repositories;

import com.ics.project.models.Category;
import com.ics.project.models.Movie;
import com.ics.project.utils.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitleOrDescription(String title, String description);

    List<Movie> findMoviesByCategoriesAndMovieType(Category category, MovieType movieType);
}
