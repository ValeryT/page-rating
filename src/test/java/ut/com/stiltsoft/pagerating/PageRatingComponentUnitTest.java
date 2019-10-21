package ut.com.stiltsoft.pagerating;

import org.junit.Test;
import com.stiltsoft.pagerating.api.PageRatingComponent;
import com.stiltsoft.pagerating.impl.PageRatingComponentImpl;

import static org.junit.Assert.assertEquals;

public class PageRatingComponentUnitTest
{
    @Test
    public void testMyName()
    {
        PageRatingComponent component = new PageRatingComponentImpl(null);
        assertEquals("names do not match!", "PageRating",component.getName());
    }
}