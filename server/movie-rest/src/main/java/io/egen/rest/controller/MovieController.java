package io.egen.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Movie> findAllByTitle() {
		return service.findAllByTitle();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyYear", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByYear() {
		return service.findAllByYear();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyGenre", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByGenre() {
		return service.findAllByGenre();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByType() {
		return service.findAllByType();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyImdbRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByImdbRatings() {
		return service.findAllByImdbRatings();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortbyImdbVoters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllByImdbVoters() {
		return service.findAllByImdbVoters();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortMbyRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findMoviesByRating() {
		return service.findMoviesByRating();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "sortSbyRatings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findSeriesByRating() {
		return service.findSeriesByRating();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{title}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findbyTitle(@PathVariable("title") String title) {
		return service.findbyTitle(title);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findOne(@PathVariable("id") String movId) {
		return service.findOne(movId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie create(@PathVariable("username") String userName, @RequestBody Movie mov) {
        return service.create(userName, mov);
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "arrayInput", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> createAll(@RequestBody List<Movie> movies){
		return service.createAll(movies);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie update(@PathVariable("id") String movId, @RequestBody Movie mov) {
		return service.update(movId, mov);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String movId) {
		service.delete(movId);
	}
}
