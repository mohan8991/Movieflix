package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Movie;

public interface MovieService {
	
	public List<Movie> findAllByTitle();
	
	public List<Movie> findAllByYear();
	
	public List<Movie> findAllByGenre();
	
	public List<Movie> findAllByType();

	public List<Movie> findAllByImdbRatings();

	public List<Movie> findAllByImdbVoters();

	public List<Movie> findMoviesByRating();

	public List<Movie> findSeriesByRating();

	public Movie findbyTitle(String title);
	
	public Movie findOne(String movid);
	
    public Movie create(String UserName, Movie mov);
	
	public List<Movie> createAll(List<Movie> movies);
	
	public Movie update(String id, Movie mov);

	public void delete(String movid);
	
}
