package io.egen.rest.service;

import io.egen.rest.entity.UserInfo;

public interface UserService {

	public UserInfo findOne(String authHeader);
	
	public UserInfo create(UserInfo uInfo);

	public UserInfo update(UserInfo uInfo, String authHeader);

	public void delete(String userName, String authHeader2);

	public String aReq(String userName);

	public void SignOut(String authHeader);

	public UserInfo SignIn(UserInfo uInfo);
	
}
