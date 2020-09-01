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

import com.gameTodoeyBackendClient.model.Review;

@Service
public class ReviewServiceRestClientImpl implements ReviewService {

	private RestTemplate restTemplate;
	private String restUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public ReviewServiceRestClientImpl(RestTemplate theRestTemplate, 
			@Value("${game.rest.url}") String theUrl) {
		
		restTemplate = theRestTemplate;
		restUrl = theUrl;
		
		logger.info("Loaded property : gameTodoey.rest.url=" + restUrl);
	}
	
	@Override
	public List<Review> getReviews() {
		
		logger.info("in getReviews(): Calling REST API " + restUrl);

		// make REST call
		ResponseEntity<List<Review>> responseEntity = 
											restTemplate.exchange(restUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Review>>() {});

		// get the list of users from response
		List<Review> reviews = responseEntity.getBody();

		logger.info("in getReviews(): Reviews" + reviews);
		
		return reviews;
	}

	@Override
	public Review getReview(int theId) {
		logger.info("in getUser(): Calling REST API " + restUrl);

		// make REST call
		Review theReview = 
				restTemplate.getForObject(restUrl + "/" + theId, 
						Review.class);

		logger.info("in saveReview(): theReview=" + theReview);
		
		return theReview;
	}
	
	@Override
	public void saveReview(Review theReview) {
		
		logger.info("in saveReview(): Calling REST API " + restUrl);
		
		int reviewId = theReview.getId();

		// make REST call
		if (reviewId == 0) {
			// add review
			restTemplate.postForEntity(restUrl, theReview, String.class);			
		
		} else {
			// update review
			restTemplate.put(restUrl, theReview);
		}

		logger.info("in saveReview(): success");	

	}

	@Override
	public void deleteReview(int theId) {
		logger.info("in deleteReview(): Calling REST API " + restUrl);

		// make REST call
		restTemplate.delete(restUrl + "/" + theId);

		logger.info("in deleteReview(): deleted review theId=" + theId);

	}

}
