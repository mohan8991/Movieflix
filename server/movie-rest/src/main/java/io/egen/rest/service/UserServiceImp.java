package io.egen.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.NewUserDefault;
import io.egen.rest.exception.UserAlreadyExistsException;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.exception.UserNotFoundException;
import io.egen.rest.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserInfo findOne(String userName) {
		UserInfo existing = repository.findOne(userName);
		if (existing == null){
			throw new UserNotFoundException("User with username:" + userName + "not found");
		}
		return existing;
	}
	
	@Override
	@Transactional
	public UserInfo create(String UserName, UserInfo uInfo) {
		String Role = aReq(UserName);
        if (!(Role.equals("Admin"))) {
            throw new UserNoWritePermission(" Only Admin Can do that ");
        }
        
        if (uInfo.getRole().equals("Admin")){
        	throw new NewUserDefault("New user cannot be Admins, Admins will be assigned to you upon request");
        }
        if(!(uInfo.getRole().equals("Admin") || uInfo.getRole().equals("User"))){
        	throw new NewUserDefault("User Role should be either Admin or User");
        }
		UserInfo existing = repository.findOne(uInfo.getUserName());
		if (existing != null){
			throw new UserAlreadyExistsException("User with ID:" + uInfo.getUserName() + "already exists" );
		}
		return repository.create(uInfo);
	}

	@Override
	@Transactional
	public UserInfo update(String userId, UserInfo uInfo) {
		String[] breakId = userId.split("&");
		String Role = aReq(breakId[1]);
		UserInfo existing = repository.findOne(breakId[0]);
		if(!(Role.equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		if (existing == null) {
			throw new UserNotFoundException("User with ID:" + userId + " not found");
		}
		return repository.update(uInfo);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		String[] breakId = userId.split("&");
		String Role = aReq(breakId[1]);
		UserInfo existing = repository.findOne(breakId[0]);
		if(!(Role.equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
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
