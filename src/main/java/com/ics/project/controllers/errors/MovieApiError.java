package com.ics.project.controllers.errors;

import com.ics.project.models.Movie;

import java.util.List;

/**
 * Subclass of ApiError class that handles specific error to do with existing movie
 *
 * @author Dr H
 */
public class MovieApiError extends ApiError {
    private Movie existingMovie;

    public MovieApiError(List<String> errors, Movie existingMovie) {
        super(errors);
        this.existingMovie = existingMovie;
    }

    public Movie getExistingMovie() {
        return existingMovie;
    }

    public void setExistingMovie(Movie existingMovie) {
        this.existingMovie = existingMovie;
    }
}
