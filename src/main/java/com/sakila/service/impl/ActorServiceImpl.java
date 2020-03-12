/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.ActorDAO;
import com.sakila.modal.Actor;
import com.sakila.service.ActorService;

/**
 * @author bc887d
 *
 */
@Service("actorService")
@Transactional
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorDAO actorDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.ActorService#getActorDetails()
	 */
	@Override
	public List<Actor> getActorDetails() {
		return actorDAO.getActors();
	}

}
