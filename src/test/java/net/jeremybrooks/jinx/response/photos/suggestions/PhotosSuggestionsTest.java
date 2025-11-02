/*
 * Jinx is Copyright 2010-2025 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos.suggestions;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * Created by jeremyb on 7/22/14.
 */
public class PhotosSuggestionsTest {

    @Test
    public void testParseEmptyList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/suggestions/sample_empty_suggestions.json"));
        Suggestions suggestions = new Gson().fromJson(reader, Suggestions.class);
        reader.close();
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertEquals(Integer.valueOf(0), suggestions.getTotal());
        assertEquals(Integer.valueOf(250), suggestions.getPerPage());
        assertEquals(Integer.valueOf(1), suggestions.getPage());
        assertNotNull(suggestions.getSuggestionList());
        assertEquals(0, suggestions.getSuggestionList().size());
    }

    @Test
    public void testParseGetList() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/suggestions/sample_get_list.json"));
        Suggestions suggestions = new Gson().fromJson(reader, Suggestions.class);
        reader.close();
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertEquals(Integer.valueOf(2), suggestions.getTotal());
        assertEquals(Integer.valueOf(250), suggestions.getPerPage());
        assertEquals(Integer.valueOf(1), suggestions.getPage());
        assertNotNull(suggestions.getSuggestionList());
        assertEquals(2, suggestions.getSuggestionList().size());
        Suggestions.Suggestion s = suggestions.getSuggestionList().get(0);
        assertNotNull(s);
        assertEquals("124834485-72157645854858962", s.getSuggestionId());
        assertEquals("14100868708", s.getPhotoId());
        assertEquals("1406082069", s.getDateSuggested());
        assertEquals("124857539@N03", s.getSuggestedByUserId());
        assertEquals("jinxlib", s.getSuggestedByUsername());
        assertEquals("test", s.getNote());
        assertEquals("23512022", s.getWoeId());
        assertEquals(Float.valueOf(37.791125f), s.getLatitude());
        assertEquals(Float.valueOf(-122.399187f), s.getLongitude());
        assertEquals(Integer.valueOf(16), s.getAccuracy());
        assertEquals("Financial District", s.getNeighbourhoodName());
        assertEquals("GddgqTpTUb8LgT93hw", s.getNeighbourhoodPlaceId());
        assertEquals("23512022", s.getNeighbourhoodWoeId());
        assertEquals("San Francisco", s.getLocalityName());
        assertEquals("7.MJR8tTVrIO1EgB", s.getLocalityPlaceId());
        assertEquals("2487956", s.getLocalityWoeId());
        assertEquals("San Francisco", s.getCountyName());
        assertEquals(".7sOmlRQUL9nK.kMzA", s.getCountyPlaceId());
        assertEquals("12587707", s.getCountyWoeId());
        assertEquals("California", s.getRegionName());
        assertEquals("NsbUWfBTUb4mbyVu", s.getRegionPlaceId());
        assertEquals("2347563", s.getRegionWoeId());
        assertEquals("United States", s.getCountryName());
        assertEquals("nz.gsghTUb4c2WAecA", s.getCountryPlaceId());
        assertEquals("23424977", s.getCountryWoeId());
    }

    @Test
    public void testParseSuggestLocation() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/suggestions/sample_suggest_location.json"));
        Suggestions suggestions = new Gson().fromJson(reader, Suggestions.class);
        reader.close();
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertEquals("124834485-72157645863752425", suggestions.getSuggestionList().get(0).getSuggestionId());
    }
}
