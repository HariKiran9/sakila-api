/**
 * 
 */
package com.sakila.service;

import java.util.List;

import com.sakila.vo.CityVO;

/**
 * @author bc887d
 *
 */
public interface CityService {

	public List<CityVO> getAllCities();

	public CityVO getCityDetailsById(int cityId);

}
