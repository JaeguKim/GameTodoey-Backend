package com.gameTodoeyBackendClient.controller;

import java.util.List;

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
import com.gameTodoeyBackendClient.model.User;
import com.gameTodoeyBackendClient.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		
		List<User> theUsers = userService.getUsers();
		
		theModel.addAttribute("users",theUsers);
		
		return "list-users";
	}
	
	@GetMapping("/game/{userId}")
	public String listGamesOfUser(@PathVariable(name="userId") int userId, Model theModel) {
		
		List<Game> theGames = userService.getGamesOfUser(userId);
		theModel.addAttribute("games",theGames);
		return "game-of-user-form";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		User theUser = new User();
		
		theModel.addAttribute("user",theUser);
		
		return "user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser) {
		
		userService.saveUser(theUser);
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		
		User theUser = userService.getUser(theId);	
		
		theModel.addAttribute("user", theUser);
				
		return "user-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId) {
		
		// delete the customer
		userService.deleteUser(theId);
		
		return "redirect:/user/list";
	}
}
