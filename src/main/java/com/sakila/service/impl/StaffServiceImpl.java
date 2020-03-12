/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.StaffDAO;
import com.sakila.service.StaffService;
import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	public StaffDAO staffDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.StaffService#getStaff()
	 */
	@Override
	public List<StaffVO> getStaff() {
		return staffDAO.getStaff();
	}

	@Override
	public StaffVO validateUser(String emailId, String password) throws Exception {
		return staffDAO.validateUser(emailId, password);
	}

}
