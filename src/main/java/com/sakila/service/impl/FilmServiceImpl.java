/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.FilmDAO;
import com.sakila.service.FilmService;
import com.sakila.vo.FilmVO;

/**
 * @author bc887d
 *
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService {

	@Autowired
	public FilmDAO filmDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.FilmService#getFilms()
	 */
	@Override
	public List<FilmVO> getFilms() {
		// TODO Auto-generated method stub
		return filmDAO.getFilms();
	}

}
