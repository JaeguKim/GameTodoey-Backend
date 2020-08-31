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

import com.gameTodoeyBackendClient.model.User;

@Service
public class UserServiceRestClientImpl implements UserService {

	private RestTemplate restTemplate;
	private String restUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public UserServiceRestClientImpl(RestTemplate theRestTemplate, 
			@Value("${user.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		restUrl = theUrl;
		
		logger.info("Loaded property : gameTodoey.rest.url=" + restUrl);
	}
	
	@Override
	public List<User> getUsers() {
		
		logger.info("in getUsers(): Calling REST API " + restUrl);

		// make REST call
		ResponseEntity<List<User>> responseEntity = 
											restTemplate.exchange(restUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<User>>() {});

		// get the list of users from response
		List<User> users = responseEntity.getBody();

		logger.info("in getUsers(): users" + users);
		
		return users;
	}

	@Override
	public User getUser(int theId) {
		logger.info("in getUser(): Calling REST API " + restUrl);

		// make REST call
		User theUser = 
				restTemplate.getForObject(restUrl + "/" + theId, 
										  User.class);

		logger.info("in saveUser(): theUser=" + theUser);
		
		return theUser;
	}
	
	@Override
	public void saveUser(User theUser) {
		
		logger.info("in saveUser(): Calling REST API " + restUrl);
		
		int userId = theUser.getId();

		// make REST call
		if (userId == 0) {
			// add employee
			restTemplate.postForEntity(restUrl, theUser, String.class);			
		
		} else {
			// update employee
			restTemplate.put(restUrl, theUser);
		}

		logger.info("in saveUser(): success");	

	}

	@Override
	public void deleteUser(int theId) {
		logger.info("in deleteUser(): Calling REST API " + restUrl);

		// make REST call
		restTemplate.delete(restUrl + "/" + theId);

		logger.info("in deleteUser(): deleted user theId=" + theId);

	}

}
