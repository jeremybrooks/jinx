package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class AddTags extends Response {

	private Tags tags;

	public List<Tag> getTagList() {
		return tags==null ? null : tags.tagList;
	}

	private class Tags {
		@SerializedName("tag")
		List<Tag> tagList;
	}
}
