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

package net.jeremybrooks.jinx.response.people;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jeremyb on 7/14/14.
 */
public class PeopleTest {
    @Test
    public void testParseGetInfo() throws Exception {
        InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/people/sample_get_info.json"));
        Person person = new Gson().fromJson(reader, Person.class);
        reader.close();
        assertNotNull(person);
        assertEquals("ok", person.getStat());
        assertEquals(0, person.getCode());
        assertEquals("124857539@N03", person.getNsid());
        assertFalse(person.isPro());
        assertEquals("5538", person.getIconServer());
        assertEquals("6", person.getIconFarm());
        assertEquals("jinxlib", person.getPathAlias());
        assertEquals("X", person.getGender());
        assertFalse(person.isIgnored());
        assertFalse(person.isContact());
        assertFalse(person.isFriend());
        assertFalse(person.isFamily());
        assertTrue(person.isRevContact());
        assertFalse(person.isRevFamily());
        assertFalse(person.isRevFriend());
        assertEquals("jinxlib", person.getUsername());
        assertEquals("Jinx Library", person.getRealname());
        assertEquals("", person.getLocation());
        assertEquals("Pacific Time (US & Canada); Tijuana", person.getTimezoneLabel());
        assertEquals("-08:00", person.getTimezoneOffset());
        assertEquals("This is the test account for the Jinx library. \n\nJinx is a Java library for the Flickr API. It is written and maintained by <a href=\"https://www.flickr.com/photos/jeremybrooks/\">Jeremy Brooks</a>. Photos on this account are by Jeremy Brooks.\n\nThis library is used by <a href=\"http://jeremybrooks.net/suprsetr/\" rel=\"nofollow\">SuprSetr</a>, a program that makes managing your Flickr albums easy.", person.getDescription());
        assertEquals("https://www.flickr.com/photos/jinxlib/", person.getPhotosUrl());
        assertEquals("https://www.flickr.com/people/jinxlib/", person.getProfileUrl());
        assertEquals("https://m.flickr.com/photostream.gne?id=124834485", person.getMobileUrl());
        assertEquals("2008-07-08 16:48:54", person.getPhotosFirstDateTaken());
        assertEquals("1400909358", person.getPhotosFirstDate());
        assertEquals(new Integer(45), person.getPhotosCount());
    }
}
