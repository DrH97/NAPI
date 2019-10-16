package com.ics.project.controllers;

import com.ics.project.controllers.exceptions.ResourceExistsException;
import com.ics.project.controllers.exceptions.ResourceNotFoundException;
import com.ics.project.models.Movie;
import com.ics.project.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping(value = "categories/{category}")
    public ResponseEntity<List<Movie>> getMovieByCategoryAndType(@PathVariable(name = "category") Long id, @RequestParam String type) throws ResourceNotFoundException {
        return ResponseEntity.ok(movieService.getMoviesByCategoryAndType(id, type));
    }

    @PostMapping(value = "{suggest}")
    public ResponseEntity<Movie> suggestMovie(@RequestBody Movie movie) throws ResourceExistsException, ResourceNotFoundException {
        return ResponseEntity.ok(movieService.suggestMovie(movie));
    }
}