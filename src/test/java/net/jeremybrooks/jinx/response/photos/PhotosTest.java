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

	@Test
	public void testAllContexts() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/photos/sample_all_contexts.json"));
		AllContexts contexts = new Gson().fromJson(reader, AllContexts.class);
		reader.close();
		assertNotNull(contexts);
		assertEquals("ok", contexts.getStat());
		assertEquals(0, contexts.getCode());
		assertNotNull(contexts.getSetList());
		assertNotNull(contexts.getPoolList());
		AllContexts.Set set = contexts.getSetList().get(0);
		assertEquals("Iceland", set.getTitle());
		assertEquals("72157644903065293", set.getPhotosetId());
		assertEquals("14090247837", set.getPrimary());
		assertEquals("a00c1c8c01", set.getSecret());
		assertEquals("3745", set.getServer());
		assertEquals(4, (int)set.getFarm());
		assertEquals(2, (int)set.getViewCount());
		assertEquals(1, (int)set.getCommentCount());
		assertEquals(17, (int)set.getCountPhoto());
		assertEquals(0, (int)set.getCountVideo());

		AllContexts.Pool pool = contexts.getPoolList().get(0);
		assertEquals("Private Jinx Test Group", pool.getTitle());
		assertEquals("/groups/2638254@N20/pool/", pool.getUrl());
		assertEquals("2638254@N20", pool.getPoolId());
		assertEquals("2934", pool.getIconServer());
		assertEquals(0, (int)pool.getIconFarm());
		assertEquals(1, (int)pool.getMembers());
		assertEquals(1, (int)pool.getPoolCount());
	}
}
