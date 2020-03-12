/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class LanguageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int languageId;

	private String name;

	private String lastUpdate;

	public LanguageVO() {
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

}
