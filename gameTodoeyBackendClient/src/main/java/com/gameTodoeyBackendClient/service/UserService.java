package com.gameTodoeyBackendClient.service;

import java.util.List;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public List<Game> getGamesOfUser(int theId);
	
	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);
}
