package net.jeremybrooks.jinx.response.photos;

import com.google.gson.Gson;
import net.jeremybrooks.jinx.response.activity.ActivityResponseTest;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jeremy Brooks
 */
public class PhotosTest {
	@Test
	public void testAddTags() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_add_tags.json"));
		AddTags tags = new Gson().fromJson(reader, AddTags.class);
		reader.close();
		assertNotNull(tags);
		assertNotNull(tags.getTagList());
		assertEquals(3, tags.getTagList().size());
		Tag tag = tags.getTagList().get(0);
		assertEquals("124857539@N03", tag.getAuthor());
		assertEquals("location:country=Iceland", tag.getRaw());
		assertFalse(tag.isMachineTag());
		assertEquals("locationcountryiceland", tag.getTag());
		assertEquals("jinxlib", tag.getAuthorName());
		assertEquals("124834485-14276354684-57115208", tag.getFullTagId());
		assertEquals("57115208", tag.getTagId());
	}

}
