package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.User;

public interface UserDAO {

	public List<User> getUsers();
	
	public List<Game> getGamesOfUser(int theId);
	
	public void saveUser(User theUser);
	
	public void addGame(int userId, Game theGame);
	
	public User getUser(int theId);

	public void deleteUser(int theId);
	
}
