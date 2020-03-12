/**
 * 
 */
package com.sakila.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.service.StaffService;
import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
@RestController
//@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	StaffService staffService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<? extends Object> validateUser(@RequestBody StaffVO staff) {
		logger.info("...Entered into validateUser() of LoginController...");
		String userName = staff.getUserName();
		String password = staff.getPassword();
		StaffVO staff2 = null;
		try {
			staff2 = staffService.validateUser(userName, password);
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return new ResponseEntity<>(staff2, HttpStatus.OK);
	}

}
