/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.RentalDAO;
import com.sakila.service.RentalService;
import com.sakila.vo.RentalVO;

/**
 * @author bc887d
 *
 */
@Service("rentalService")
public class RentalServiceImpl implements RentalService {

	@Autowired
	public RentalDAO rentalDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.RentalService#getRental()
	 */
	@Override
	public List<RentalVO> getRental() {
		return rentalDAO.getRental();
	}

}
