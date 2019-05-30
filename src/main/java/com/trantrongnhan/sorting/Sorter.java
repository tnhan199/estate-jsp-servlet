package com.trantrongnhan.sorting;

public class Sorter {
	private String orderBy;
	private String sortBy;
	public Sorter(String orderBy,String sortBy) {
		this.orderBy=orderBy;
		this.sortBy=sortBy;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
}
