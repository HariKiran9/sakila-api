/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class InventoryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int inventoryId;

	private FilmVO film;

	private StoreVO store;

	private String lastUpdate;

	public InventoryVO() {
	}

	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the film
	 */
	public FilmVO getFilm() {
		return film;
	}

	/**
	 * @param film the film to set
	 */
	public void setFilm(FilmVO film) {
		this.film = film;
	}

	/**
	 * @return the store
	 */
	public StoreVO getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(StoreVO store) {
		this.store = store;
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
