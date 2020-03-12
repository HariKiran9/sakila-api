/**
 * 
 */
package com.sakila.service;

import java.util.List;

import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
public interface StaffService {

	public StaffVO validateUser(String emailId, String password) throws Exception;

	public List<StaffVO> getStaff();

}
