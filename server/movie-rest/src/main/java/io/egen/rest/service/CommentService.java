package io.egen.rest.service;

import java.util.List;

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;


public interface CommentService {

	public Comments findOne(String comId);

	public Comments create(String userName, String movId, Comments comments);

	public Comments update(String userName, String comId, Comments comments);

	public void delete(String userName, String comId);

	public List<Comments> findAllByTitle(String title);

	public List<Comments> findAllByUser(String userName);

	public MStars updateStar(String userName, String StarId, MStars stars);

	public MStars createStar(String userName, String starId, MStars stars);

	public MStars findStarbyId(String starId);

	public MStars findStarByMov(String movId);

	public MStars avgRating(String movId);
	
	

}
