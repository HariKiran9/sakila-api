
package com.sakila.modal;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "address")
@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID", unique = true, nullable = false)
	private int addressId;

	@Column(name = "address")
	private String address;

	@Column(name = "address2")
	private String address2;

	@Column(name = "district")
	private String district;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "LOCATION")
	private Blob location;

	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@JsonManagedReference
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
	private Set<Staff> staff;

}
