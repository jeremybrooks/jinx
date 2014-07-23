/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
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

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.suggestions.Suggestions;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jeremyb on 7/22/14.
 */
public class PhotosSuggestionsApiTest {
    private static PhotosSuggestionsApi photosSuggestionsApi;

    private static final String PHOTO_ID = "14089987020";

    @BeforeClass
    public static void beforeClass() throws Exception {
        Properties p = new Properties();
        p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));
        String filename = p.getProperty("path.to.oauth.token");
        assertNotNull(filename);
        File file = new File(filename);
        assertTrue(file.exists());
        OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
        oAuthAccessToken.load(new FileInputStream(file));
        assertNotNull(oAuthAccessToken);
        Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
//        jinx.setVerboseLogging(true);
//        JinxLogger.setLogger(new StdoutLogger());
        photosSuggestionsApi = new PhotosSuggestionsApi(jinx);
    }

    @Test
    public void testSuggestApprove() throws Exception {
        // suggest
        Suggestions suggestions = photosSuggestionsApi.suggestLocation(PHOTO_ID, 37.791125f, -122.399187f, 16, null, null, "Jinx automated test");
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertEquals(1, suggestions.getSuggestionList().size());
        String suggestionId = suggestions.getSuggestionList().get(0).getSuggestionId();
        assertNotNull(suggestionId);

        // get list
        suggestions = photosSuggestionsApi.getList(PHOTO_ID, null);
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertNotNull(suggestions.getSuggestionList());

        // approve
        Response response = photosSuggestionsApi.approveSuggestion(suggestionId);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }

    @Test
    public void testSuggestRemove() throws Exception {
        // suggest
        Suggestions suggestions = photosSuggestionsApi.suggestLocation(PHOTO_ID, 37.791125f, -122.399187f, 16, null, null, "Jinx automated test");
        assertNotNull(suggestions);
        assertEquals("ok", suggestions.getStat());
        assertEquals(0, suggestions.getCode());
        assertEquals(1, suggestions.getSuggestionList().size());
        String suggestionId = suggestions.getSuggestionList().get(0).getSuggestionId();
        assertNotNull(suggestionId);

        // remove
        Response response = photosSuggestionsApi.removeSuggestion(suggestionId);
        assertNotNull(response);
        assertEquals("ok", response.getStat());
        assertEquals(0, response.getCode());
    }


    // TODO: This seems to be broken; the suggestion is rejected but Flickr returns 999. Uncomment when fixed.
    @Test
    public void testSuggestReject() throws Exception {
//        // suggest
//        Suggestions suggestions = photosSuggestionsApi.suggestLocation(PHOTO_ID, 37.791125f, -122.399187f, 16, null, null, "Jinx automated test");
//        assertNotNull(suggestions);
//        assertEquals("ok", suggestions.getStat());
//        assertEquals(0, suggestions.getCode());
//        assertEquals(1, suggestions.getSuggestionList().size());
//        String suggestionId = suggestions.getSuggestionList().get(0).getSuggestionId();
//        assertNotNull(suggestionId);
//
//        // reject
//        Response response = photosSuggestionsApi.rejectSuggestion(suggestionId);
//        assertNotNull(response);
//        assertEquals("ok", response.getStat());
//        assertEquals(0, response.getCode());
    }
}
