package io.egen.movieflex_Server.movie_controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.movieflex_Server.entity.movie;

@RestController
@RequestMapping(path = "movies")
public class movie_controller {
	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List<movie> findAll(){
		return null; //empty Method need to implement
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public movie findone(@PathVariable("id") String mov_Id){
		return null;//empty Method need to implement
	}
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public movie create(@RequestBody movie new_mov){
		return null;//empty Method need to implement
	}
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public movie update(@PathVariable("id") String mov_Id, @RequestBody movie new_mov){
		return null;//empty Method need to implement
	}
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}" )
	public movie delete(@PathVariable("id") String mov_Id){
		return null;//empty Method need to implement
	}

}
