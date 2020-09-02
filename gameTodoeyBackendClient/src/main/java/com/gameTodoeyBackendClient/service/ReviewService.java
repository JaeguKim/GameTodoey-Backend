package com.gameTodoeyBackendClient.service;

import java.util.List;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Review;

public interface ReviewService {
	
	public Review getReview(int theId);
	
	public List<Review> getReviewsOfGameId(int gameId);
	
	public void saveReview(Review theReview);
	
	public void deleteReview(int theId);
}
