package com.gameTodoeyBackendClient.service;

import java.util.List;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Review;

public interface ReviewService {
	
	public List<Review> getReviews();

	public void saveReview(Review theReview);

	public Review getReview(int theId);

	public void deleteReview(int theId);
}
