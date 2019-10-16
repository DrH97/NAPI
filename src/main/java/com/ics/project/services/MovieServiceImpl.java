package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.controllers.exceptions.UserNotFoundException;
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
        List<Movie> movies = movieRepo.findAll();
        return movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws ResourceNotFoundException {
        return movieRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.createWith(id, "Movie"));
    }

    @Override
    public Movie suggestMovie(Movie movie) throws ResourceExistsException, ResourceNotFoundException {

        Movie existingMovie = movieRepo.findByTitle(Utils.titleCaseConversion(movie.getTitle()));

        if (movie.getUser() == null) {
            throw ResourceNotFoundException.createWith("", "User");
        }

        User existingUser = null;

        if (movie.getUser().getId() != null) {
            existingUser = userService.me(movie.getUser().getId());
        } else if (movie.getUser().getIdNumber() != null) {
            existingUser = userService.byIdNumber(movie.getUser().getIdNumber());
        }

        if (existingUser == null) {
            throw UserNotFoundException.createWith(movie.getUser().getIdNumber(), "User");
        }

        if (existingMovie == null) {
            Movie newMovie = new Movie(movie.getTitle(), movie.getDescription());

            if (movie.getCategories() != null) {
                Category category = categoryService.getCategoryByName(movie.getCategories().get(0).getName());

                if (category != null) {
                    newMovie.setCategories(Collections.singletonList(category));
                }
            }

            newMovie.setUser(existingUser);

            newMovie.setMovieType(MovieType.SUGGESTED);
            return movieRepo.save(newMovie);
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

    @Override
    public String delete(Long id, String user) throws ResourceNotFoundException {
        if (user == null || user.isEmpty()) {
            throw ResourceNotFoundException.createWith("", "User");
        }

        User existingUser = null;

        existingUser = userService.byIdNumber(user);

        if (existingUser == null) {
            try {
                existingUser = userService.me(Long.valueOf(user));
            } catch (NumberFormatException e) {
                throw UserNotFoundException.createWith(user, "User");
            }
        }

        if (existingUser == null) {
            throw UserNotFoundException.createWith(user, "User");
        }

        Movie userMovie = movieRepo.findByIdAndUser(id, existingUser);

        if (userMovie == null) {
            throw ResourceNotFoundException.createWith("", "Movie " + id);
        }

        movieRepo.deleteById(id);

        return "Movie deleted successfully";
    }
}
