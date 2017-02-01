/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxUtils;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class Tag implements Serializable {
	private static final long serialVersionUID = 4575059666034706071L;
	private String author;
	private String raw;
	@SerializedName("machine_tag")
	private String machineTag;  // return as Boolean
	@SerializedName("_content")
	private String tag;
	@SerializedName("authorname")
	private String authorName;
	@SerializedName("full_tag_id")
	private String fullTagId;
	@SerializedName("id")
	private String tagId;
	private Integer count;
	private Integer score;


	public String getAuthor() {
		return author;
	}

	public String getRaw() {
		return raw;
	}

	public Boolean isMachineTag() {
		return JinxUtils.flickrBooleanToBoolean(machineTag);
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

	public Integer getCount() { return count; }

	public Integer getScore() { return score; }

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("net.jeremybrooks.jinx.response.photos.Tag");
		sb.append("{author='").append(author).append('\'');
		sb.append(" | raw='").append(raw).append('\'');
		sb.append(" | isMachineTag=").append(isMachineTag());
		sb.append(" | tag='").append(tag).append('\'');
		sb.append(" | authorName='").append(authorName).append('\'');
		sb.append(" | fullTagId='").append(fullTagId).append('\'');
		sb.append(" | tagId='").append(tagId).append('\'');
    sb.append(" | count=").append(count);
    sb.append(" | score=").append(score);
		sb.append('}');
		return sb.toString();
	}
}
