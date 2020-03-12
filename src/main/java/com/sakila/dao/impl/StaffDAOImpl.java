/**
 * 
 */
package com.sakila.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sakila.dao.StaffDAO;
import com.sakila.modal.Address;
import com.sakila.modal.City;
import com.sakila.modal.Country;
import com.sakila.modal.Staff;
import com.sakila.vo.AddressVO;
import com.sakila.vo.CityVO;
import com.sakila.vo.CountryVO;
import com.sakila.vo.StaffVO;

/**
 * @author bc887d
 *
 */
@Repository("staffDAO")
public class StaffDAOImpl implements StaffDAO {

	private static final Logger logger = LoggerFactory.getLogger(StaffDAOImpl.class);

	@PersistenceContext
	public EntityManager entityManagerFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.StaffDAO#getStaff()
	 */
	@Override
	public List<StaffVO> getStaff() {
		logger.info("... Entered into getStaff() of StaffDAOImpl ...");
		List<StaffVO> staffVOList = new ArrayList<StaffVO>();
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> criteria = builder.createQuery(Staff.class);
		Root<Staff> contactRoot = criteria.from(Staff.class);
		criteria.select(contactRoot);
		Query<Staff> query = session.createQuery(criteria);

		List<Staff> staffList = query.getResultList();
		for (Staff staff2 : staffList) {
			StaffVO staff = new StaffVO();
			staff.setStaffId(staff2.getStaffId());
			staff.setActive(staff2.getActive());
			staff.setEmail(staff2.getEmail());
			staff.setFirstName(staff2.getFirstName());
			staff.setLastName(staff2.getLastName());
			staff.setLastUpdate(staff2.getLastUpdate());
			staff.setUserName(staff2.getUserName());
			staff.setPassword(staff2.getPassword());

			AddressVO address = new AddressVO();
			Address address2 = getAddressDetailsById(staff2.getAddress().getAddressId());
			address.setAddressId(address2.getAddressId());
			address.setAddress(address2.getAddress());
			address.setAddress2(address2.getAddress2());

			CityVO city = getCityDetailsById(address2.getCity().getCityId());

			CountryVO country = new CountryVO();
			if (city.getCountry() != null) {
				Country country2 = getCountryDetailsById(city.getCountry().getCountryId());
				country.setCountryId(country2.getCountryId());
				country.setCountry(country2.getCountry());
				country.setLastUpdate(country2.getLastUpdate());
				city.setCountry(country);
			}

			address.setCity(city);
			address.setDistrict(address2.getDistrict());
			address.setLastUpdate(address2.getLastUpdate());
			address.setPhone(address2.getPhone());
			address.setPostalCode(address2.getPostalCode());

			staff.setAddress(address);
			staffVOList.add(staff);
		}
		return staffVOList;
	}

	public CityVO getCityDetailsById(int cityId) {
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<City> countryCretieriaQuery = builder.createQuery(City.class);
		Root<City> countryRoot = countryCretieriaQuery.from(City.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("cityId"), cityId));
		countryCretieriaQuery.select(countryRoot);
		Query<City> countryQuery = session.createQuery(countryCretieriaQuery);
		City city1 = countryQuery.getSingleResult();

		CityVO city = new CityVO();
		city.setCityId(city1.getCityId());
		city.setCity(city1.getCity());
		city.setLastUpdate(city1.getLastUpdate());

		CountryVO country = new CountryVO();
		if (city.getCountry() != null) {
			Country country1 = getCountryDetailsById(city.getCountry().getCountryId());
			country.setCountryId(country1.getCountryId());
			country.setCountry(country1.getCountry());
			country.setLastUpdate(country1.getLastUpdate());
			city.setCountry(country);
		}
		System.out.println("City Obj : " + city);
		return city;
	}

	public Country getCountryDetailsById(int countryId) {
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Country> countryCretieriaQuery = builder.createQuery(Country.class);
		Root<Country> countryRoot = countryCretieriaQuery.from(Country.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("countryId"), countryId));
		countryCretieriaQuery.select(countryRoot);
		Query<Country> countryQuery = session.createQuery(countryCretieriaQuery);
		Country country = countryQuery.getSingleResult();
		System.out.println("Country Obj : " + country);
		return country;
	}

	public Address getAddressDetailsById(int addressId) {
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> countryCretieriaQuery = builder.createQuery(Address.class);
		Root<Address> countryRoot = countryCretieriaQuery.from(Address.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("addressId"), addressId));
		Query<Address> countryQuery = session.createQuery(countryCretieriaQuery);
		Address address = countryQuery.getSingleResult();
		System.out.println("Address Obj : " + address);
		return address;
	}

	@Override
	public StaffVO validateUser(String emailId, String password) throws Exception {
		StaffVO staff = new StaffVO();
		Session session = (Session) entityManagerFactory.getDelegate();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> cretieriaQuery = builder.createQuery(Staff.class);
		Root<Staff> root = cretieriaQuery.from(Staff.class);
		cretieriaQuery.select(root);
		cretieriaQuery.where(builder.equal(root.get("email"), emailId));
		Query<Staff> countryQuery = session.createQuery(cretieriaQuery);
		Staff staffObj = countryQuery.getSingleResult();

//		Staff staffObj = session.load(Staff.class, emailId);
		String password1 = staffObj.getPassword();
		if (staffObj.getEmail().equals(emailId)) {
			staff.setFirstName(staffObj.getFirstName());
			staff.setLastName(staffObj.getLastName());
			if (password1.equals(password)) {
				staff.setFirstName(staffObj.getFirstName());
				staff.setLastName(staffObj.getLastName());
			} else {
				throw new Exception("Password not matched !!!");
			}
		} else {
			throw new Exception("Username not matched !!!");
		}
		return staff;
	}

}
