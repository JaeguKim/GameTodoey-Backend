package com.gameTodoeyBackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Review;
import com.gameTodoeyBackend.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewRestController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews")
	public List<Review> getReviews() {
		return reviewService.getReviews();
	}
	
	@GetMapping("/reviews/{gameId}")
	public List<Review> getReviews(@PathVariable(value="gameId") int gameId) {
		List<Review> reviews = reviewService.getReviews(gameId);
		if (reviews == null) {
			throw new GameNotFoundException("Review is not found when gameID - " + gameId);
		}
		return reviews;
	}
	
	@PostMapping("/reviews/")
	public Review saveReview(@RequestBody Review theReview) {
		
		theReview.setId(0);
		reviewService.saveReview(theReview);
		return theReview;
	}
	
	@GetMapping("/reviews/{reviewId}")
	public Review getReviewId(@PathVariable(value = "reviewId") int reviewId) {
		Review theReview = reviewService.getReview(reviewId);
		
		if (theReview == null) {
			throw new GameNotFoundException("Game id not found - " + reviewId);
		}
		return theReview;
	}

	@PutMapping("/reviews")
	public Review updateReview(@RequestBody Review theReview) {
		
		reviewService.saveReview(theReview);
		return theReview;
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public String deleteReview(@PathVariable(name = "reviewId") int reviewId) {
		
		Review theReview = reviewService.getReview(reviewId);
		
		if (theReview == null) {
			throw new GameNotFoundException("Game id not found - " + reviewId);
		}
		reviewService.deleteReview(reviewId);
		return "Deleted review id - " + reviewId;
	}
}
