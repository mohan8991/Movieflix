package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Movie;

public interface MovieService {
	
	public List<Movie> findAllByTitle(String authHeader);
	
	public List<Movie> findAllByYear(String authHeader);
	
	public List<Movie> findAllByGenre(String authHeader);
	
	public List<Movie> findAllByType(String authHeader);

	public List<Movie> findAllByImdbRatings(String authHeader);

	public List<Movie> findAllByImdbVoters(String authHeader);

	public List<Movie> findMoviesByRating(String authHeader);

	public List<Movie> findSeriesByRating(String authHeader);

	public Movie findbyTitle(String title, String authHeader);
	
	public Movie findOne(String movid, String authHeader);
	
    public Movie create(Movie mov, String authHeader);
	
	public List<Movie> createAll(List<Movie> movies, String authHeader);
	
	public Movie update(Movie mov, String authHeader);

	public void delete(String movid, String authHeader);
	
}
