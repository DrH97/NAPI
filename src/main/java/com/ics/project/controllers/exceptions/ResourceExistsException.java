package com.ics.project.controllers.exceptions;

import com.ics.project.models.Movie;

public class ResourceExistsException extends Exception {
    private Movie movie;

    public static ResourceExistsException createWith(Movie movie) {
        return new ResourceExistsException(movie);
    }

    private ResourceExistsException(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String getMessage() {
        return "Movie already exists";
    }

    public Movie getMovie() {
        return movie;
    }
}
