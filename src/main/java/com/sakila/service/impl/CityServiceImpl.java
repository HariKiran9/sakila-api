/**
 * 
 */
package com.sakila.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakila.dao.CityDAO;
import com.sakila.service.CityService;
import com.sakila.vo.CityVO;

/**
 * @author bc887d
 *
 */
@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	public CityDAO cityDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.service.CityService#getAllCities()
	 */
	@Override
	public List<CityVO> getAllCities() {
		return cityDAO.getAllCities();
	}

	@Override
	public CityVO getCityDetailsById(int cityId) {		 
		return cityDAO.getCityDetailsById(cityId);
	}

}
