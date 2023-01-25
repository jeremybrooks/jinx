/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.commons;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * @author Jeremy Brooks
 */
public class CommonsTest {
	@Test
	public void testParseGetInstitutions() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/commons/sample_get_institutions.json"));
		Institutions institutions = new Gson().fromJson(reader, Institutions.class);
		reader.close();
		assertNotNull(institutions);
		List<Institutions.Institution> iList = institutions.getInstitutionList();
		assertNotNull(iList);
		assertEquals(88, iList.size());
		Institutions.Institution i = iList.get(0);
		assertEquals("104959762@N04", i.getInstitutionId());
		assertEquals("1402064583", i.getDateLaunch());
		assertEquals("CDHS", i.getName());
		List<PhotoInfo.Url> urls = i.getUrlList();
		assertNotNull(urls);
		assertEquals(3, urls.size());
		PhotoInfo.Url url = urls.get(0);
		assertEquals("site", url.getType());
		assertEquals("www.cloynepioneermuseum.ca", url.getUrl());
	}
}
