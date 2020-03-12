/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.modal.Address;

/**
 * @author bc887d
 *
 */
public interface AddressDAO {

	public List<Address> getAddressDetails();

	public Address getAddressDetailsById(int addressId);
}
