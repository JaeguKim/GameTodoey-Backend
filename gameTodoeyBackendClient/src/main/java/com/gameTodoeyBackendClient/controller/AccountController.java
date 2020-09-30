package com.gameTodoeyBackendClient.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Account;
import com.gameTodoeyBackendClient.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired 
	private AccountService accountService;
	
	@GetMapping("/list")
	public String listAccounts(Model theModel) {
		
		List<Account> theAccounts = accountService.getAccounts();
		
		theModel.addAttribute("accounts",theAccounts);
		
		return "list-accounts";
	}
	
	@GetMapping("/game/{accountId}")
	public String listGamesOfAccount(@PathVariable(name="accountId") int accountId, Model theModel) {
		
		List<Game> theGames = accountService.getGamesOfAccount(accountId);
		theModel.addAttribute("games",theGames);
		theModel.addAttribute("accountId",accountId);
		return "list-games";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Account theAccount = new Account();
		
		theModel.addAttribute("account",theAccount);
		
		return "account-form";
	}
	
	@PostMapping("/saveAccount")
	public String saveAccount(@ModelAttribute("account") Account theAccount) {
		
		accountService.saveAccount(theAccount);
		
		return "redirect:/account/list";
	}
	
	@PostMapping("/addGame/{accountId}")
	public String addGameToAccount(@PathVariable(value="accountId") int accountId, 
			@ModelAttribute("game") Game theGame) {
		
		accountService.addGameToAccount(accountId,theGame);
		return String.format("redirect:/account/game/%s",accountId);
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("accountId") int theId, Model theModel) {
		
		Account theAccount = accountService.getAccount(theId);	
		
		theModel.addAttribute("account", theAccount);
				
		return "account-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("accountId") int theId) {
		
		// delete the customer
		accountService.deleteAccount(theId);
		
		return "redirect:/account/list";
	}
}
