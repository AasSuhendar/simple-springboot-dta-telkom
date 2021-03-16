package com.telkom.training.controller;

import com.telkom.training.model.MovieModel;
import com.telkom.training.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/api/v1/movie", produces={"application/json"})
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;


    @GetMapping
    public Iterable<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public MovieModel createMovie(@RequestParam("title") String title,
                                  @RequestParam("genre") String genre,
                                  @RequestParam("release_year") String releaseYear) {
        MovieModel movieModel = new MovieModel();
        movieModel.setMovieTitle(title);
        movieModel.setMovieGenre(genre);
        movieModel.setMovieReleaseYear(releaseYear);

        return movieService.createMovieModel(movieModel);
    }

    @GetMapping("/{id}")
    public MovieModel getEmployeeById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }
}
