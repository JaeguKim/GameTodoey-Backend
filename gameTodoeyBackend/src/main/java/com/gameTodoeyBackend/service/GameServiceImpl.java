package com.gameTodoeyBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameTodoeyBackend.dao.GameDAO;
import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Review;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDAO gameDAO;
	
	@Override
	@Transactional
	public List<Game> getGames() {
		return gameDAO.getGames();
	}

	@Override
	@Transactional
	public void saveGame(Game theGame) {

		gameDAO.saveGame(theGame);
	}

	@Override
	@Transactional
	public Game getGame(int theId) {
		
		return gameDAO.getGame(theId);
	}

	@Override
	@Transactional
	public void deleteGame(int theId) {

		gameDAO.deleteGame(theId);
	}

	@Override
	@Transactional
	public void addReview(int theId, Review theReview) {
		gameDAO.addReview(theId, theReview);
	}
}





