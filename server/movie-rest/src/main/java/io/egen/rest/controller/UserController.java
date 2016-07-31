package io.egen.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@RequestMapping(method = RequestMethod.GET, path = "{UserName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo findbyUserName(@PathVariable("UserName") String userName, @RequestHeader(value="Authorization") String authHeader) {
		return service.findOne(userName, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo create(@RequestBody UserInfo uInfo) {
		return service.create(uInfo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo update(@RequestBody UserInfo uInfo, @RequestHeader(value="Authorization") String authHeader) {
		return service.update(uInfo, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{UserName}")
	public void delete(@PathVariable("UserName") String userName,@RequestHeader(value="Authorization") String authHeader) {
		service.delete(userName, authHeader);
	}
	
	@RequestMapping(method = RequestMethod.PUT,path = "Signin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserInfo SignIn(@RequestBody UserInfo uInfo) {
		return service.SignIn(uInfo);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "SignOut")
	public void SignOut(@RequestHeader(value="Authorization") String authHeader){
		service.SignOut(authHeader);
	}
	
	public String aReq(String UserName){
		return service.aReq(UserName);
	}
}
