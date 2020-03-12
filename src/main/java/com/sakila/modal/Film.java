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
@Table(name = "FILM")
public class Film implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILM_ID", unique = true, nullable = false)
	private int filmId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "RELEASE_YEAR")
	private int releaseYear;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "LANGUAGE_ID")
	private Language language;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ORIGINAL_LANGUAGE_ID", referencedColumnName = "LANGUAGE_ID")
	private Language originalLanguage;

	@Column(name = "RENTAL_DURATION")
	private int rentalDuration;

	@Column(name = "RENTAL_RATE")
	private float rentalRate;

	@Column(name = "LENGTH")
	private int length;

	@Column(name = "REPLACEMENT_COST")
	private float replacementCost;

	// ENUM('G', 'PG', 'PG-13', 'R', 'NC-17')
	@Column(name = "RATING")
	private String rating;

	// SET('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')
	@Column(name = "SPECIAL_FEATURES")
	private String specialFeatures;

	@Column(name = "LAST_UPDATE")
	private String lastUpdate;

	public Film() {
	}

	/**
	 * @return the filmId
	 */
	public int getFilmId() {
		return filmId;
	}

	/**
	 * @param filmId the filmId to set
	 */
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear the releaseYear to set
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return the originalLanguage
	 */
	public Language getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	/**
	 * @return the rentalDuration
	 */
	public int getRentalDuration() {
		return rentalDuration;
	}

	/**
	 * @param rentalDuration the rentalDuration to set
	 */
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	/**
	 * @return the rentalRate
	 */
	public float getRentalRate() {
		return rentalRate;
	}

	/**
	 * @param rentalRate the rentalRate to set
	 */
	public void setRentalRate(float rentalRate) {
		this.rentalRate = rentalRate;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the replacementCost
	 */
	public float getReplacementCost() {
		return replacementCost;
	}

	/**
	 * @param replacementCost the replacementCost to set
	 */
	public void setReplacementCost(float replacementCost) {
		this.replacementCost = replacementCost;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the specialFeatures
	 */
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	/**
	 * @param specialFeatures the specialFeatures to set
	 */
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
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
