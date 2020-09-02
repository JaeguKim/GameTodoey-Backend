package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Review;

public interface GameDAO {

	public List<Game> getGames();

	public void saveGame(Game theGame);

	public Game getGame(int theId);
	
	public void addReview(int id, Review theReview);
	
	public void deleteGame(int theId);
	
}
