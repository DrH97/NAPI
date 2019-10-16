package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Movie;
import com.ics.project.utils.MovieType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(Long id) throws ResourceNotFoundException;

    Movie suggestMovie(Movie movie) throws ResourceExistsException;

    List<Movie> getMoviesByCategoryAndType(Long id, String movieType) throws ResourceNotFoundException;
}
