package io.egen.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQuery(name = "MStars.findByMovId", query = "SELECT e FROM MStars e, IN(e.movie) y WHERE y.id=:pMovID")
public class MStars {
	
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;
	
	private int star;
	
	@OneToOne
	@JoinColumn(name="user")
	private UserInfo user;
	
	@OneToOne
	@JoinColumn(name="movie")
	private Movie movie;

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "MStars [id=" + id + ", star=" + star + ", user=" + user + ", movie=" + movie + "]";
	}
}
