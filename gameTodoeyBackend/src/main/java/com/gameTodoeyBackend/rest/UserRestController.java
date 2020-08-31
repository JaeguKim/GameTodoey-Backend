package com.gameTodoeyBackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameTodoeyBackend.entity.User;
import com.gameTodoeyBackend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getCustomers();
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable(value = "userId") int userId) {
		return userService.getUser(userId);
	}
}
