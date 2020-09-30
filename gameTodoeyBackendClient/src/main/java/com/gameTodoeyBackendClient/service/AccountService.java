package com.gameTodoeyBackendClient.service;

import java.util.List;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Account;

public interface AccountService {
	
	public List<Account> getAccounts();
	
	public List<Game> getGamesOfAccount(int theId);
	
	public void saveAccount(Account theAccount);
	
	public void addGameToAccount(int accountId, Game theGame);
	
	public Account getAccount(int theId);

	public void deleteAccount(int theId);
}
