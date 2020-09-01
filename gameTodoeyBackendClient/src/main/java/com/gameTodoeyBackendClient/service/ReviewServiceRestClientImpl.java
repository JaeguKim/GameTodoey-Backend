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
	private String reviewUrl;
	private String gameUrl;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public ReviewServiceRestClientImpl(RestTemplate theRestTemplate, 
			@Value("${review.rest.url}") String reviewUrl, @Value("${game.rest.url}") String gameUrl) {
		
		restTemplate = theRestTemplate;
		this.reviewUrl = reviewUrl;
		this.gameUrl = gameUrl; 
		logger.info("Loaded property : review.rest.url=" + reviewUrl);
		logger.info("Loaded property : game.rest.url=" + gameUrl);
		
	}
	
	@Override
	public List<Review> getReviews(int gameId) {
		
		String requestUrl = String.format("%s/review/%s", gameUrl,gameId);
		logger.info("in getReviews(): Calling REST API " + requestUrl);

		// make REST call
		ResponseEntity<List<Review>> responseEntity = 
											restTemplate.exchange(requestUrl, HttpMethod.GET, null, 
																  new ParameterizedTypeReference<List<Review>>() {});

		// get the list of users from response
		List<Review> reviews = responseEntity.getBody();

		logger.info("in getReviews(): Reviews" + reviews);
		
		return reviews;
	}

	@Override
	public Review getReview(int theId) {
		logger.info("in getUser(): Calling REST API " + reviewUrl);

		// make REST call
		Review theReview = 
				restTemplate.getForObject(reviewUrl + "/" + theId, 
						Review.class);

		logger.info("in getReview(): theReview=" + theReview);
		
		return theReview;
	}
	
	@Override
	public void saveReview(int gameId, Review theReview) {
		String requestUrl = String.format("%s/review/%s", gameUrl,gameId);
		logger.info("in saveReview(): Calling REST API " + requestUrl);

		// make REST call
		// add review
		restTemplate.postForEntity(requestUrl, theReview, String.class);
		logger.info("in saveReview(): success");	

	}

	@Override
	public void deleteReview(int theId) {
		logger.info("in deleteReview(): Calling REST API " + reviewUrl);

		// make REST call
		restTemplate.delete(reviewUrl + "/" + theId);

		logger.info("in deleteReview(): deleted review theId=" + theId);

	}

}
