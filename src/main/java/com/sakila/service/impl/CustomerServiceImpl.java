/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.CustomerDAO;
import com.sakila.service.CustomerService;
import com.sakila.vo.CustomerVO;

/**
 * @author bc887d
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	public CustomerDAO customerDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.CustomerService#getCustomers()
	 */
	@Override
	public List<CustomerVO> getCustomers() {
		return customerDAO.getCustomers();
	}

}
