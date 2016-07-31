package io.egen.rest.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.entity.Movie;
import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.MovieAlreadyExistsException;
import io.egen.rest.exception.MovieNotFoundException;
import io.egen.rest.exception.NoAuthHeaderFound;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.repository.MovieRepository;
import io.egen.rest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class MovieServiceImp implements MovieService{

	@Autowired
	MovieRepository repository;
	
	@Autowired
    UserRepository userRepo;

	
	@Override
	@Transactional
	public List<Movie> findAllByTitle(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByTitle();
	}

	@Override
	@Transactional
	public List<Movie> findAllByYear(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByYear();
	}
	
	@Override
	@Transactional
	public List<Movie> findAllByGenre(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByGenre();
	}

	@Override
	@Transactional
	public List<Movie> findAllByType(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByType();
	}

	@Override
	@Transactional
	public List<Movie> findAllByImdbRatings(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByImdbRatings();
	}

	@Override
	@Transactional
	public List<Movie> findAllByImdbVoters(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findAllByImdbVoters();
	}

	@Override
	@Transactional
	public List<Movie> findMoviesByRating(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findMoviesByRating();
	}

	@Override
	@Transactional
	public List<Movie> findSeriesByRating(String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		return repository.findSeriesByRating();
	}

	@Override
	@Transactional
	public Movie findbyTitle(String title, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie existing = repository.findByTitle(title);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + title + " not found");
		}
		return existing;
	}
	
	@Override
	@Transactional
	public Movie findOne(String movid, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		Movie existing = repository.findOne(movid);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movid + " not found");
		}
		return existing;
	}
		
	@Override
	@Transactional
    public Movie create(Movie mov, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
        if (!(usr.getRole().equals("Admin"))) {
            throw new UserNoWritePermission(" Only Admin Can do that ");
        }
		Movie existing = repository.findByTitle(mov.getTitle());
		if (existing != null) {
			throw new MovieAlreadyExistsException("Movie Already Exists: " + mov.getTitle());
		}
		return repository.create(mov);
	}
	
	@Override
	@Transactional
	public List<Movie> createAll(List<Movie> movies, String authHeader){
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!usr.getRole().equals("Admin")){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		return repository.createAll(movies);
	}
	
	@Override
	@Transactional
	public Movie update(Movie mov, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!(usr.getRole().equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		Movie existing = repository.findOne(mov.getId());
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + mov.getId() + " not found");
		}
		return repository.update(mov);
	}

	@Override
	@Transactional
	public void delete(String movid, String authHeader) {
		String token = authHeader.substring(7);
		Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		String userName = (String) ((Map<String, Object>) claims.get("role")).get("userName");
		UserInfo usr = userRepo.findOne(userName);
		if(claims == null){
			throw new NoAuthHeaderFound("Invalid Request: No Authorization");
		}
		if(!usr.getRole().equals("Admin")){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		Movie existing = repository.findOne(movid);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + movid + " not found");
		}
		repository.delete(existing);
		
	}

}
