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

		List<Review> theReviews = reviewService.getReviews(gameId);

		theModel.addAttribute("reviews", theReviews);

		return "list-reviews";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("reviewId") int theId, Model theModel) {

		Review theReview = new Review();

		theModel.addAttribute("review", theReview);
		theModel.addAttribute("id",theId);
		return "review-form";
	}

	@PostMapping("/saveReview")
	public String saveReview(int gameId, @ModelAttribute("review") Review theReview) {

		reviewService.saveReview(theReview);

		return String.format("redirect:/review/%s", gameId);
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("reviewId") int theId, Model theModel) {

		Review theReview = reviewService.getReview(theId);

		theModel.addAttribute("review", theReview);
		theModel.addAttribute("id",theId);
		return "review-form";
	}

	@GetMapping("/delete")
	public String deleteReview(int gameId, @RequestParam("reviewId") int theId) {

		// delete the review
		reviewService.deleteReview(theId);

		return String.format("redirect:/review/%s", gameId);
	}
}
