package com.gameTodoeyBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameTodoeyBackend.dao.ReviewDAO;
import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO reviewDAO;
	
	@Override
	@Transactional
	public List<Review> getReviews() {
		return reviewDAO.getReviews();
	}
		
	@Override
	@Transactional
	public List<Review> getReviews(int gameId) {
		return reviewDAO.getReviews(gameId);
	}

	@Override
	@Transactional
	public void saveReview(Review theReview) {

		reviewDAO.saveReview(theReview);
	}

	@Override
	@Transactional
	public Review getReview(int theId) {
		
		return reviewDAO.getReview(theId);
	}

	@Override
	@Transactional
	public void deleteReview(int theId) {

		reviewDAO.deleteReview(theId);
	}
}





