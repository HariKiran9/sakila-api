/**
 * 
 */
package com.sakila.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sakila.dao.ActorDAO;
import com.sakila.modal.Actor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bc887d
 *
 */
@Slf4j
@Repository("actorDAO")
public class ActorDAOImpl implements ActorDAO {

	@PersistenceContext
	private EntityManager entityManagerFactory;

	@Override
	public List<Actor> getActors() {
		log.info("... Entered into getActors() of ActorDAOImpl ...");
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
