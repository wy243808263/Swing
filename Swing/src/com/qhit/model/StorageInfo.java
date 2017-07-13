package com.qhit.model;

public class StorageInfo {

	private Long year;
	private Long month;
	private Long date;

	public StorageInfo(Long year, Long month, Long date) {
		this.year = year;
		this.month = month;
		this.date = date;
	}

	public StorageInfo() {
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}
}