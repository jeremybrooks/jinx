package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Tag implements Serializable {
	private static final long serialVersionUID = 4575059666034706071L;
	private String author;
	private String raw;
	@SerializedName("machine_tag")
	private Integer machineTag;
	@SerializedName("_content")
	private String tag;
	@SerializedName("authorname")
	private String authorName;
	@SerializedName("full_tag_id")
	private String fullTagId;
	@SerializedName("id")
	private String tagId;

	public String getAuthor() {
		return author;
	}

	public String getRaw() {
		return raw;
	}

	public boolean isMachineTag() {
		return machineTag != null && machineTag == 1;
	}

	public String getTag() {
		return tag;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getFullTagId() {
		return fullTagId;
	}

	public String getTagId() {
		return tagId;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.Tag");
		sb.append("{author='").append(author).append('\'');
		sb.append(" | raw='").append(raw).append('\'');
		sb.append(" | isMachineTag=").append(machineTag == 1);
		sb.append(" | tag='").append(tag).append('\'');
		sb.append(" | authorName='").append(authorName).append('\'');
		sb.append(" | fullTagId='").append(fullTagId).append('\'');
		sb.append(" | tagId='").append(tagId).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
