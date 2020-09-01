package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;

public interface ReviewDAO {

	public List<Game> getGames();

	public void saveGame(Game theGame);

	public Game getGame(int theId);

	public void deleteGame(int theId);
	
}
