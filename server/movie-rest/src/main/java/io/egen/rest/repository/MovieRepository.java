package io.egen.rest.repository;

import java.util.List;

import io.egen.rest.entity.Movie;

public interface MovieRepository {

	public List<Movie> findAll();
	
	public Movie findOne(String id);
	
	public Movie findByTitle(String title);
	
	public Movie create(Movie mov);
	
	public Movie update(Movie mov);

	public void delete(Movie mov);

}
