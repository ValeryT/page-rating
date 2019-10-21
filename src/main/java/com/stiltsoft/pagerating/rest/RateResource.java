package com.stiltsoft.pagerating.rest;

import javax.ws.rs.*;

import javax.ws.rs.core.Response;

import com.atlassian.json.jsonorg.JSONObject;
import com.stiltsoft.pagerating.rest.model.PageRateData;
import com.stiltsoft.pagerating.service.RateService;

@Path("/")
public class RateResource {	
	@GET
	@Path("/add-rate/{pageId}/{userId}/{rate}")
	public Response addRate(@PathParam ("pageId") Long pageId,
			@PathParam ("userId") String userId,
			@PathParam ("rate") Integer rate) {
		return Response.ok(getJson(RateService.getInstance().addRate(pageId, userId, rate)).toString()).build();
	}
	
	@GET
	@Path("/add-rate/{pageId}/{rate}")
	public Response addRate(@PathParam ("pageId") Long pageId,
			@PathParam ("rate") Integer rate) {
		return Response.ok(getJson(RateService.getInstance().addRate(pageId,  rate)).toString()).build();
	}
	
	@GET
	@Path("/get-rate/{pageId}/{userId}")
	public Response getRatePageUser(@PathParam ("pageId") Long pageId,
			@PathParam ("userId") String userId) {			
		int rateV = RateService.getInstance().getPageUserRate(pageId, userId);		
		return Response.ok(new JSONObject().put("value", String.valueOf(rateV)).toString()).build();
	}
	
	@GET
	@Path("/get-rate/{pageId}")
	public Response getRatePage(@PathParam ("pageId") Long pageId) {
		return Response.ok(new JSONObject().put("value", RateService.getInstance().getPageRate(pageId)).toString()).build();
	}
	
	private JSONObject getJson(PageRateData prD) {
		JSONObject jsO = new JSONObject();
		jsO.put("value", prD.getRate());
		jsO.put("count", prD.getCount());
		return jsO;
	}

}
