/*
 * Jinx is Copyright 2010-2018 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.activity;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class ActivityResponse extends Response {
	private static final long serialVersionUID = -3215640525283101800L;

	private Items items;

	public int getPerPage() {
		return this.items == null ? 0 : this.items.perPage;
	}

	public int getPage() {
		return this.items == null ? 0 : this.items.page;
	}

	public int getPages() {
		return this.items == null ? 0 : this.items.pages;
	}

	public int getTotal() {
		return this.items == null ? 0 : this.items.total;
	}

	public List<Item> getItemList() {
		return this.items == null ? null : this.items.item;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",perPage=").append(this.getPerPage());
		sb.append(",page=").append(this.getPerPage());
		sb.append(",pages=").append(this.getPages());
		sb.append(",total=").append(this.getTotal());
		sb.append(",items=[");
		for (Item item : this.getItemList()) {
			sb.append(item.toString());
		}
		sb.append("],");
		sb.append(super.toString());
		return sb.toString();
	}

	private class Items implements Serializable {
		private static final long serialVersionUID = 4190734066952184327L;
		@SerializedName("perpage")
		private Integer perPage;
		private Integer page;
		private Integer pages;
		private Integer total;
		private List<Item> item;
	}

	public class Item implements Serializable {
		private static final long serialVersionUID = 4768439875856231541L;
		private String type;
		private String id;
        @SerializedName("owner")
		private String ownerId;
		@SerializedName("ownername")
		private String ownerName;
		@SerializedName("iconserver")
		private String iconServer;
		@SerializedName("iconfarm")
		private String iconFarm;
		private String secret;
		private String server;
		private String farm;
		private _Title title;
		private String media;
		private Integer comments;
		private Integer notes;
		private Integer views;
		private Integer faves;
		private _Activity activity;

		public String getType() {
			return type;
		}

		public String getId() {
			return id;
		}

		public String getOwnerId() {
			return ownerId;
		}

		public String getOwnerName() {
			return ownerName;
		}

		public String getIconServer() {
			return iconServer;
		}

		public String getIconFarm() {
			return iconFarm;
		}

		public String getSecret() {
			return secret;
		}

		public String getServer() {
			return server;
		}

		public String getFarm() {
			return farm;
		}

		public String getTitle() {
			return this.title == null ? null : this.title.title;
		}

		public String getMedia() {
			return media;
		}

		public Integer getComments() {
			return comments;
		}

		public Integer getNotes() {
			return notes;
		}

		public Integer getViews() {
			return views;
		}

		public Integer getFaves() {
			return faves;
		}

		public List<Event> getActivityEventList() {
			return this.activity == null ? null : this.activity.event;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append('{');
			sb.append("type=\"").append(type).append('\"');
			sb.append(",id=").append(id);
			sb.append(",ownerId=\"").append(ownerId).append('\"');
			sb.append(",ownerName=\"").append(ownerName).append('\"');
			sb.append(",iconServer=").append(iconServer);
			sb.append(",iconFarm=").append(iconFarm);
			sb.append(",secret=\"").append(secret).append('\"');
			sb.append(",server=").append(server);
			sb.append(",farm=").append(farm);
			sb.append(",title=").append(getTitle());
			sb.append(",media=\"").append(media).append('\"');
			sb.append(",comments=").append(comments);
			sb.append(",notes=").append(notes);
			sb.append(",views=").append(views);
			sb.append(",faves=").append(faves);
			sb.append(",activity=[");
			if (this.getActivityEventList() != null) {
				for (Event event : this.getActivityEventList()) {
					sb.append(event.toString());
				}
			}
			sb.append(']');
			sb.append('}');
			return sb.toString();
		}
	}

	private class _Title implements Serializable {
		private static final long serialVersionUID = -6877387037227982736L;
		@SerializedName("_content")
		private String title;
	}

	private class _Activity implements Serializable {
		private static final long serialVersionUID = 3206360896813303863L;
		List<Event> event;
	}

	public class Event implements Serializable {
		private static final long serialVersionUID = 3460353318629060L;
		private String type;
        @SerializedName("user")
		private String userId;
		private String username;
		@SerializedName("iconserver")
		private String iconServer;
		@SerializedName("iconfarm")
		private String iconFarm;
		@SerializedName("dateadded")
		private String dateAdded;
		@SerializedName("commentid")
		private String commentId;
		@SerializedName("_content")
		private String content;

		public String getType() {
			return type;
		}

		public String getUserId() {
			return userId;
		}

		public String getUsername() {
			return username;
		}

		public String getIconServer() {
			return iconServer;
		}

		public String getIconFarm() {
			return iconFarm;
		}

		public String getDateAdded() {
			return dateAdded;
		}

		public String getCommentId() {
			return commentId;
		}

		public String getContent() {
			return content;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append('{');
			sb.append("type=\"").append(type).append('\"');
			sb.append(",userId=\"").append(userId).append('\"');
			sb.append(",username=\"").append(username).append('\"');
			sb.append(",iconServer=").append(iconServer);
			sb.append(",iconFarm=").append(iconFarm);
			sb.append(",dateAdded=").append(dateAdded);
			sb.append(",commentId=").append(commentId);
			sb.append(",content=\"").append(content).append('\"');
			sb.append('}');
			return sb.toString();
		}
	}
}
