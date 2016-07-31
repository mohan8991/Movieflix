package io.egen.rest.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.InvalidUserNameException;
import io.egen.rest.exception.NewUserDefault;
import io.egen.rest.exception.NoAuthHeaderFound;
import io.egen.rest.exception.PasswordNotMatchException;
import io.egen.rest.exception.UserAlreadyExistsException;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.exception.UserNotFoundException;
import io.egen.rest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserInfo findOne(String userNametoFind, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = repository.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!(usr.getRole().equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that or Who have created it can only do it");
		}
		UserInfo existing = repository.findOne(userNametoFind);
		if (existing == null){
			throw new UserNotFoundException("User with username:" + userName + "not found");
		}
		return existing;
	}
	
	@Override
	@Transactional
	public UserInfo create(UserInfo uInfo) {  
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
		uInfo.setToken(Jwts.builder().setSubject(uInfo.getUserName()).claim("role", uInfo).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		return repository.create(uInfo);
	}

	@Override
	@Transactional
	public UserInfo update(UserInfo uInfo, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = repository.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!(usr.getRole().equals("Admin") || usr.getUserName().equals(userName))){
			throw new UserNoWritePermission(" Only Admin Can do that or Who have created it can only do it");
		}
		if (usr == null) {
			throw new UserNotFoundException("User with ID:" + usr.getId() + " not found");
		}
		uInfo.setToken(Jwts.builder().setSubject(uInfo.getUserName()).claim("role", uInfo).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		return repository.update(uInfo);
	}

	@Override
	@Transactional
	public void delete(String userName1, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = repository.findOne(userName);
		UserInfo delusr = repository.findOne(userName1);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!usr.getRole().equals("Admin")){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		
		if (usr == null) {
			throw new UserNotFoundException("User with ID:" + usr.getId() + "not found");
		}
		repository.delete(delusr);
	}

	@Override
	public String aReq(String userName) {
		UserInfo usr =  repository.findOne(userName);
		return usr.getRole();
	}

	@Override
	@Transactional
	public void SignOut(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = repository.findOne(userName);
		usr.setToken(null);
		repository.update(usr);
	}
	
	
	@Override
	@Transactional
	public UserInfo SignIn(UserInfo uInfo) {
		UserInfo existing = repository.findOne(uInfo.getUserName());
		
		if(!existing.getUserName().equals(uInfo.getUserName())){
			throw new InvalidUserNameException("The UserName you have entered do not Match please SignUP");
		}
		if(!existing.getPassword().equals(uInfo.getPassword())){
			throw new PasswordNotMatchException("The password you have entered do not Match please try again");
		}	
		existing.setToken(Jwts.builder().setSubject(existing.getUserName()).claim("role", existing).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		return repository.update(existing);
		}

}
