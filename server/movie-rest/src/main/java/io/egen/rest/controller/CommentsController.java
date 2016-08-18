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

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;
import io.egen.rest.service.CommentService;

@RestController
@RequestMapping(path = "Comments")
public class CommentsController {

	@Autowired
	CommentService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "{comId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments findOne(@PathVariable("comId") String comId, @RequestHeader(value="Authorization") String authHeader) {
		return service.findOne(comId, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findbyTitle/{movTitle}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comments> findAllByTitle(@PathVariable("movTitle") String Title, @RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByTitle(Title, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findbyUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comments> findAllByUser(@RequestHeader(value="Authorization") String authHeader) {
		return service.findAllByUser(authHeader);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "{movid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments create(@PathVariable("movid") String movId,  @RequestBody Comments comments, @RequestHeader(value="Authorization") String authHeader) {
		return service.create(movId, comments, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{comId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments update(@PathVariable("comId") String comId, @RequestBody Comments comments, @RequestHeader(value="Authorization") String authHeader) {
		return service.update(comId, comments, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{comId}")
	public void delete(@PathVariable("comId") String comId, @RequestHeader(value="Authorization") String authHeader) {
		service.delete(comId, authHeader);
	}
	
	//for Star Management
	
	@RequestMapping(method = RequestMethod.GET, path = "findStar/{starId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars findStarbyId(@PathVariable("starId") String starId, @RequestHeader(value="Authorization") String authHeader) {
		return service.findStarbyId(starId, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findStarByMov/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MStars> findStarByMov(@PathVariable("movId") String movId) {
		return service.findStarByMov(movId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "star/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars createStar(@PathVariable("movId") String movId,  @RequestBody MStars Stars, @RequestHeader(value="Authorization") String authHeader) {
		return service.createStar(movId, Stars, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "star/{starId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars updateStar(@PathVariable("starId") String starId, @RequestBody MStars Stars, @RequestHeader(value="Authorization") String authHeader) {
		return service.updateStar(starId, Stars, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "getAvgStar/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars avgRating(@PathVariable("movId") String movId, @RequestHeader(value="Authorization") String authHeader) {
		return service.avgRating(movId, authHeader);
	}
	
}
