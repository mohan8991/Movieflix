package io.egen.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.rest.entity.Movie;
import io.egen.rest.service.MovieService;

@RestController
@RequestMapping(path = "Movies")
public class MovieController {
	
	@Autowired
	MovieService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyTitle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByTitle(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByTitle(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyYear", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByYear(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByYear(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyGenre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByGenre(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByGenre(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByType(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByType(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyImdbRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByImdbRatings(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByImdbRatings(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyImdbVoters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByImdbVoters(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByImdbVoters(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortMbyRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findMoviesByRating(@RequestHeader(value="Authorization") String authHeader) {
		return service.findMoviesByRating(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortSbyRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findSeriesByRating(@RequestHeader(value="Authorization") String authHeader) {
		return service.findSeriesByRating(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/title/{title}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findbyTitle(@PathVariable("title") String title, @RequestHeader(value="Authorization") String authHeader) {
		return service.findbyTitle(title, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findOne(@PathVariable("id") String movId, @RequestHeader(value="Authorization") String authHeader) {
		return service.findOne(movId, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie create(@RequestBody Movie mov, @RequestHeader(value="Authorization") String authHeader) {
        return service.create(mov, authHeader);
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "arrayInput", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> createAll(@RequestBody List<Movie> movies, @RequestHeader(value="Authorization") String authHeader){
		return service.createAll(movies, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie update(@RequestHeader(value="Authorization") String authHeader, @RequestBody Movie mov) {
		return service.update(mov, authHeader);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String movId, @RequestHeader(value="Authorization") String authHeader) {
		service.delete(movId, authHeader);
	}
}
