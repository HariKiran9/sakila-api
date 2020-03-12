/**
 * 
 */
package com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.FilmService;

/**
 * @author bc887d
 *
 */
@RestController
@RequestMapping(value = "/film")
public class FilmController {

	@Autowired
	public FilmService filmService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getFilm() {
		return new ResponseEntity<>(filmService.getFilms(), HttpStatus.OK);
	}

}
