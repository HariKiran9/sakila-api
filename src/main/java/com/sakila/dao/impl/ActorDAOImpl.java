/**
 * 
 */
package com.sakila.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sakila.dao.ActorDAO;
import com.sakila.modal.Actor;

/**
 * @author bc887d
 *
 */
@Repository("actorDAO")
public class ActorDAOImpl implements ActorDAO {

	private static final Logger logger = LoggerFactory.getLogger(ActorDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManagerFactory;

	@Override
	public List<Actor> getActors() {
		logger.info("... Entered into getActors() of ActorDAOImpl ..."); 
//		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
//		Session session = sessionFactory.getCurrentSession();
		
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Actor> criteria = builder.createQuery(Actor.class);
		Root<Actor> contactRoot = criteria.from(Actor.class);
		criteria.select(contactRoot);
		return session.createQuery(criteria).getResultList();
	}

}
