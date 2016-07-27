package io.egen.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.rest.entity.Movie;

@Repository
public class MovieRepositoryImp implements MovieRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Movie> findAllByTitle() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAllByTitle", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findAllByYear() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAllByYear", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public Movie findOne(String id) {
		return em.find(Movie.class, id);
	}
	
	@Override
	public Movie create(Movie mov) {
		em.persist(mov);
		return mov;
	}
	
	@Override
	public Movie findByTitle(String title) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitle", Movie.class);
		query.setParameter("pTitle", title);
		List<Movie> Movies = query.getResultList();
		if (Movies != null && Movies.size() == 1) {
			return Movies.get(0);
		}
		return null;
	}

	@Override
	public Movie update(Movie mov) {
		return em.merge(mov);
	}

	@Override
	public void delete(Movie mov) {
		em.remove(mov);	
	}

	@Override
	public List<Movie> createAll(List<Movie> movies) {
		for(Movie mov : movies )
		{
			em.persist(mov);
		}
		return movies;
		
	}

}
