/**
 * 
 */
package com.sakila.service;

import java.util.List;

import com.sakila.modal.Address;

/**
 * @author bc887d
 *
 */
public interface AddressService {

	public List<Address> getAddressDetails();

	public Address getAddressDetailsById(int addressId);
}
