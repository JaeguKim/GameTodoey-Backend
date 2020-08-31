package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.User;

public interface GameDAO {

	public List<User> getGames();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
	
}
