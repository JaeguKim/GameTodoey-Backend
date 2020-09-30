package com.gameTodoeyBackend.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gameTodoeyBackend.entity.Game;
import com.gameTodoeyBackend.entity.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Account> getAccounts() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Account> theQuery = 
				currentSession.createQuery("from Account order by lastName",
						Account.class);
		
		// execute query and get result list
		List<Account> users = theQuery.getResultList();
				
		// return the results		
		return users;
	}

	@Override
	public List<Game> getGamesOfAccount(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Account theUser = currentSession.get(Account.class,theId);
		
	    Hibernate.initialize(theUser.getGames());
		
	    return theUser.getGames();
	}
	
	@Override
	public void saveAccount(Account theAccount) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer
		currentSession.saveOrUpdate(theAccount);
		
	}
	
	@Override
	public void addGame(int accountId, Game theGame) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		String queryString = String.format("Insert Into game_user values (%s, %s)", theGame.getId(),accountId);
		Query theQuery = currentSession.createNativeQuery(queryString);
		theQuery.executeUpdate();
	}

	@Override
	public Account getAccount(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Account theAccount = currentSession.get(Account.class, theId);
		
		return theAccount;
	}

	@Override
	public void deleteAccount(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Account where id=:accountId");
		theQuery.setParameter("accountId", theId);
		
		theQuery.executeUpdate();		
	}




}











