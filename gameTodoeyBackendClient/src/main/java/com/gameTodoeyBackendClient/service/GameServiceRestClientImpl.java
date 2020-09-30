package com.gameTodoeyBackendClient.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Account;

@Service
public class GameServiceRestClientImpl implements GameService {

	private RestTemplate restTemplate;
	private String restUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public GameServiceRestClientImpl(RestTemplate theRestTemplate, 
			@Value("${game.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		restUrl = theUrl;
		
		logger.info("Loaded property : gameTodoey.rest.url=" + restUrl);
	}
	
	@Override
	public List<Game> getGames() {
		
		logger.info("in getGames(): Calling REST API " + restUrl);

		// make REST call
		ResponseEntity<List<Game>> responseEntity = 
											restTemplate.exchange(restUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Game>>() {});

		// get the list of users from response
		List<Game> games = responseEntity.getBody();

		logger.info("in getGames(): games" + games);
		
		return games;
	}

	@Override
	public Game getGame(int theId) {
		logger.info("in getUser(): Calling REST API " + restUrl);

		// make REST call
		Game theGame = 
				restTemplate.getForObject(restUrl + "/" + theId, 
										  Game.class);

		logger.info("in saveGame(): theGame=" + theGame);
		
		return theGame;
	}
	
	@Override
	public void saveGame(Game theGame) {
		
		logger.info("in saveGame(): Calling REST API " + restUrl);
		
		int gameId = theGame.getId();

		// make REST call
		if (gameId == 0) {
			// add game
			restTemplate.postForEntity(restUrl, theGame, String.class);			
		
		} else {
			// update game
			restTemplate.put(restUrl, theGame);
		}

		logger.info("in saveGame(): success");	

	}

	@Override
	public void deleteGame(int theId) {
		logger.info("in deleteGame(): Calling REST API " + restUrl);

		// make REST call
		restTemplate.delete(restUrl + "/" + theId);

		logger.info("in deleteGame(): deleted game theId=" + theId);

	}

}
