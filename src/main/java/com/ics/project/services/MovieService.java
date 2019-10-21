package com.ics.project.services;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Movie;

import java.util.List;

/**
 * @author Dr H
 */
public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(Long id) throws ResourceNotFoundException;

    Movie suggestMovie(Movie movie) throws ResourceExistsException, ResourceNotFoundException;

    List<Movie> getMoviesByCategoryAndType(Long id, String movieType) throws ResourceNotFoundException;

    String delete(Long id, String user) throws ResourceNotFoundException;
}
