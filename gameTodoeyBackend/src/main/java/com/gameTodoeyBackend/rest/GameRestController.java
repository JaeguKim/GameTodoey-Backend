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
import com.gameTodoeyBackend.entity.Review;
import com.gameTodoeyBackend.service.GameService;

@RestController
@RequestMapping("/api")
public class GameRestController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/games")
	public List<Game> getGames() {
		return gameService.getGames();
	}
	
	@GetMapping("/games/{gameId}")
	public Game getGame(@PathVariable(value = "gameId") int gameId) {
		Game theGame = gameService.getGame(gameId);
		
		if (theGame == null) {
			throw new GameNotFoundException("Game id not found - " + gameId);
		}
		return theGame;
	}
	
	@PostMapping("/games")
	public Game addGame(@RequestBody Game theGame) {
		
		theGame.setId(0);
		gameService.saveGame(theGame);
		return theGame;
	}
	
	@PutMapping("/games")
	public Game updateGame(@RequestBody Game theGame) {
		
		gameService.saveGame(theGame);
		return theGame;
	}
	
	@PostMapping("/games/review/{gameId}")
	public String addReview(@PathVariable(name = "gameId") int gameId, @RequestBody Review theReview) {
		
		Game theGame = gameService.getGame(gameId);
		
		if (theGame == null) {
			throw new GameNotFoundException("Game id not found - " + gameId);
		}
		gameService.addReview(gameId,theReview);
		return "Successfully added review of game id - "+gameId;
	}
	
	@DeleteMapping("/games/{gameId}")
	public String deleteGame(@PathVariable(name = "gameId") int gameId) {
		
		Game theGame = gameService.getGame(gameId);
		
		if (theGame == null) {
			throw new GameNotFoundException("Game id not found - " + gameId);
		}
		gameService.deleteGame(gameId);
		return "Deleted user id - " + gameId;
	}
}
