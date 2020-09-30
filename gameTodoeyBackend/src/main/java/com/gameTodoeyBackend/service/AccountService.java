package com.gameTodoeyBackend.service;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Account;

public interface AccountService {

	public List<Account> getAccounts();
	
	public List<Game> getGamesOfAccount(int userId);
	
	public void saveAccount(Account theCustomer);
	
	public void addGame(int accountId, Game theGame);
	
	public Account getAccount(int theId);

	public void deleteAccount(int theId);
	
}
