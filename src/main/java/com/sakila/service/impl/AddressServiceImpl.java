package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.AddressDAO;
import com.sakila.modal.Address;
import com.sakila.service.AddressService;

import jakarta.transaction.Transactional;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDAO addressDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.AddressService#getAddressDetails()
	 */
	@Override
	public List<Address> getAddressDetails() {
		return addressDAO.getAddressDetails();
	}

	@Override
	public Address getAddressDetailsById(int addressId) {
		return addressDAO.getAddressDetailsById(addressId);
	}

}
