/**
 * 
 */
package com.sakila.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sakila.modal.Film;
import com.sakila.vo.FilmVO;

/**
 * @author bc887d
 *
 */
public interface FilmService {

	List<FilmVO> getFilms();

	Page<Film> getPaginatedFilms(String search, int page, int size, String sortBy, String direction);

}
