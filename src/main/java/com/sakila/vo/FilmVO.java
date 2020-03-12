/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class FilmVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int filmId;

	private String title;

	private String description;

	private int releaseYear;

	private LanguageVO language;

	private LanguageVO originalLanguage;

	private int rentalDuration;

	private float rentalRate;

	private int length;

	private float replacementCost;

	// ENUM('G', 'PG', 'PG-13', 'R', 'NC-17')
	private String rating;

	// SET('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')
	private String specialFeatures;

	private String lastUpdate;

	public FilmVO() {
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
	 * @return the languageVO
	 */
	public LanguageVO getLanguage() {
		return language;
	}

	/**
	 * @param languageVO the languageVO to set
	 */
	public void setLanguage(LanguageVO language) {
		this.language = language;
	}

	/**
	 * @return the originalLanguage
	 */
	public LanguageVO getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * @param originalLanguage the originalLanguage to set
	 */
	public void setOriginalLanguage(LanguageVO originalLanguage) {
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
