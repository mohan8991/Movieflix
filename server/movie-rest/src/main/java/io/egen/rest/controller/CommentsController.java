package io.egen.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Comments findOne(@PathVariable("comId") String comId) {
		return service.findOne(comId);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findbyTitle/{movTitle}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comments> findAllByTitle(@PathVariable("movTitle") String Title) {
		return service.findAllByTitle(Title);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findbyUser/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Comments> findAllByUser(@PathVariable("userName") String userName) {
		return service.findAllByUser(userName);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "{username}/{movid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments create(@PathVariable("username") String userName, @PathVariable("movid") String movId,  @RequestBody Comments comments) {
		return service.create(userName, movId, comments);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{userName}/{comId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Comments update(@PathVariable("userName") String UserName, @PathVariable("comId") String comId, @RequestBody Comments comments) {
		return service.update(UserName, comId, comments);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{username}/{comId}")
	public void delete(@PathVariable("username") String UserName, @PathVariable("comId") String comId) {
		service.delete(UserName, comId);
	}
	
	//for Star Management
	
	@RequestMapping(method = RequestMethod.GET, path = "findStar/{starId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars findStarbyId(@PathVariable("starId") String starId) {
		return service.findStarbyId(starId);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "findStarByMov/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars findStarByMov(@PathVariable("movId") String movId) {
		return service.findStarByMov(movId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "star/{username}/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars createStar(@PathVariable("username") String userName, @PathVariable("movId") String movId,  @RequestBody MStars Stars) {
		return service.createStar(userName, movId, Stars);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "star/{userName}/{starId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars updateStar(@PathVariable("userName") String UserName, @PathVariable("starId") String starId, @RequestBody MStars Stars) {
		return service.updateStar(UserName, starId, Stars);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "getAvgStar/{movId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MStars avgRating(@PathVariable("movId") String movId) {
		return service.avgRating(movId);
	}
	
}
