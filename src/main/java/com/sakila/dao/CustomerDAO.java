/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.CustomerVO;

/**
 * @author bc887d
 *
 */
public interface CustomerDAO {

	public List<CustomerVO> getCustomers();

}
