package io.egen.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({
	@NamedQuery(name = "Comments.findByTitle", query = "SELECT e FROM Comments e, IN(e.movie) y WHERE y.id=:pMovId"),
	@NamedQuery(name = "Comments.findByUser", query = "SELECT e FROM Comments e, IN(e.user) y WHERE y.userName=:pUserName")
})
public class Comments {

	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;
	
	@Column(length = 512)
	private String comments;
	
	@OneToOne
	@JoinColumn(name="user")
	private UserInfo user;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="movie")
	private Movie movie;
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	private String date;
	private String time;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comments [id=" + id + ", comments=" + comments + ", user=" + user + ", date=" + date + ", time=" + time
				+ "]";
	}

}
