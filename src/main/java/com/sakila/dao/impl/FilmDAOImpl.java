/**
 * 
 */
package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sakila.dao.FilmDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.Film;
import com.sakila.vo.FilmVO;
import com.sakila.vo.LanguageVO;

/**
 * @author bc887d
 *
 */
@Repository("filmDAO")
public class FilmDAOImpl implements FilmDAO {

	private static final Logger logger = LoggerFactory.getLogger(FilmDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.FilmDAO#getFilms()
	 */
	@Override
	public List<FilmVO> getFilms() {
		logger.info("... Entered into getFilms() of FilmDAOImpl ...");
		List<FilmVO> films = new ArrayList<FilmVO>();
		Session session = (Session) entityManager.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Film> criteria = builder.createQuery(Film.class);
		Root<Film> contactRoot = criteria.from(Film.class);
		criteria.select(contactRoot);
		Query<Film> query = session.createQuery(criteria);

		List<Film> filmList = query.getResultList();

		for (Film film : filmList) {
			FilmVO film1 = new FilmVO();
			film1.setFilmId(film.getFilmId());
			film1.setTitle(film.getTitle());
			film1.setDescription(film.getDescription());

			if (film.getLanguage() != null) {
				LanguageVO language = SKUtility.getLanguageDetailsById(session, film.getLanguage().getLanguageId());
				film1.setLanguage(language);
			}

			if (film.getOriginalLanguage() != null) {
				LanguageVO orgLanguage = SKUtility.getLanguageDetailsById(session,
						film.getOriginalLanguage().getLanguageId());
				film1.setOriginalLanguage(orgLanguage);
			}

			film1.setRentalDuration(film.getRentalDuration());
			film1.setRentalRate(film.getRentalRate());
			film1.setLength(film.getLength());
			film1.setReplacementCost(film.getReplacementCost());
			film1.setRating(film.getRating());
			film1.setSpecialFeatures(film.getSpecialFeatures());
			film1.setLastUpdate(film.getLastUpdate());
			films.add(film1);
		}
		return films;
	}

}
