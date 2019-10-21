package com.stiltsoft.pagerating.service;

import java.util.ArrayList;
import com.atlassian.bandana.BandanaContext;
import com.atlassian.bandana.BandanaManager;
import com.atlassian.confluence.setup.bandana.ConfluenceBandanaContext;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.spring.container.ContainerManager;
import com.stiltsoft.pagerating.entity.Rate;
import com.stiltsoft.pagerating.rest.model.PageRateData;

public class RateService {
	private BandanaManager bandanaManager;
	private final BandanaContext bandanaContext;
	
	private static RateService INSTANCE;
	
	public static RateService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RateService();
		}
		return INSTANCE;
	}
	
	private RateService() {
		ContainerManager.autowireComponent(this);
		this.bandanaContext = new ConfluenceBandanaContext("rateservice");
	}
	
	public PageRateData addRate(Long pageId, String userId, Integer rate) {
		Rate rateObject = new Rate(null, pageId, userId, rate);
		bandanaManager.setValue(this.bandanaContext, rateObject.getRateId(), rateObject);
		return getPageRate(pageId);
	}
	
	public PageRateData addRate(Long pageId, Integer rate) {
		Rate rateObject = new Rate(null, pageId, AuthenticatedUserThreadLocal.get().getKey().getStringValue(), rate);
		bandanaManager.setValue(this.bandanaContext, rateObject.getRateId(), rateObject);
		return getPageRate(pageId);
	}
	
	public void removeRate(String rateId) {
		bandanaManager.removeValue(this.bandanaContext, rateId);
	}
	
	public PageRateData getPageRate(Long pageId) {
		Double counter = 0.0;
		int result = 0;
		Rate rate;
		
		for (String id : this.bandanaManager.getKeys(this.bandanaContext)) {			
			rate = (Rate) this.bandanaManager.getValue(this.bandanaContext, id);
			if(rate.getPageId().equals(pageId)) {
				counter ++;
				result+= rate.getRate();
			}
		}
		if(counter >0 ) {
			return new PageRateData(counter.intValue(), Math.round(result/counter*10.0)/10.0);
		}	
		return new PageRateData(counter.intValue(), 0.0);
	}
	
	public int getPageUserRate(Long pageId, String userId) {
		for (String id : this.bandanaManager.getKeys(this.bandanaContext)) {			
			Rate rate = (Rate) this.bandanaManager.getValue(this.bandanaContext, id);
			if(rate.getPageId().equals(pageId) && rate.getUserId().equals(userId)) {
				return rate.getRate();
			}
		}
		return 0;
	}
	
	public int getPageUserRate(Long pageId) {
		for (String id : this.bandanaManager.getKeys(this.bandanaContext)) {			
			Rate rate = (Rate) this.bandanaManager.getValue(this.bandanaContext, id);
			String userId = AuthenticatedUserThreadLocal.get().getKey().getStringValue();
			if(rate.getPageId().equals(pageId) && rate.getUserId().equals(userId)) {
				return rate.getRate();
			}
		}
		return 0;
	}
	
	public BandanaManager getBandanaManager() {
		return bandanaManager;
	}
	public void setBandanaManager(BandanaManager bandanaManager) {
		this.bandanaManager = bandanaManager;
	}

}
