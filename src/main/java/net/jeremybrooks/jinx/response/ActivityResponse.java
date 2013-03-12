/*
 * Jinx is Copyright 2010-2013 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
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
package net.jeremybrooks.jinx.response;

import com.google.gson.annotations.SerializedName;

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

		private class Items {
			@SerializedName("perpage")
			private int perPage;
			private int page;
			private int pages;
			private int total;
			private List<Item> item;
		}

		public class Item {
			private String type;
			private long id;
			private String owner;
			@SerializedName("ownername")
			private String ownerName;
			@SerializedName("iconserver")
			private String iconServer;
			@SerializedName("iconfarm")
			private int iconFarm;
			private String secret;
			private String server;
			private int farm;
			private Title title;
			private String media;
			private int comments;
			private int notes;
			private int views;
			private int faves;
			private Activity activity;

			public String getType() {
				return type;
			}

			public long getId() {
				return id;
			}

			public String getOwner() {
				return owner;
			}

			public String getOwnerName() {
				return ownerName;
			}

			public String getIconServer() {
				return iconServer;
			}

			public int getIconFarm() {
				return iconFarm;
			}

			public String getSecret() {
				return secret;
			}

			public String getServer() {
				return server;
			}

			public int getFarm() {
				return farm;
			}

			public String getTitle() {
				return this.title == null ? null : this.title.title;
			}

			public String getMedia() {
				return media;
			}

			public int getComments() {
				return comments;
			}

			public int getNotes() {
				return notes;
			}

			public int getViews() {
				return views;
			}

			public int getFaves() {
				return faves;
			}

			public List<Event> getActivityEventList() {
				return this.activity == null ? null : this.activity.event;
			}

			/*
						private Activity activity;*/
			@Override
			public String toString() {
				final StringBuilder sb = new StringBuilder();
				sb.append('{');
				sb.append("type=\"").append(type).append('\"');
				sb.append(",id=").append(id);
				sb.append(",owner=\"").append(owner).append('\"');
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

		private class Title {
			@SerializedName("_content")
			private String title;
		}

		private class Activity {
			List<Event> event;
		}

		public class Event {
			private String type;
			private String user;
			private String username;
			@SerializedName("iconserver")
			private String iconServer;
			@SerializedName("iconfarm")
			private int iconFarm;
			@SerializedName("dateadded")
			private long dateAdded;
			@SerializedName("commentid")
			private long commentId;
			@SerializedName("_content")
			private String content;

			public String getType() {
				return type;
			}

			public String getUser() {
				return user;
			}

			public String getUsername() {
				return username;
			}

			public String getIconServer() {
				return iconServer;
			}

			public int getIconFarm() {
				return iconFarm;
			}

			public long getDateAdded() {
				return dateAdded;
			}

			public long getCommentId() {
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
				sb.append(",user=\"").append(user).append('\"');
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
