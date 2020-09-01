package com.gameTodoeyBackend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gameTodoeyBackend.entity.Review;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Review> getReviews() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		
		Query<Review> theQuery = 
				currentSession.createQuery("from Review",
						Review.class);
		
		// execute query and get result list
		List<Review> reviews = theQuery.getResultList();
				
		// return the results		
		return reviews;
	}

	@Override
	public void saveReview(Review theReview) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(theReview);
		
	}

	@Override
	public Review getReview(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Review theReview = currentSession.get(Review.class, theId);
		
		return theReview;
	}

	@Override
	public void deleteReview(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Review where id=:reviewId");
		theQuery.setParameter("reviewId", theId);
		
		theQuery.executeUpdate();		
	}

}











