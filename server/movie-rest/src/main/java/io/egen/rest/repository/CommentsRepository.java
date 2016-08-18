package io.egen.rest.repository;

import java.util.List;

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;

public interface CommentsRepository {

	public Comments findOne(String comId);

	public Comments create(Comments comments);

	public void delete(Comments existing);

	public Comments update(Comments comments);

	public List<Comments> findByTitle(String Movid);

	public List<Comments> findByUser(String userName);

	public MStars findStarbyId(String starId);

	public MStars updateStar(MStars stars);

	public MStars createStar(MStars stars);

	public List<MStars> findStarByMov(String movId);
	
	public int findAvgStars(String movId);
	
}
