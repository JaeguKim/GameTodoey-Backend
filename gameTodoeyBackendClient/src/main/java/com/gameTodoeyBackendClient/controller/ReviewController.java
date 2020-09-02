package com.gameTodoeyBackendClient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameTodoeyBackendClient.model.Game;
import com.gameTodoeyBackendClient.model.Review;
import com.gameTodoeyBackendClient.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/{gameId}")
	public String showReviews(@PathVariable(value = "gameId") int gameId, Model theModel) {

		List<Review> theReviews = reviewService.getReviewsOfGameId(gameId);

		theModel.addAttribute("reviews", theReviews);
		theModel.addAttribute("gameId", gameId);
		return "list-reviews";
	}

	@GetMapping("/showFormForAdd/{gameId}")
	public String showFormForAdd(@PathVariable(value="gameId") int gameId, Model theModel) {

		Review theReview = new Review();

		theModel.addAttribute("review", theReview);
		theModel.addAttribute("gameId",gameId);
		return "review-form";
	}

	@PostMapping("/saveReview")
	public String saveReview(@ModelAttribute("review") Review theReview) {
		
		reviewService.saveReview(theReview);

		return String.format("redirect:/review/%s", theReview.getGameId());
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("reviewId") int theId, Model theModel) {

		Review theReview = reviewService.getReview(theId);

		theModel.addAttribute("review", theReview);
		theModel.addAttribute("id",theId);
		return "review-form";
	}

	@GetMapping("/delete/{gameId}")
	public String deleteReview(@PathVariable(value="gameId") int gameId, @RequestParam("reviewId") int theId) {

		// delete the review
		reviewService.deleteReview(theId);

		return String.format("redirect:/review/%s", gameId);
	}
}
