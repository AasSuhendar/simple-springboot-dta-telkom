package com.telkom.training.service;

import com.telkom.training.model.MovieModel;
import com.telkom.training.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Iterable<MovieModel> getAllMovies(){
        return movieRepository.findAll();
    }

    public MovieModel createMovieModel(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    public MovieModel getMovieById(int id) {
        return movieRepository.findById(id).get();
    }
}
