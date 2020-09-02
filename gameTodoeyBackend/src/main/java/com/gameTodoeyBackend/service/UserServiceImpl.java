package com.gameTodoeyBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameTodoeyBackend.dao.UserDAO;
import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@Override
	@Transactional
	public List<Game> getGamesOfUser(int userId) {
		return userDAO.getGamesOfUser(userId);
	}
	
	@Override
	@Transactional
	public void saveUser(User theUser) {

		userDAO.saveUser(theUser);
	}
	
	@Override
	public void addGame(int userId, Game theGame) {
		
		userDAO.addGame(userId,theGame);
	}
	
	@Override
	@Transactional
	public User getUser(int theId) {
		
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {

		userDAO.deleteUser(theId);
	}




}





