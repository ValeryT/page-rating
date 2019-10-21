package com.stiltsoft.pagerating.macro;


import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.user.ConfluenceUser;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.spring.container.ContainerManager;
import com.stiltsoft.pagerating.service.RateService;

import java.util.Map;

import org.apache.velocity.VelocityContext;

public class PageRating implements Macro {
	@ComponentImport
	private final PageManager pageManager = (PageManager) ContainerManager.getComponent("pageManager");
//	@ComponentImport
	private final RateService rateService = RateService.getInstance();

    public String execute(Map<String, String> map, String s, ConversionContext conversionContext) throws MacroExecutionException {
    	ConfluenceUser loggedInUser = AuthenticatedUserThreadLocal.get();
    	Page p = pageManager.getPage(conversionContext.getSpaceKey(), conversionContext.getPageContext().getPageTitle());
       // VelocityContext contextMap = new VelocityContext(MacroUtils.defaultVelocityContext());
    	Map<String, Object> contextMap = MacroUtils.defaultVelocityContext();
        contextMap.put("pageId",p.getId());
        if(map.get("showResult")!=null && map.get("showResult").equals("true")) {
        	contextMap.put("pageRating",rateService.getPageRate(p.getId()).getRate());
        	contextMap.put("pageRatingCount",rateService.getPageRate(p.getId()).getCount());
        }	
        else {
        	contextMap.put("pageRating",-1);
        	contextMap.put("pageRatingCount",-1);
        }
        
        contextMap.put("pageUserRating",rateService.getPageUserRate(p.getId(),loggedInUser.getKey().getStringValue()));
        
        return VelocityUtils.getRenderedTemplate("/templates/pagerating.vm", contextMap);
        
    }

    public BodyType getBodyType() { return BodyType.NONE; }

    public OutputType getOutputType() { return OutputType.BLOCK; }
}

