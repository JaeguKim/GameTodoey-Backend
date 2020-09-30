package com.gameTodoeyBackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Account;
import com.gameTodoeyBackend.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}
	
	@GetMapping("/accounts/{accountId}")
	public Account getAccount(@PathVariable(value = "accountId") int accountId) {
		Account theAccount = accountService.getAccount(accountId);
		
		if (theAccount == null) {
			throw new AccountNotFoundException("Account id not found - " + accountId);
		}
		return theAccount;
	}
	
	@GetMapping("/accounts/games/{accountId}")
	public List<Game> getGamesOfAccount(@PathVariable(value = "accountId") int accountId) {
		List<Game> theGames = accountService.getGamesOfAccount(accountId);
		
		if (theGames == null) {
			throw new AccountNotFoundException("Account id not found - " + accountId);
		}
		return theGames;
	}
	
	@PostMapping("/accounts")
	public Account addAccount(@RequestBody Account theAccount) {
		
		theAccount.setId(0);
		accountService.saveAccount(theAccount);
		return theAccount;
	}
	
	@PostMapping("/accounts/games/{accountId}")
	public Game addGameToAccount(@PathVariable(value="accountId") int accountId, @RequestBody Game theGame) {
		
		accountService.addGame(accountId,theGame);
		return theGame;
	}
	
	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account theAccount) {
		
		accountService.saveAccount(theAccount);
		return theAccount;
	}
	
	@DeleteMapping("/accounts/{accountId}")
	public String deleteAccount(@PathVariable(name = "accountId") int accountId) {
		
		Account theAccount = accountService.getAccount(accountId);
		
		if (theAccount == null) {
			throw new AccountNotFoundException("User id not found - " + accountId);
		}
		accountService.deleteAccount(accountId);
		return "Deleted account id - " + accountId;
	}
}
