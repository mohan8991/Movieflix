package io.egen.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.rest.entity.UserInfo;

@Repository
public class UserRepositoryimp implements UserRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public UserInfo findOne(String userId) {
		TypedQuery<UserInfo> query = em.createNamedQuery("UserInfo.findOne", UserInfo.class);
		query.setParameter("pUserName", userId);
		List<UserInfo> Users = query.getResultList();
		if ( Users != null && Users.size() == 1) {
			return Users.get(0);
		}
		return null;
	}

	@Override
	public UserInfo create(UserInfo uInfo) {
		em.persist(uInfo);
		return uInfo;
	}

	@Override
	public UserInfo update(UserInfo uInfo) {
		return em.merge(uInfo);
	}

	@Override
	public void delete(UserInfo uInfo) {
		em.remove(uInfo);
	}

}
