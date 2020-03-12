/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class RentalVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rentalId;

	private String rentalDate;

	private InventoryVO inventory;

	private CustomerVO customer;

	private String returnDate;

	private StaffVO staff;

	private String lastUpdate;

	public RentalVO() {
	}

	/**
	 * @return the rentalId
	 */
	public int getRentalId() {
		return rentalId;
	}

	/**
	 * @param rentalId the rentalId to set
	 */
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	/**
	 * @return the rentalDate
	 */
	public String getRentalDate() {
		return rentalDate;
	}

	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	/**
	 * @return the inventory
	 */
	public InventoryVO getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(InventoryVO inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the customer
	 */
	public CustomerVO getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}

	/**
	 * @return the returnDate
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
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
