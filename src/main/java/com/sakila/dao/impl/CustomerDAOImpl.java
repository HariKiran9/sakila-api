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

import com.sakila.dao.CustomerDAO;
import com.sakila.modal.Address;
import com.sakila.modal.City;
import com.sakila.modal.Country;
import com.sakila.modal.Customer;
import com.sakila.modal.Staff;
import com.sakila.modal.Store;
import com.sakila.vo.AddressVO;
import com.sakila.vo.CityVO;
import com.sakila.vo.CountryVO;
import com.sakila.vo.CustomerVO;
import com.sakila.vo.StaffVO;
import com.sakila.vo.StoreVO;

/**
 * @author bc887d
 *
 */
@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

	private static final Logger loger = LoggerFactory.getLogger(CustomerDAOImpl.class);

	@PersistenceContext
	public EntityManager entityManagerFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sakila.dao.CustomerDAO#getCustomers()
	 */
	@Override
	public List<CustomerVO> getCustomers() {
		loger.info("... Entered into getStaff() of StaffDAOImpl ...");
		List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> contactRoot = criteria.from(Customer.class);
		criteria.select(contactRoot);
		Query<Customer> query = session.createQuery(criteria);
		List<Customer> customerList = query.getResultList();

		for (Customer customer1 : customerList) {
			CustomerVO customer = new CustomerVO();
			customer.setCustomerId(customer1.getCustomerId());
			customer.setFirstName(customer1.getFirstName());
			customer.setLastName(customer1.getLastName());
			customer.setEmail(customer1.getEmail());
			customer.setActive(customer1.getActive());
			customer.setLastUpdate(customer1.getLastUpdate());

			AddressVO address = getAddressDetailsById(customer1.getAddress().getAddressId());
			customer.setAddress(address);

			StoreVO store = getStoreDetailsById(customer1.getStore().getStoreId());
			customer.setStore(store);
			customerVOList.add(customer);
		} // for

		return customerVOList;
	}

	private AddressVO getAddressDetailsById(int addressId) {
		AddressVO address = new AddressVO();
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> countryCretieriaQuery = builder.createQuery(Address.class);
		Root<Address> countryRoot = countryCretieriaQuery.from(Address.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("addressId"), addressId));
		countryCretieriaQuery.select(countryRoot);
		Query<Address> countryQuery = session.createQuery(countryCretieriaQuery);
		Address address1 = countryQuery.getSingleResult();

		address.setAddressId(address1.getAddressId());
		address.setAddress(address1.getAddress());
		address.setAddress2(address1.getAddress2());		
		address.setDistrict(address1.getDistrict());
		address.setPhone(address1.getPhone());
		address.setLastUpdate(address1.getLastUpdate());

		CityVO city = getCityDetailsById(address1.getCity().getCityId());
		address.setCity(city);

		return address;

	}

	private CityVO getCityDetailsById(int cityId) {
		CityVO city = new CityVO();

		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<City> countryCretieriaQuery = builder.createQuery(City.class);
		Root<City> countryRoot = countryCretieriaQuery.from(City.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("cityId"), cityId));
		countryCretieriaQuery.select(countryRoot);
		Query<City> countryQuery = session.createQuery(countryCretieriaQuery);
		City city1 = countryQuery.getSingleResult();

		city.setCityId(city1.getCityId());
		city.setCity(city1.getCity());

		CountryVO country = getCountryDetailsById(city1.getCountry().getCountryId());
		city.setCountry(country);
		return city;
	}

	private CountryVO getCountryDetailsById(int countryId) {

		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Country> countryCretieriaQuery = builder.createQuery(Country.class);
		Root<Country> countryRoot = countryCretieriaQuery.from(Country.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("countryId"), countryId));
		countryCretieriaQuery.select(countryRoot);
		Query<Country> countryQuery = session.createQuery(countryCretieriaQuery);
		Country country1 = countryQuery.getSingleResult();

		CountryVO country = new CountryVO();
		country.setCountryId(country1.getCountryId());
		country.setCountry(country1.getCountry());
		return country;

	}

	private StoreVO getStoreDetailsById(int storeId) {
		StoreVO store = new StoreVO();

		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Store> countryCretieriaQuery = builder.createQuery(Store.class);
		Root<Store> countryRoot = countryCretieriaQuery.from(Store.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("storeId"), storeId));
		countryCretieriaQuery.select(countryRoot);
		Query<Store> countryQuery = session.createQuery(countryCretieriaQuery);
		Store store1 = countryQuery.getSingleResult();
		store.setStoreId(store1.getStoreId());

		StaffVO staff = getStaffDetailsById(store1.getStaff().getStaffId());
		store.setStaff(staff);

		AddressVO address = getAddressDetailsById(store1.getAddress().getAddressId());
		store.setAddress(address);

		return store;
	}

	private StaffVO getStaffDetailsById(int staffId) {
		StaffVO staff = new StaffVO();

		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> countryCretieriaQuery = builder.createQuery(Staff.class);
		Root<Staff> countryRoot = countryCretieriaQuery.from(Staff.class);
		countryCretieriaQuery.select(countryRoot);
		countryCretieriaQuery.where(builder.equal(countryRoot.get("staffId"), staffId));
		countryCretieriaQuery.select(countryRoot);
		Query<Staff> countryQuery = session.createQuery(countryCretieriaQuery);
		Staff staff1 = countryQuery.getSingleResult();
		staff.setStaffId(staff1.getStaffId());
		staff.setFirstName(staff1.getFirstName());
		staff.setLastName(staff1.getLastName());
		return staff;
	}

}
