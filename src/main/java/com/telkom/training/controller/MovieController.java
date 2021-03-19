package com.telkom.training.controller;

import java.util.List;

import com.telkom.training.model.MovieModel;
import com.telkom.training.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping(value="/api/v1/movie", produces={"application/json"})
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;


    @GetMapping
    public Iterable<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/pagination")
    public Iterable<MovieModel> getAllMoviesByPage(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        // Validate Page Number
        if (page == null) {
            page = 0;
        }

        // Validate Page Size
        if (size == null) {
            size = 10;
        }

        return movieService.getAllMoviesByPage(page,size);
    }

    @GetMapping("/pagination/sort")
    public Iterable<MovieModel> getAllMoviesByPageSort(@RequestParam(name = "page", required = false) Integer page, 
                                                       @RequestParam(name = "size", required = false) Integer size,
                                                       @RequestParam(name = "sortby", required = false) String sortby,
                                                       @RequestParam(name = "ordertype", required = false) String ordertype) {
        // Validate Page Number
        if (page == null) {
            page = 0;
        }

        // Validate Page Size
        if (size == null) {
            size = 10;
        }
        
        
        switch (sortby) {
            case "rating":
                sortby =  "rating";
                break;
            case "release-year":
                sortby = "movieReleaseYear";
                break;
            default:
                sortby =  "rating";
                break;
        }
        return movieService.getAllMoviesByPageSort(page,size,sortby,ordertype);
    }

    @GetMapping("/find/genre/{genre}")
    public Iterable<MovieModel> getAllMoviesByGenre(@RequestParam(name = "page", required = false) Integer page, 
                                                       @RequestParam(name = "size", required = false) Integer size,
                                                       @PathVariable String genre) {
        // Validate Page Number
        if (page == null) {
            page = 0;
        }

        // Validate Page Size
        if (size == null) {
            size = 10;
        }
        
        return movieService.getMoviesPageByGenre(genre, page, size);
    }

    @GetMapping("/find/release/{release}")
    public Iterable<MovieModel> getAllMoviesByRelease(@RequestParam(name = "page", required = false) Integer page, 
                                                       @RequestParam(name = "size", required = false) Integer size,
                                                       @PathVariable int release) {
        // Validate Page Number
        if (page == null) {
            page = 0;
        }

        // Validate Page Size
        if (size == null) {
            size = 10;
        }
        
        return movieService.getMoviesPageByRelease(release, page, size);
    }

    @PostMapping
    public MovieModel createMovie(@RequestParam("title") String title,
                                  @RequestParam("genre") String genre,
                                  @RequestParam("release_year") int releaseYear,
                                  @RequestParam("rating") int rating) {
        MovieModel movieModel = new MovieModel();
        movieModel.setMovieTitle(title);
        movieModel.setMovieGenre(genre);
        movieModel.setMovieReleaseYear(releaseYear);
        movieModel.setRating(rating);

        return movieService.createMovieModel(movieModel);
    }

    @GetMapping("/{id}")
    public MovieModel getEmployeeById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/query/rating")
    public List<MovieModel> getMovieByRating(@RequestParam("rating") int rating){
        return movieService.getMovieByRating(rating);
    }

    @GetMapping("/query")
    public List<MovieModel> getMovieByGenreAndTitle(@RequestParam("genre") String genre, @RequestParam("title") String title){
        return movieService.getMovieByGenreAndTitle(genre, title);
    }

    @PutMapping(value="/update/{id}")
    public MovieModel putMovieById(@PathVariable int id, @RequestBody MovieModel movieModel) {
        return movieService.putMovie(movieModel, id);
    }

    @DeleteMapping("/delete/genre/{genre}")
    public void delMovieByGenre(@PathVariable String genre) {
        movieService.delMovieByGenre(genre);
    }
}
