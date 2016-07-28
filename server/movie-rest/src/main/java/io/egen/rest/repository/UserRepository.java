package io.egen.rest.repository;

import io.egen.rest.entity.UserInfo;

public interface UserRepository {

	public UserInfo findOne(String userId);

	public UserInfo create(UserInfo uInfo);

	public UserInfo update(UserInfo uInfo);

	public void delete(UserInfo uInfo);

}
