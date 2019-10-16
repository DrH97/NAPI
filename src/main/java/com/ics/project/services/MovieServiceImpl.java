package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Category;
import com.ics.project.models.Movie;
import com.ics.project.models.User;
import com.ics.project.repositories.MovieRepository;
import com.ics.project.utils.MovieType;
import com.ics.project.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepo;
    private CategoryService categoryService;
    private UserService userService;

    public MovieServiceImpl(MovieRepository movieRepo, CategoryService categoryService, UserService userService) {
        this.movieRepo = movieRepo;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws ResourceNotFoundException {
        return movieRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.createWith(id, "Movie"));
    }

    @Override
    public Movie suggestMovie(Movie movie) throws ResourceExistsException, ResourceNotFoundException {

        Movie existingMovie = movieRepo.findByTitleContainingOrDescriptionContaining(movie.getTitle(), movie.getDescription());

        Utils.print(movie.toString());
        if (movie.getUser() == null) {
            throw ResourceNotFoundException.createWith("null", "User");
        }

        User existingUser = userService.me(movie.getUser().getId());

        if (existingMovie == null) {
            Movie newMovie = new Movie(movie.getTitle(), movie.getDescription());

            if (movie.getCategories() != null) {
                Category category = categoryService.getCategoryByName(movie.getCategories().get(0).getName());
                Utils.print(category.toString());
                newMovie.setCategories(Collections.singletonList(category));
            }

            newMovie.setMovieType(MovieType.SUGGESTED);
            return movieRepo.save(movie);
        }

        throw ResourceExistsException.createWith(existingMovie);
    }

    @Override
    public List<Movie> getMoviesByCategoryAndType(Long id, String type) throws ResourceNotFoundException {
        MovieType movieType;

        try {
            movieType = MovieType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw ResourceNotFoundException.createWith(type, "MovieType");
        }

        return movieRepo.findMoviesByCategoriesAndMovieType(categoryService.getCategoryById(id), movieType);
    }
}
