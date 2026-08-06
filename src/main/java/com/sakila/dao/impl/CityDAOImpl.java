
package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sakila.dao.CityDAO;
import com.sakila.db.util.SKUtility;
import com.sakila.modal.City;
import com.sakila.vo.CityVO;
import com.sakila.vo.CountryVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("cityDAO")
public class CityDAOImpl implements CityDAO {

	@PersistenceContext
	public EntityManager entityManagerFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.CityDAO#getAllCities()
	 */
	@Override
	public List<CityVO> getAllCities() {
		log.info("... Entered into getAllCities() of CityDAOImpl ...");
		List<CityVO> cityList = new ArrayList<CityVO>();
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<City> criteria = builder.createQuery(City.class);
		Root<City> contactRoot = criteria.from(City.class);
		criteria.select(contactRoot);
		Query<City> query = session.createQuery(criteria);
//		query.setFirstResult(1);
//		query.setMaxResults(100);
		List<City> cities = query.getResultList();
		System.out.println("City Size : " + cities.size());
		for (City city : cities) {
			CityVO cityVO = new CityVO();
			cityVO.setCityId(city.getCityId());
			cityVO.setCity(city.getCity());
			cityVO.setLastUpdate(city.getLastUpdate());

			CountryVO country = SKUtility.getCountryDetailsById(session, city.getCountry().getCountryId());
			cityVO.setCountry(country);
			cityList.add(cityVO);
		}
		return cityList;
	}

	@Override
	public CityVO getCityDetailsById(int cityId) {
		Session session = (Session) entityManagerFactory.getDelegate();
		CityVO city = SKUtility.getCityDetailsById(session, cityId);
		CountryVO country = SKUtility.getCountryDetailsById(session, city.getCountry().getCountryId());
		city.setCountry(country);
		System.out.println("City Obj : " + city);
		return city;
	}

}
