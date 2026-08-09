package com.sakila.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sakila.modal.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

	/**
	 * Executes optimized queries tailored for column-oriented storage partitions.
	 */
//	@Query(value = "SELECT film_id, title, description, release_year, language_id, original_language_id, "
//			+ "rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update "
//			+ "FROM sakila.film WHERE title LIKE :searchText", 
//			countQuery = "SELECT COUNT(1) FROM sakila.film WHERE title LIKE :searchText", 
//			nativeQuery = true)
	
	
//	@Query(value = "SELECT f.film_id, f.title, f.description, f.release_year, "
//			+ "f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, "
//			+ "f.special_features AS special_features, f.last_update, f.original_language_id, f.language_id AS language_id, "
//			+ "l.language_id AS language_id, l.name AS name, l.last_update AS last_update"
//			+ " FROM sakila.film f "
//			+ "LEFT JOIN sakila.language l ON f.language_id = l.language_id "
//			+ "WHERE f.title LIKE :searchText", 
//			countQuery = "SELECT COUNT(1) FROM sakila.film WHERE title LIKE :searchText", 
//			nativeQuery = true)
	
	// Set nativeQuery = false (or remove it entirely)
	@Query(value = "SELECT f FROM Film f JOIN FETCH f.language WHERE f.title LIKE :searchText")
	Page<Film> findFilmsWithCount(@Param("searchText") String searchText, Pageable pageable);
}
