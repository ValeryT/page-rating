package com.stiltsofr.pagerating.rest.model;

public class PageRateData {

	private Integer count;
	private Double rate;
	public PageRateData() {
		super();
	}
	
	public PageRateData(Integer count, Double rate) {
		super();
		this.count = count;
		this.rate = rate;
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	

}
