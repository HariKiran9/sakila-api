/**
 * 
 */
package com.sakila.modal;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author bc887d
 *
 */
@Entity
@DynamicUpdate
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "COUNTRY")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID", unique = true, nullable = false)
	private int countryId;

	@Column(name = "COUNTRY", unique = true, nullable = false)
	private String country;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;

	@JsonManagedReference
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private Set<City> citys;

	public Country() {
	}

	@Override
	public String toString() {
		return "{countryId:" + getCountryId() + ", country:" + getCountry() + ", lastUpdate:" + getLastUpdate() + "}";
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
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

	/**
	 * @return the city
	 */
	public Set<City> getCitys() {
		return citys;
	}

	/**
	 * @param city the city to set
	 */
	public void setCitys(Set<City> citys) {
		this.citys = citys;
	}

}
