package com.telkom.training.repository;

import java.util.List;

import com.telkom.training.model.MovieModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MovieRepository extends PagingAndSortingRepository<MovieModel, Integer>{
    Page<MovieModel> getByMovieGenre(String genre, Pageable pagination);
    Page<MovieModel> getByMovieReleaseYear(int release_year, Pageable pagination);

    @Transactional
    void deleteByMovieGenre(String genre);

    // EmployeeModel is an Entity (JPQL)
    @Query("select movie from MovieModel movie where movie.rating > ?1")
    List<MovieModel> getByMovieRating(int rating);

    // Native Query
    @Query(value = "select * from movie where movie_genre like %?1% " +
            "and movie_title like %?2%", nativeQuery = true)
    List<MovieModel> getByMovieGenreAndTitle(String genre, String title);
}
