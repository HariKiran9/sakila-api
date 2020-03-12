/**
 * 
 */
package com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.CityService;

/**
 * @author bc887d
 *
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

	@Autowired
	private CityService cityService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getAllCities() {
		return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getCityDetailById(@PathVariable(name = "id") int cityId) {
		return new ResponseEntity<>(cityService.getCityDetailsById(cityId), HttpStatus.OK);
	}

}
