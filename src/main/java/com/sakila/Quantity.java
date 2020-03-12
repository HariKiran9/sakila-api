package com.sakila;

import java.io.Serializable;
import java.util.List;

public class Quantity implements Serializable {

	private static final long serialVersionUID = 1L;

	private int totalRecords;

	private List<Trans> data;

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<Trans> getData() {
		return data;
	}

	public void setData(List<Trans> data) {
		this.data = data;
	}

}
