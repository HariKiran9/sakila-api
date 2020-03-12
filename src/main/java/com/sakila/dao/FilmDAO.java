/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.FilmVO;

/**
 * @author bc887d
 *
 */
public interface FilmDAO {
	
	public List<FilmVO> getFilms();

}
