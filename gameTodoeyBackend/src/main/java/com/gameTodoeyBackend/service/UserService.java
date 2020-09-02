package com.gameTodoeyBackend.service;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.User;

public interface UserService {

	public List<User> getUsers();
	
	public List<Game> getGamesOfUser(int userId);
	
	public void saveUser(User theCustomer);
	
	public void addGame(int userId, Game theGame);
	
	public User getUser(int theId);

	public void deleteUser(int theId);
	
}
