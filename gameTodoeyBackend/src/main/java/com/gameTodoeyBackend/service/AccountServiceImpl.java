package com.gameTodoeyBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameTodoeyBackend.dao.AccountDAO;
import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO userDAO;
	
	@Override
	@Transactional
	public List<Account> getAccounts() {
		return userDAO.getAccounts();
	}
	
	@Override
	@Transactional
	public List<Game> getGamesOfAccount(int userId) {
		return userDAO.getGamesOfAccount(userId);
	}
	
	@Override
	@Transactional
	public void saveAccount(Account theUser) {

		userDAO.saveAccount(theUser);
	}
	
	@Override
	@Transactional
	public void addGame(int userId, Game theGame) {
		
		userDAO.addGame(userId,theGame);
	}
	
	@Override
	@Transactional
	public Account getAccount(int theId) {
		
		return userDAO.getAccount(theId);
	}

	@Override
	@Transactional
	public void deleteAccount(int theId) {

		userDAO.deleteAccount(theId);
	}




}





