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

    /**
     * Find all existing movies
     *
     * @return List of movies
     */
    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepo.findAll();
        return movieRepo.findAll();
    }

    /**
     * Find movie by ID
     * @param id Long
     * @return movie
     * @throws ResourceNotFoundException if no movie found
     */
    @Override
    public Movie getMovieById(Long id) throws ResourceNotFoundException {
        return movieRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.createWith(id, "Movie"));
    }

    /**
     * Suggest movie by a user
     * @param movie Movie
     * @return movie
     * @throws ResourceExistsException if suggested movie already exists
     * @throws ResourceNotFoundException if the user is not provided or found.
     */
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

    /**
     * Find movies by category and type
     * @param id of category
     * @param type of movie as MovieType
     * @return List of movies
     * @throws ResourceNotFoundException if provided movie type is not found.
     */
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

    /**
     * Delete a movie by a user
     * @param id of movie to delete
     * @param user the user performing the deletion
     * @return String specifying result of deletion
     * @throws ResourceNotFoundException if user provided does not exist, or is not the owner of the movie, or the movie does not exist
     */
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
