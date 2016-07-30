package io.egen.rest.service;

import io.egen.rest.entity.UserInfo;

public interface UserService {

	public UserInfo findOne(String userName);
	
	public UserInfo create(String userName, UserInfo uInfo);

	public UserInfo update(String userId, UserInfo uInfo);

	public void delete(String userId);

	public String aReq(String userName);
	
}
