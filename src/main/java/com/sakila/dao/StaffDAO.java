/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
public interface StaffDAO {

	public StaffVO validateUser(String emailId, String password) throws Exception;

	public List<StaffVO> getStaff();

}
