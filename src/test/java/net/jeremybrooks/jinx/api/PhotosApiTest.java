package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxTestUtil;
import net.jeremybrooks.jinx.dto.Photo;
import net.jeremybrooks.jinx.dto.Photos;
import net.jeremybrooks.jinx.dto.SearchParameters;
import net.jeremybrooks.jinx.logger.JinxLogger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhotosApiTest {

	@Before
	public void setup() throws Exception
	{
		JinxTestUtil.login();
	}
	
	@Test
	public void searchPhotos() throws Exception
	{
	    // Create a search request
	    SearchParameters search = new SearchParameters();

	    // Search our photos. Excluding this will search everybody's photos.
	    //search.setUserId(token.getNsid());

	    // Search by tag, requiring all tags to be found
	    search.setTagMode(JinxConstants.TAG_MODE_ALL);
	    search.setTags("sanfrancisco, fog");

	    // Return 10 items per page
	    search.setPerPage(10);
	    
	    // perform search
	    Photos photos = PhotosApi.getInstance().search(search);
	    JinxLogger.getLogger().log("Found " + photos.getTotal() + " results.");
	    for (Photo p : photos.getPhotos()) {
	    	JinxLogger.getLogger().log(p.getTitle() + " <" + p.getId() + ">");
	    }
	    Assert.assertNotNull(photos);
	}
}
