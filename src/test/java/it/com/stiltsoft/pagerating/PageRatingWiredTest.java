package it.com.stiltsoft.pagerating;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.stiltsoft.pagerating.api.PageRatingComponent;
import com.atlassian.sal.api.ApplicationProperties;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class PageRatingWiredTest
{
    private final ApplicationProperties applicationProperties;
    private final PageRatingComponent myPluginComponent;

    public PageRatingWiredTest(ApplicationProperties applicationProperties,PageRatingComponent myPluginComponent)
    {
        this.applicationProperties = applicationProperties;
        this.myPluginComponent = myPluginComponent;
    }

    @Test
    public void testMyName()
    {
        assertEquals("names do not match!", "PageRating:" + applicationProperties.getDisplayName(),myPluginComponent.getName());
    }
}