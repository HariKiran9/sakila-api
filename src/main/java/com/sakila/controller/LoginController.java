/**
 * 
 */
package com.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.StaffService;
import com.sakila.vo.StaffVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bc887d
 *
 */
@Slf4j
@RestController
//@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private StaffService staffService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> validateUser(@RequestBody StaffVO staff) {
		log.info("...Entered into validateUser() of LoginController...");
		String userName = staff.getUserName();
		String password = staff.getPassword();
		StaffVO staff2 = null;
		try {
			staff2 = staffService.validateUser(userName, password);
		} catch (Exception e) {
			log.error("Exception: ", e);
		}
		return new ResponseEntity<>(staff2, HttpStatus.OK);
	}

}
