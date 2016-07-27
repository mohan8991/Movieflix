package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Movie;

public interface MovieService {
	
	public List<Movie> findAll();
	
	public Movie findOne(String movid);
	
	public Movie create(Movie mov);
	
	public Movie update(String id, Movie mov);

	public void delete(String movid);
}
