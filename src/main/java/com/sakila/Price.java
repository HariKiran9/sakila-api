/**
 * 
 */
package com.sakila;

import java.io.Serializable;
import java.util.List;

/**
 * @author bc887d
 *
 */
public class Price implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalRecords;

	private List<Trans> data;

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the data
	 */
	public List<Trans> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<Trans> data) {
		this.data = data;
	}

}
