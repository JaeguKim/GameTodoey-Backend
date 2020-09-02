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
import com.gameTodoeyBackend.entity.User;
import com.gameTodoeyBackend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable(value = "userId") int userId) {
		User theUser = userService.getUser(userId);
		
		if (theUser == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}
		return theUser;
	}
	
	@GetMapping("/users/games/{userId}")
	public List<Game> getGamesOfUsers(@PathVariable(value = "userId") int userId) {
		List<Game> theGames = userService.getGamesOfUser(userId);
		
		if (theGames == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}
		return theGames;
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		
		theUser.setId(0);
		userService.saveUser(theUser);
		return theUser;
	}
	
	@PostMapping("/users/games/{userId}")
	public Game addGameToUser(@PathVariable(value="userId") int userId, @RequestBody Game theGame) {
		
		userService.addGame(userId,theGame);
		return theGame;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User theUser) {
		
		userService.saveUser(theUser);
		return theUser;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable(name = "userId") int userId) {
		
		User theUser = userService.getUser(userId);
		
		if (theUser == null) {
			throw new UserNotFoundException("User id not found - " + userId);
		}
		userService.deleteUser(userId);
		return "Deleted user id - " + userId;
	}
}
