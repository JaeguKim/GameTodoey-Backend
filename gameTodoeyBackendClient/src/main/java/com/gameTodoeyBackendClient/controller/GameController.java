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
import com.gameTodoeyBackendClient.model.Review;
import com.gameTodoeyBackendClient.model.User;
import com.gameTodoeyBackendClient.service.GameService;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired 
	private GameService gameService;
	
	@GetMapping("/list")
	public String listGames(Model theModel) {
		
		List<Game> theGames = gameService.getGames();
		
		theModel.addAttribute("games",theGames);
		
		return "list-games";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Game theGame = new Game();
		
		theModel.addAttribute("game",theGame);
		
		return "game-form";
	}
	
	@PostMapping("/saveGame")
	public String saveGame(@ModelAttribute("game") Game theGame) {
		
		gameService.saveGame(theGame);
		
		return "redirect:/game/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("gameId") int theId, Model theModel) {
		
		Game theGame = gameService.getGame(theId);	
		
		theModel.addAttribute("game", theGame);
				
		return "game-form";
	}
	
	@GetMapping("/delete")
	public String deleteGame(@RequestParam("gameId") int theId) {
		
		// delete the customer
		gameService.deleteGame(theId);
		
		return "redirect:/game/list";
	}
}
