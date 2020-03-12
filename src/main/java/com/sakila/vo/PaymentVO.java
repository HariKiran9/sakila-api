/**
 * 
 */
package com.sakila.vo;

import java.io.Serializable;

/**
 * @author bc887d
 *
 */
public class PaymentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int paymentId;

	private CustomerVO customer;

	private StaffVO staff;

	private RentalVO rental;

	private float amount;

	private String paymentDate;

	private String lastUpdate;

	public PaymentVO() {
	}

	/**
	 * @return the paymentId
	 */
	public int getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	 * @return the rental
	 */
	public RentalVO getRental() {
		return rental;
	}

	/**
	 * @param rental the rental to set
	 */
	public void setRental(RentalVO rental) {
		this.rental = rental;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
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
