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
