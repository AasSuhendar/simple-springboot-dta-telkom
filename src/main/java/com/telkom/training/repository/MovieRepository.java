package com.telkom.training.repository;

import com.telkom.training.model.MovieModel;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieModel, Integer>{

}
