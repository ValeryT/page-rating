package com.stiltsoft.pagerating.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.stiltsoft.pagerating.api.PageRatingComponent;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService ({PageRatingComponent.class})
@Named ("pageRatingComponent")
public class PageRatingComponentImpl implements PageRatingComponent
{
    @ComponentImport
    private final ApplicationProperties applicationProperties;

    @Inject
    public PageRatingComponentImpl(final ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "PageRating:" + applicationProperties.getDisplayName();
        }
        
        return "PageRating";
    }
}