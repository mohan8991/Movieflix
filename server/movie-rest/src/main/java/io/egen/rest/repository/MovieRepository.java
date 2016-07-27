package io.egen.rest.repository;

import java.util.List;

import io.egen.rest.entity.Movie;

public interface MovieRepository {

	public List<Movie> findAll();
	
	public Movie create(Movie mov);
}
