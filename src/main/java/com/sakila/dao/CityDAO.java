/**
 * 
 */
package com.sakila.dao;

import java.util.List;

import com.sakila.vo.CityVO;

/**
 * @author bc887d
 *
 */
public interface CityDAO {

	public List<CityVO> getAllCities();

	public CityVO getCityDetailsById(int cityId);
}
