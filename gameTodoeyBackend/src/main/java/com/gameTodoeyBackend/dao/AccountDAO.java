package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Account;

public interface AccountDAO {

	public List<Account> getAccounts();
	
	public List<Game> getGamesOfAccount(int theId);
	
	public void saveAccount(Account theUser);
	
	public void addGame(int userId, Game theGame);
	
	public Account getAccount(int theId);

	public void deleteAccount(int theId);
	
}
