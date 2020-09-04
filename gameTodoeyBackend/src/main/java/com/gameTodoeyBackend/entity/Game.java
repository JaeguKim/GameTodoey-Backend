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
	/*
	IDENTITY : 기본 키 생성을 데이터베이스에 위임하는 방법 (데이터베이스에 의존적)
	- 주로 MySQL, PostgresSQL, SQL Server, DB2에서 사용합니다.
	
	SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당하는 방법 (데이터베이스에 의존적)
	- 주로 시퀀스를 지원하는 Oracle, PostgresSQL, DB2, H2에서 사용합니다. 
	- @SequenceGenerator를 사용하여 시퀀스 생성기를 등록하고, 실제 데이터베이스의 생성될 시퀀스이름을 지정해줘야 합니다.
	
	TABLE : 키 생성 테이블을 사용하는 방법
	- 키 생성 전용 테이블을 하나 만들고 여기에 이름과 값으로 사용할 컬럼을 만드는 방법입니다.
	- 테이블을 사용하므로, 데이터베이스 벤더에 상관없이 모든 데이터베이스에 적용이 가능합니다.
	
	AUTO : 데이터베이스 벤더에 의존하지 않고, 데이터베이스는 기본키를 할당하는 벙법
	- 데이터베이스에 따라서 IDENTITY, SEQUENCE, TABLE 방법 중 하나를 자동으로 선택해주는 방법입니다.
	- 예를들어, Oracle일 경우 SEQUENCE를 자동으로 선택해서 사용합니다. 따라서, 데이터베이스를 변경해도 코드를 수정할 필요가 없습니다.
	*/
	
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
