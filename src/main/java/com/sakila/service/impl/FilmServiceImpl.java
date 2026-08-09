/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sakila.dao.FilmDAO;
import com.sakila.modal.Film;
import com.sakila.repository.FilmRepository;
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
	
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public List<FilmVO> getFilms() {
		return filmDAO.getFilms();
	}
	
	public Page<Film> getPaginatedFilms(String search, int page, int size, String sortBy, String direction) {
        // Enforce safe property sorting fall-backs
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // Inject native SQL wildcard tags safely
        String querySearch = (search == null || search.trim().isEmpty()) ? "%" : "%" + search.trim() + "%";
        
        return filmRepository.findFilmsWithCount(querySearch, pageable);
    }

}
