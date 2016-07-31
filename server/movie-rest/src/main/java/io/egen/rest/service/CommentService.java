package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;


public interface CommentService {
	
	public List<Comments> findAllByTitle(String title, String authHeader);

	public List<Comments> findAllByUser(String authHeader);

	public Comments findOne(String comId, String authHeader);

	public Comments create(String movId, Comments comments, String authHeader);

	public Comments update( String comId, Comments comments, String authHeader);

	public void delete(String comId, String authHeader);

	public MStars updateStar(String StarId, MStars stars, String authHeader);

	public MStars createStar(String starId, MStars stars, String authHeader);

	public MStars findStarbyId(String starId, String authHeader);

	public MStars findStarByMov(String movId);

	public MStars avgRating(String movId, String authHeader);
	
	

}
