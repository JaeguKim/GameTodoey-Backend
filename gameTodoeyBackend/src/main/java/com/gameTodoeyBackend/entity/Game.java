package com.gameTodoeyBackend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="game")
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="title")
	private String title;
	
	@Column(name="popularity")
	private int popularity;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="game_id")
	@JsonIgnore
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY, 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="game_user",joinColumns=@JoinColumn(name="game_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	@JsonIgnore
	private List<User> users;
	
	public Game() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review theReview) {
		
		if (reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(theReview);
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) { 
		
		if (users == null) {
			users = new ArrayList<>();
		}
		
		users.add(user);
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", popularity=" + popularity + "]";
	}

}
