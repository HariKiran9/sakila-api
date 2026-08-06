package com.sakila.modal;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "LANGUAGE")
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANGUAGE_ID", unique = true, nullable = false)
	private int languageId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;

	@JsonManagedReference
	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
	private Set<Film> films;

	public Language() {
	}

	@Override
	public String toString() {
		return "{languageId:" + getLanguageId() + ", name:" + getName() + ", lastUpdate:" + getLastUpdate() + "}";
	}

	/**
	 * @return the languageId
	 */
	public int getLanguageId() {
		return languageId;
	}

	/**
	 * @param languageId the languageId to set
	 */
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the films
	 */
	public Set<Film> getFilms() {
		return films;
	}

	/**
	 * @param films the films to set
	 */
	public void setFilms(Set<Film> films) {
		this.films = films;
	}

}
