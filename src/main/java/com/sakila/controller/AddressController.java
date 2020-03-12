/**
 * 
 */
package com.sakila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.modal.Address;
import com.sakila.service.AddressService;

/**
 * @author bc887d
 *
 */
@RestController
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getAllAddress() {
		List<Address> addressList = addressService.getAddressDetails();
//		addressList.forEach(address -> {
//			System.out.println("Address Id : " + address.getAddressId());
//		});
		return new ResponseEntity<>(addressList, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<? extends Object> getAddressDetailById(@PathVariable(name = "id") int addressId) {
		return new ResponseEntity<>(addressService.getAddressDetailsById(addressId), HttpStatus.OK);
	}
}
