package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Movie;
import com.ics.project.repositories.MovieRepository;
import com.ics.project.utils.MovieType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepo;
    private CategoryService categoryService;

    public MovieServiceImpl(MovieRepository movieRepo, CategoryService categoryService) {
        this.movieRepo = movieRepo;
        this.categoryService = categoryService;
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
    public Movie suggestMovie(Movie movie) throws ResourceExistsException {

        Movie existingMovie = movieRepo.findByTitleOrDescription(movie.getTitle(), movie.getDescription());

        if (existingMovie == null) {
            movie.setMovieType(MovieType.SUGGESTED);
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
