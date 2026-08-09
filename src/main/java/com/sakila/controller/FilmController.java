/**
 * 
 */
package com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.modal.Film;
import com.sakila.service.FilmService;

/**
 * @author bc887d
 *
 */
@RestController
public class FilmController {

	@Autowired
	private FilmService filmService;

	@RequestMapping(value = "/film", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getFilm() {
		return new ResponseEntity<>(filmService.getFilms(), HttpStatus.OK);
	}

	@GetMapping(value = "/film/query")
	public ResponseEntity<Page<Film>> fetchFilms(@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sortBy", defaultValue = "film_id") String sortBy,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Page<Film> filmPage = filmService.getPaginatedFilms(search, page, size, sortBy, direction);
		return ResponseEntity.ok(filmPage);
	}

}
