package com.gameTodoeyBackendClient.service;

import java.util.List;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Review;

public interface ReviewService {
	
	public List<Review> getReviews(int gameId);

	public void saveReview(int gameId, Review theReview);
	
	public Review getReview(int theId);

	public void deleteReview(int theId);
}
