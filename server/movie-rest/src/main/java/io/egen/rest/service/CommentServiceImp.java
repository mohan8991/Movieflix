package io.egen.rest.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;
import io.egen.rest.entity.Movie;
import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.CommentsNotFoundException;
import io.egen.rest.exception.MovieNotFoundException;
import io.egen.rest.exception.NoAuthHeaderFound;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.exception.UserNotFoundException;
import io.egen.rest.exception.starsNotFoundException;
import io.egen.rest.repository.CommentsRepository;
import io.egen.rest.repository.MovieRepository;
import io.egen.rest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentsRepository repository;	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MovieRepository movieRepo;
	
	@Override
	@Transactional
	public List<Comments> findAllByTitle(String title, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie existing = movieRepo.findByTitle(title);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + title + " not found");
		}
		return repository.findByTitle(existing.getId());
	}

	@Override
	@Transactional
	public List<Comments> findAllByUser(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if (usr == null) {
			throw new UserNotFoundException("User with User Name:" + userName + " not found" );
		}
		return repository.findByUser(userName);
	}
	
	@Override
	public Comments findOne(String comId, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Comments existing = repository.findOne(comId);
		if (existing == null) {
			throw new CommentsNotFoundException("Comments with ID:" + comId + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Comments create(String movId, Comments comments, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie mov = movieRepo.findOne(movId);
		comments.setUser(usr);
		comments.setMovie(mov);
		return repository.create(comments);
		}

	@Override
	@Transactional
	public Comments update(String comId, Comments comm, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Comments existing = repository.findOne(comId);
		if(!(usr.getRole().equals("Admin") || existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can update only your comments or Admins can do that");
		}
		if(existing == null) {
			throw new CommentsNotFoundException("Comments with ID: " + comId + "not found" );
		}
		return repository.update(comm);
	}

	@Override
	@Transactional
	public void delete(String comId, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Comments existing = repository.findOne(comId);
		System.out.println(existing);
		if(!(usr.getRole().equals("Admin") || existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can delete only your comments or Admins can do that");
		}
		if (existing == null) {
			throw new CommentsNotFoundException("Comments with ID:" + comId + " not found");
		}
		repository.delete(existing);
	}

	@Override
	@Transactional
	public MStars updateStar(String starId, MStars stars, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		MStars existing = repository.findStarbyId(starId);
		if(!(existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can update only your own Stars");
		}
		if(existing == null) {
			throw new starsNotFoundException("Comments with ID: " + starId + "not found" );
		}
		stars.setMovie(existing.getMovie());;
		stars.setUser(existing.getUser());
		return repository.updateStar(stars);
	}
	
	@Override
	@Transactional
	public MStars createStar(String movId, MStars stars, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie mov = movieRepo.findOne(movId);
		stars.setUser(usr);
		stars.setMovie(mov);
		return repository.createStar(stars);
		}

	@Override
	@Transactional
	public MStars findStarbyId(String starId, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		MStars existing = repository.findStarbyId(starId);
		if (existing == null) {
			throw new starsNotFoundException("Comments with ID:" + starId + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public MStars findStarByMov(String movId) {
		Movie existing = movieRepo.findOne(movId);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movId + " not found");
		}
		return repository.findStarByMov(movId);
	}
	
	@Override
	@Transactional
	public MStars avgRating(String movId, String authHeader){
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie existing = movieRepo.findOne(movId);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movId + " not found");
		}
		MStars avgStar = new MStars();
		avgStar.setStar(repository.findAvgStars(movId));
		avgStar.setMovie(existing);
		return avgStar;
	}


}
