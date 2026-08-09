package com.sakila.modal;

import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "FILM")
@Getter
@Setter
@ToString
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

	// 1. Update the primary language mapping
	@JsonIgnoreProperties("films") // Replaces @JsonBackReference
	@ManyToOne
	@JoinColumn(name = "LANGUAGE_ID", referencedColumnName = "LANGUAGE_ID")
	private Language language;

	// 2. Update the original language mapping
	@JsonIgnoreProperties("films") // Replaces @JsonBackReference
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

}
