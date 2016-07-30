package io.egen.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.rest.controller.MovieController;
import io.egen.rest.controller.UserController;
import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;
import io.egen.rest.entity.Movie;
import io.egen.rest.entity.UserInfo;
import io.egen.rest.exception.CommentsNotFoundException;
import io.egen.rest.exception.MovieNotFoundException;
import io.egen.rest.exception.UserNoWritePermission;
import io.egen.rest.exception.UserNotFoundException;
import io.egen.rest.exception.starsNotFoundException;
import io.egen.rest.repository.CommentsRepository;

@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	CommentsRepository repository;	
	
	@Autowired
	UserController userController;
	
	@Autowired
	MovieController movieController;
	
	@Override
	@Transactional
	public List<Comments> findAllByTitle(String title) {
		Movie existing = movieController.findbyTitle(title);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + title + " not found");
		}
		return repository.findByTitle(existing.getId());
	}

	@Override
	@Transactional
	public List<Comments> findAllByUser(String userName) {
		UserInfo existing = userController.findOne(userName);
		if (existing == null) {
			throw new UserNotFoundException("User with User Name:" + userName + " not found" );
		}
		return repository.findByUser(userName);
	}
	
	@Override
	public Comments findOne(String comId) {
		Comments existing = repository.findOne(comId);
		if (existing == null) {
			throw new CommentsNotFoundException("Comments with ID:" + comId + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Comments create(String userName, String movId, Comments comments) {
		UserInfo user = userController.findOne(userName);
		Movie mov = movieController.findOne(movId);
		comments.setUser(user);
		comments.setMovie(mov);
		return repository.create(comments);
		}

	@Override
	@Transactional
	public Comments update(String userName, String comId, Comments comm) {
		String Role = userController.aReq(userName);
		Comments existing = repository.findOne(comId);
		if(!(Role.equals("Admin") || existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can update only your comments or Admins can do that");
		}
		if(existing == null) {
			throw new CommentsNotFoundException("Comments with ID: " + comId + "not found" );
		}
		return repository.update(comm);
	}

	@Override
	@Transactional
	public void delete(String userName, String comId) {
		String Role = userController.aReq(userName);
		Comments existing = repository.findOne(comId);
		System.out.println(existing);
		if(!(Role.equals("Admin") || existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can delete only your comments or Admins can do that");
		}
		if (existing == null) {
			throw new CommentsNotFoundException("Comments with ID:" + comId + " not found");
		}
		repository.delete(existing);
	}

	@Override
	@Transactional
	public MStars updateStar(String userName, String starId, MStars stars) {
		MStars existing = repository.findStarbyId(starId);
		if(!(existing.getUser().getUserName().equals(userName))){
			throw new UserNoWritePermission(" You can update only your own Stars");
		}
		if(existing == null) {
			throw new starsNotFoundException("Comments with ID: " + starId + "not found" );
		}
		return repository.updateStar(stars);
	}
	
	@Override
	@Transactional
	public MStars createStar(String userName, String movId, MStars stars) {
		UserInfo user = userController.findOne(userName);
		Movie mov = movieController.findOne(movId);
		stars.setUser(user);
		stars.setMovie(mov);
		return repository.createStar(stars);
		}

	@Override
	@Transactional
	public MStars findStarbyId(String starId) {
		MStars existing = repository.findStarbyId(starId);
		if (existing == null) {
			throw new starsNotFoundException("Comments with ID:" + starId + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public MStars findStarByMov(String movId) {
		Movie existing = movieController.findOne(movId);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movId + " not found");
		}
		return repository.findStarByMov(movId);
	}
	
	@Override
	@Transactional
	public MStars avgRating(String movId){
		Movie existing = movieController.findOne(movId);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with ID:" + movId + " not found");
		}
		MStars avgStar = new MStars();
		avgStar.setStar(repository.findAvgStars(movId));
		avgStar.setMovie(existing);
		return avgStar;
	}


}
