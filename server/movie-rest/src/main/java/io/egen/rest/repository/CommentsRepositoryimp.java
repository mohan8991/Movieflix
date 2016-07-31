package io.egen.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.rest.entity.Comments;
import io.egen.rest.entity.MStars;

@Repository
public class CommentsRepositoryimp implements CommentsRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comments findOne(String comId) {
		return em.find(Comments.class, comId);
	}

	@Override
	public Comments create(Comments comments) {
		em.persist(comments);
		return comments;
	}

	@Override
	public void delete(Comments existing) {
		em.remove(existing);
	}

	@Override
	public Comments update(Comments comments) {
		return em.merge(comments);
	}

	@Override
	public List<Comments> findByTitle(String Movid) {
		TypedQuery<Comments> query = em.createNamedQuery("Comments.findByTitle", Comments.class);
		query.setParameter("pMovId", Movid);
		List<Comments> comments = query.getResultList();
		return comments;
	}
	
	@Override
	public List<Comments> findByUser(String userName) {
		TypedQuery<Comments> query = em.createNamedQuery("Comments.findByUser", Comments.class);
		query.setParameter("pUserName", userName);
		List<Comments> comments = query.getResultList();
		return comments;
	}

	@Override
	public MStars findStarbyId(String starId) {
		return em.find(MStars.class, starId);
	}

	@Override
	public MStars updateStar(MStars stars) {
		return em.merge(stars);
	}

	@Override
	public MStars createStar(MStars stars) {
		em.persist(stars);
		return stars;
	}

	@Override
	public MStars findStarByMov(String movId) {
		TypedQuery<MStars> query = em.createNamedQuery("MStars.findByMovId", MStars.class);
		query.setParameter("pMovID", movId);
		List<MStars> stars = query.getResultList();
		if (stars != null && stars.size() == 1) {
			return stars.get(0);
		}
		return null;
	}
	
	public int findAvgStars(String movId){
		TypedQuery<MStars> query = em.createNamedQuery("MStars.findByMovId", MStars.class);
		query.setParameter("pMovID", movId);
		List<MStars> stars = query.getResultList();
		int noOfUsers = stars.size();
		int totalStar = 0;
		for(MStars star : stars){
			totalStar += star.getStar();
		}
		return Math.round(totalStar/noOfUsers);
	}
}
