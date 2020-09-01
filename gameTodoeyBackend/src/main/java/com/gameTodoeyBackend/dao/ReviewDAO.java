package com.gameTodoeyBackend.dao;

import java.util.List;

import com.gameTodoeyBackend.entity.Review;

public interface ReviewDAO {

	public List<Review> getReviews();

	public void saveReview(Review theReview);

	public Review getReview(int theId);

	public void deleteReview(int theId);
	
}
