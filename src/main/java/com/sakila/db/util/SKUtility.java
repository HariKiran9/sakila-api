/**
 * 
 */
package com.sakila.db.util;

import org.hibernate.Session;

import com.sakila.modal.Address;
import com.sakila.modal.Category;
import com.sakila.modal.City;
import com.sakila.modal.Country;
import com.sakila.modal.Customer;
import com.sakila.modal.Film;
import com.sakila.modal.Inventory;
import com.sakila.modal.Language;
import com.sakila.modal.Rental;
import com.sakila.modal.Staff;
import com.sakila.modal.Store;
import com.sakila.vo.AddressVO;
import com.sakila.vo.CategoryVO;
import com.sakila.vo.CityVO;
import com.sakila.vo.CountryVO;
import com.sakila.vo.CustomerVO;
import com.sakila.vo.FilmVO;
import com.sakila.vo.InventoryVO;
import com.sakila.vo.LanguageVO;
import com.sakila.vo.RentalVO;
import com.sakila.vo.StaffVO;
import com.sakila.vo.StoreVO;

/**
 * @author bc887d
 *
 */
public class SKUtility {

	public static RentalVO getRentalDetailsById(Session session, int rentalId) {
		RentalVO rental = new RentalVO();

		Rental rental1 = session.load(Rental.class, rentalId);
		rental.setRentalId(rental1.getRentalId());
		rental.setRentalDate(rental1.getRentalDate());
		rental.setReturnDate(rental1.getReturnDate());

		CustomerVO customer = getCustomerDetailsById(session, rental1.getCustomer().getCustomerId());
		rental.setCustomer(customer);

		InventoryVO inventory = getInventoryDetailsById(session, rental1.getInventory().getInventoryId());
		rental.setInventory(inventory);

		StaffVO staff = getStaffDetailsById(session, rental1.getStaff().getStaffId());
		rental.setStaff(staff);

		return rental;
	}

	public static InventoryVO getInventoryDetailsById(Session session, int inventoryId) {
		InventoryVO inventory = new InventoryVO();

		Inventory inventory1 = session.get(Inventory.class, inventoryId);

		inventory.setInventoryId(inventory1.getInventoryId());

		FilmVO film = getFilmDetailsById(session, inventory1.getFilm().getFilmId());
		inventory.setFilm(film);

		StoreVO store = getStoreDetailsById(session, inventory1.getStore().getStoreId());
		inventory.setStore(store);

		return inventory;
	}

	public static CustomerVO getCustomerDetailsById(Session session, int customerId) {
		CustomerVO customer = new CustomerVO();
		Customer customer1 = session.load(Customer.class, customerId);
		customer.setCustomerId(customer1.getCustomerId());
		customer.setFirstName(customer1.getFirstName());
		customer.setLastName(customer1.getLastName());
		customer.setActive(customer1.getActive());
		return customer;
	}

	public static FilmVO getFilmDetailsById(Session session, int filmId) {
		FilmVO film = new FilmVO();

		Film film1 = session.load(Film.class, filmId);
		film.setFilmId(film1.getFilmId());
		film.setTitle(film1.getTitle());
		film.setDescription(film1.getDescription());

		LanguageVO language = getLanguageDetailsById(session, film1.getLanguage().getLanguageId());
		film.setLanguage(language);

		return film;
	}

	public static StoreVO getStoreDetailsById(Session session, int storeId) {
		StoreVO store = new StoreVO();

		Store store1 = session.load(Store.class, storeId);
		store.setStoreId(store1.getStoreId());

		StaffVO staff = getStaffDetailsById(session, store1.getStaff().getStaffId());
		store.setStaff(staff);

		AddressVO address = getAddressDetailsById(session, store1.getAddress().getAddressId());
		store.setAddress(address);

		return store;
	}

	public static StaffVO getStaffDetailsById(Session session, int staffId) {
		StaffVO staff = new StaffVO();

		Staff staff2 = session.load(Staff.class, staffId);
		staff.setStaffId(staff2.getStaffId());
		staff.setActive(staff2.getActive());
		staff.setEmail(staff2.getEmail());
		staff.setFirstName(staff2.getFirstName());
		staff.setLastName(staff2.getLastName());
		staff.setLastUpdate(staff2.getLastUpdate());
		staff.setUserName(staff2.getUserName());
		staff.setPassword(staff2.getPassword());

		AddressVO staffAddess = getAddressDetailsById(session, staff2.getAddress().getAddressId());
		staff.setAddress(staffAddess);
		return staff;
	}

	public static AddressVO getAddressDetailsById(Session session, int addressId) {
		AddressVO address = new AddressVO();
		Address address1 = session.load(Address.class, addressId);
		address.setAddressId(address1.getAddressId());
		address.setAddress(address1.getAddress());
		address.setAddress2(address1.getAddress2());
		address.setDistrict(address1.getDistrict());
		address.setLastUpdate(address1.getLastUpdate());
		address.setPhone(address.getPhone());
		address.setPostalCode(address1.getPostalCode());

		CityVO city = getCityDetailsById(session, address1.getCity().getCityId());
		address.setCity(city);

		return address;
	}

	public static CityVO getCityDetailsById(Session session, int cityId) {
		CityVO city = new CityVO();
		City city1 = session.load(City.class, cityId);
		city.setCityId(city1.getCityId());
		city.setCity(city1.getCity());
		CountryVO country = getCountryDetailsById(session, city1.getCountry().getCountryId());
		city.setCountry(country);
		city.setLastUpdate(city1.getLastUpdate());
		return city;
	}

	public static CountryVO getCountryDetailsById(Session session, int countryId) {
		CountryVO country = new CountryVO();
		Country country1 = session.load(Country.class, countryId);
		country.setCountryId(country1.getCountryId());
		country.setCountry(country1.getCountry());
		country.setLastUpdate(country1.getLastUpdate());
		return country;
	}

	public static LanguageVO getLanguageDetailsById(Session session, int languageId) {
		LanguageVO language = new LanguageVO();
		Language language1 = session.load(Language.class, languageId);
		language.setLanguageId(language1.getLanguageId());
		language.setName(language1.getName());
		language1.setLastUpdate(language.getLastUpdate());
		return language;
	}

	public static CategoryVO getCategoryDetailsById(Session session, int categoryId) {
		CategoryVO category = new CategoryVO();
		Category category1 = session.load(Category.class, categoryId);
		category.setCategoryId(category1.getCategoryId());
		category.setName(category1.getName());
		category.setLastUpdate(category1.getLastUpdate());
		return category;
	}

}
