package io.egen.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.controller.UserController;
import io.egen.rest.entity.Movie;
import io.egen.rest.exception.MovieAlreadyExistsException;
import io.egen.rest.exception.MovieNotFoundException;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService{

	@Autowired
	MovieRepository repository;
	
	@Autowired
    UserController userController;

	
	@Override
	public List<Movie> findAllByTitle() {
		return repository.findAllByTitle();
	}

	@Override
	public List<Movie> findAllByYear() {
		return repository.findAllByYear();
	}
	
	@Override
	public Movie findOne(String movid) {
		Movie existing = repository.findOne(movid);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movid + " not found");
		}
		return existing;
	}
		
	@Override
	@Transactional
    public Movie create(String UserName, Movie mov) {
        String Role = userController.aReq(UserName);
        if (!(Role.equals("Admin"))) {
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
	public List<Movie> createAll(List<Movie> movies){
		for(Movie mov : movies){
			System.out.println(mov.getTitle());
			return repository.createAll(movies);
		}
		System.out.println("this is from the service");
		return movies;
	}
	
	@Override
	@Transactional
	public Movie update(String id, Movie mov) {
		String[] breakId = id.split("&");
		String Role = userController.aReq(breakId[1]);
		if(!(Role.equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		Movie existing = repository.findOne(breakId[0]);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + id + " not found");
		}
		return repository.update(mov);
	}

	@Override
	@Transactional
	public void delete(String movid) {
		String[] breakId = movid.split("&");
		String Role = userController.aReq(breakId[1]);
		if(!(Role.equals("Admin"))){
			throw new UserNoWritePermission(" Only Admin Can do that ");
		}
		Movie existing = repository.findOne(breakId[0]);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + movid + " not found");
		}
		repository.delete(existing);
		
	}

}
