package io.egen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.rest.entity.UserInfo;
import io.egen.rest.service.UserService;

@RestController
@RequestMapping(path = "UserInfo")
public class UserController {

	@Autowired
	UserService service;
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo findOne(@PathVariable("id") String userName) {
		return service.findOne(userName);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo create(@PathVariable("username") String userName, @RequestBody UserInfo uInfo) {
		return service.create(userName, uInfo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo update(@PathVariable("id") String userId, @RequestBody UserInfo uInfo) {
		return service.update(userId, uInfo);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String userId) {
		service.delete(userId);
	}
	
	public String aReq(String UserName){
		return service.aReq(UserName);
	}
}
