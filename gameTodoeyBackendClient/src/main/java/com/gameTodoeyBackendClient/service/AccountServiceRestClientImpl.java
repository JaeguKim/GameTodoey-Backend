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
public class AccountServiceRestClientImpl implements AccountService {

	private RestTemplate restTemplate;
	private String restUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public AccountServiceRestClientImpl(RestTemplate theRestTemplate, 
			@Value("${account.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		restUrl = theUrl;
		
		logger.info("Loaded property : gameTodoey.rest.url=" + restUrl);
	}
	
	@Override
	public List<Account> getAccounts() {
		
		logger.info("in getAccounts(): Calling REST API " + restUrl);

		// make REST call
		ResponseEntity<List<Account>> responseEntity = 
											restTemplate.exchange(restUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Account>>() {});

		// get the list of users from response
		List<Account> accounts = responseEntity.getBody();

		logger.info("in getAccounts(): accounts" + accounts);
		
		return accounts;
	}

	@Override
	public List<Game> getGamesOfAccount(int theId) {
		
		String requestUrl = String.format("%s/games/%s", restUrl,theId);
		logger.info("in getGamesOfAccount(): Calling REST API " + requestUrl);

		// make REST call
		ResponseEntity<List<Game>> responseEntity = 
											restTemplate.exchange(requestUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Game>>() {});

		// get the list of users from response
		List<Game> games = responseEntity.getBody();

		logger.info("in getGamesOfUser(): games" + games);
		
		return games;

	}
	
	@Override
	public Account getAccount(int theId) {
		logger.info("in getAccount(): Calling REST API " + restUrl);

		// make REST call
		Account theAccount = 
				restTemplate.getForObject(restUrl + "/" + theId, 
										  Account.class);

		logger.info("in saveAccount(): theUser=" + theAccount);
		
		return theAccount;
	}
	
	@Override
	public void saveAccount(Account theAccount) {
		
		logger.info("in saveUser(): Calling REST API " + restUrl);
		
		int accountId = theAccount.getId();

		// make REST call
		if (accountId == 0) {
			// add employee
			restTemplate.postForEntity(restUrl, theAccount, String.class);			
		
		} else {
			// update employee
			restTemplate.put(restUrl, theAccount);
		}

		logger.info("in saveAccount(): success");	

	}
	
	@Override
	public void addGameToAccount(int userId, Game theGame) {
		
		String requestUrl = String.format("%s/games/%s", restUrl,userId);
		logger.info("in addGame(): Calling REST API " + requestUrl);

		restTemplate.postForEntity(requestUrl, theGame, String.class);	

		logger.info("in addGame(): success");	
	}
	
	@Override
	public void deleteAccount(int theId) {
		logger.info("in deleteUser(): Calling REST API " + restUrl);

		// make REST call
		restTemplate.delete(restUrl + "/" + theId);

		logger.info("in deleteUser(): deleted user theId=" + theId);

	}

	



}
