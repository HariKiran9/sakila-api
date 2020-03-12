/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class StoreVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int storeId;

	private StaffVO staff;

	private AddressVO address;

	private String lastUpdate;

	public StoreVO() {
	}

	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the staff
	 */
	public StaffVO getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(StaffVO staff) {
		this.staff = staff;
	}

	/**
	 * @return the address
	 */
	public AddressVO getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressVO address) {
		this.address = address;
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
