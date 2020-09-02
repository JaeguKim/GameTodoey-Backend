package com.gameTodoeyBackend.service;

import java.util.List;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Review;

public interface ReviewService {
	
	public List<Review> getReviews();

	public void saveReview(Review theReview);
	
	public Review getReview(int theId);
	
	public List<Review> getReviews(int gameId);
	
	public void deleteReview(int theId);
	
}
