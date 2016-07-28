package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Movie;


public interface MovieService {
	
	public List<Movie> findAllByTitle();
	
	public List<Movie> findAllByYear();
	
	public Movie findOne(String movid);
	
	public Movie create(String UserName, Movie mov);
	
	public List<Movie> createAll(List<Movie> movies);
	
	public Movie update(String id, Movie mov);

	public void delete(String movid);

}
