package com.stiltsoft.pagerating.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Rate  implements Serializable{
	@XmlElement
	private String rateId;
	@XmlElement
	private Long pageId;
	@XmlElement
	private String userId;
	@XmlElement
	private Integer rate;
	
	public Rate() {
	}

	public Rate(String rateId, Long pageId, String userId, Integer rate) {
		super();
		if(rateId==null)
			this.rateId = userId+"_"+pageId;
		this.pageId = pageId;
		this.userId = userId;
		this.rate = rate;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rate [rateId=");
		builder.append(rateId);
		builder.append(", pageId=");
		builder.append(pageId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", rate=");
		builder.append(rate);
		builder.append("]");
		return builder.toString();
	}

	
}
