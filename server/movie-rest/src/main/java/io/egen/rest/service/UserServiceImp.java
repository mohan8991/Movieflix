package io.egen.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.UserAlreadyExistsException;
import io.egen.rest.exception.UserNotFoundException;
import io.egen.rest.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserInfo findOne(String userId) {
		UserInfo existing = repository.findOne(userId);
		if (existing == null){
			throw new UserNotFoundException("User with username:" + userId + "not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public UserInfo create(UserInfo uInfo) {
		UserInfo existing = repository.findOne(uInfo.getUserName());
		if (existing != null){
			throw new UserAlreadyExistsException("User with ID:" + uInfo.getUserName() + "already exists" );
		}
		return repository.create(uInfo);
	}

	@Override
	@Transactional
	public UserInfo update(String userId, UserInfo uInfo) {
		UserInfo existing = repository.findOne(userId);
		if (existing == null) {
			throw new UserNotFoundException("User with ID:" + userId + " not found");
		}
		return repository.update(uInfo);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		UserInfo existing = repository.findOne(userId);
		if (existing == null) {
			throw new UserNotFoundException("User with ID:" + userId + "not found");
		}
		repository.delete(existing);
	}

	@Override
	public String aReq(String userName) {
		UserInfo usr =  repository.findOne(userName);
		return usr.getRole();
	}

}
