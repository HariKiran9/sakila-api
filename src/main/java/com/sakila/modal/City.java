/**
 * 
 */
package com.sakila.modal;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author bc887d
 *
 */
@Entity
@DynamicUpdate
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "CITY")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID", unique = true, nullable = false)
	private int cityId;

	@Column(name = "city")
	private String city;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;

	public City() {
	}

//	String country2 = "Country{countryId:" + country.getCountryId() + ", country:" + country.getCountry()
//			+ ", lastUpdate:" + country.getLastUpdate() + "}";
//	+ "country:" + country2

	@Override
	public String toString() {
		return "{cityId:" + getCityId() + ", city:" + getCity() + ", country:" + getCountry() + ", lastUpdate:"
				+ getLastUpdate() + "}";
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the countryId
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the lastUpdate
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
