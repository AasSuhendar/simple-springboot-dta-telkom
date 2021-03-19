package com.telkom.training.service;

import java.util.List;
import java.util.Optional;

import com.telkom.training.model.MovieModel;
import com.telkom.training.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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

    public Page<MovieModel> getAllMoviesByPage(int page, int size){
        Pageable pagination = PageRequest.of(page, size);
        return movieRepository.findAll(pagination);
    }

    public Page<MovieModel> getAllMoviesByPageSort(int page, int size, String sortby, String orderType){
        Pageable pagination = null;
        if (orderType.equals("asc")) {
            pagination = PageRequest.of(page, size, Sort.by(Order.asc(sortby)));
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Order.desc(sortby)));
        }
        
        return movieRepository.findAll(pagination);
    }

    public List<MovieModel> getMovieByRating(int rating){
        return movieRepository.getByMovieRating(rating);
    }

    public List<MovieModel> getMovieByGenreAndTitle(String genre, String title){
        return movieRepository.getByMovieGenreAndTitle(genre, title);
    }

    public Page<MovieModel> getMoviesPageByGenre(String genre, int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return movieRepository.getByMovieGenre(genre, pagination);
    }

    public Page<MovieModel> getMoviesPageByRelease(int release, int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        return movieRepository.getByMovieReleaseYear(release, pagination);
    }

    public MovieModel createMovieModel(MovieModel movieModel) {
        return movieRepository.save(movieModel);
    }

    public MovieModel getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    public MovieModel putMovie(MovieModel movieModel, int id){
        Optional<MovieModel> currentMovie = movieRepository.findById(id);
        if (currentMovie.isPresent()) {
            return movieRepository.save(movieModel);
        }
        return null;
    }

    public void delMovieByGenre(String genre) {
        movieRepository.deleteByMovieGenre(genre);
    }
}
