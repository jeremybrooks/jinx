/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
*/
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

	@Test
	public void testSizes() throws Exception {
		SearchParameters search = new SearchParameters();
		search.setUserId("85853333@N00"); // search Jeremy's photos
		search.setTags("vinyl,blogged"); // this photos should have all sizes
		search.setPerPage(1);
		StringBuilder sb = new StringBuilder(JinxConstants.SIZE_LARGE);
		sb.append(",").append(JinxConstants.SIZE_LARGE_1600);
		sb.append(",").append(JinxConstants.SIZE_LARGE_2048);
		sb.append(",").append(JinxConstants.SIZE_LARGE_SQUARE);
		sb.append(",").append(JinxConstants.SIZE_MEDIUM);
		sb.append(",").append(JinxConstants.SIZE_MEDIUM_640);
		sb.append(",").append(JinxConstants.SIZE_MEDIUM_800);
		sb.append(",").append(JinxConstants.SIZE_ORIGINAL);
		sb.append(",").append(JinxConstants.SIZE_SMALL);
		sb.append(",").append(JinxConstants.SIZE_SMALL_320);
		sb.append(",").append(JinxConstants.SIZE_SMALL_SQUARE);
		sb.append(",").append(JinxConstants.SIZE_THUMBNAIL);

		search.setExtras(sb.toString());

		Photos photos = PhotosApi.getInstance().search(search);

		for (Photo p :photos.getPhotos()) {
			System.out.println(p.toString());
		}
	}
}
