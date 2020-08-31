package com.gameTodoeyBackend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gameTodoeyBackend.entity.Game;

@Repository
public class GameDAOImpl implements GameDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Game> getGames() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		
		Query<Game> theQuery = 
				currentSession.createQuery("from game order by popularity DESC",
						Game.class);
		
		// execute query and get result list
		List<Game> games = theQuery.getResultList();
				
		// return the results		
		return games;
	}

	@Override
	public void saveGame(Game theGame) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(theGame);
		
	}

	@Override
	public Game getGame(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Game theGame = currentSession.get(Game.class, theId);
		
		return theGame;
	}

	@Override
	public void deleteGame(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from game where id=:gameId");
		theQuery.setParameter("gameId", theId);
		
		theQuery.executeUpdate();		
	}

}











