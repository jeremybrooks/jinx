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

package net.jeremybrooks.jinx.response.photos;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PhotoInfo extends Response {
	private static final long serialVersionUID = 8900941240694188294L;

	public String getPhotoId() {
		return photo == null ? null : photo.id;
	}
	public String getSecret() {
		return photo == null ? null : photo.secret;
	}
	public String getServer() {
		return photo == null ? null : photo.server;
	}
	public Integer getFarm() {
		return photo == null ? null : photo.farm;
	}
	public String getDateUploaded() {
		return photo == null ? null : photo.dateuploaded;
	}
	public Boolean isFavorite() {return photo == null ? null : JinxUtils.flickrBooleanToBoolean(photo.isfavorite);}
	public JinxConstants.SafetyLevel getSafetyLevel() {return photo == null ? null : JinxUtils.flickrSafetyLevelIdToSafetyLevel(photo.safety_level);}
	public Integer getRotation() {return photo == null ? null : photo.rotation;}
	public String getOriginalSecret() {return photo == null ? null : photo.originalsecret;}
	public String getOriginalFormat() {return photo == null ? null : photo.originalformat;}

	public String getOwnerNsid() {return (photo == null || photo.owner == null) ? null : photo.owner.nsid;}
	public String getOwnerUsername() {return (photo == null || photo.owner == null) ? null : photo.owner.username;}
	public String getOwnerRealName() {return (photo == null || photo.owner == null) ? null : photo.owner.realname;}
	public String getOwnerLocation() {return (photo == null || photo.owner == null) ? null : photo.owner.location;}
	public String getOwnerIconServer() {return (photo == null || photo.owner == null) ? null : photo.owner.iconserver;}
	public Integer getOwnerIconFarm() {return (photo == null || photo.owner == null) ? null : photo.owner.iconfarm;}
	public String getOwnerPathAlias() {return (photo == null || photo.owner == null) ? null : photo.owner.path_alias;}

	public String getTitle() {return photo.title == null ? null : photo.title._content;}
	public String getDescription() {return photo.description == null ? null : photo.description._content;}

    public Boolean isPublic() {return (photo == null || photo.visibility == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.visibility.ispublic);}
    public Boolean isFriend() {return (photo == null || photo.visibility == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.visibility.isfriend);}
    public Boolean isFamily() {return (photo == null || photo.visibility == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.visibility.isfamily);}

	public String getDatePosted() {return (photo == null || photo.dates == null) ? null : photo.dates.posted;}
	public String getDateTaken() {return (photo == null || photo.dates == null) ? null : photo.dates.taken;}
	public Integer getDateTakenGranularity() {return (photo == null || photo.dates == null) ? null : photo.dates.takengranularity;}
	public String getDateLastUpdate() {return (photo == null || photo.dates == null) ? null : photo.dates.lastupdate;}

	public JinxConstants.Perms getPermComment() {return (photo == null || photo.permissions == null) ? null : JinxUtils.flickrPermsIdToPerms(photo.permissions.permcomment);}
	public JinxConstants.Perms getPermAddMeta() {return (photo == null || photo.permissions == null) ? null : JinxUtils.flickrPermsIdToPerms(photo.permissions.permaddmeta);}

	public Integer getViews() {return photo == null ? null : photo.views;}

	public Boolean isCanComment() {return (photo == null || photo.editability == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.editability.cancomment);}
	public Boolean isCanAddMeta() {return (photo == null || photo.editability == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.editability.canaddmeta);}
	public Boolean isPublicCanComment() {return (photo == null || photo.publiceditability == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.publiceditability.cancomment);}
	public Boolean isPublicCanAddMeta() {return (photo == null || photo.publiceditability == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.publiceditability.canaddmeta);}

	public Boolean isCanDownload() {return (photo == null || photo.usage == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.usage.candownload);}
	public Boolean isCanBlog() {return (photo == null || photo.usage == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.usage.canblog);}
	public Boolean isCanPrint() {return (photo == null || photo.usage == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.usage.canprint);}
	public Boolean isCanShare() {return (photo == null || photo.usage == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.usage.canshare);}

	public Integer getComments() {return (photo == null || photo.comments == null) ? 0 : photo.comments._content;}

	public List<Note> getNotes() {return (photo == null || photo.notes == null) ? null : photo.notes.note;}

	public Boolean isHasPeople() {return (photo == null || photo.people == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.people.haspeople);}

	public List<Tag> getTags() {return (photo == null || photo.tags == null) ? null : photo.tags.tag;}

	public Float getLatitude() {return (photo == null || photo.location == null) ? null : photo.location.latitude;}
	public Float getLongitude() {return (photo == null || photo.location == null) ? null : photo.location.longitude;}
	public Integer getAccuracy() {return (photo == null || photo.location == null) ? null : photo.location.accuracy;}
	public Integer getContext() {return (photo == null || photo.location == null) ? null : photo.location.context;}
	public String getPlaceId() {return (photo == null || photo.location == null) ? null : photo.location.place_id;}
	public String getWoeId() {return (photo == null || photo.location == null) ? null : photo.location.woeid;}
	public String getNeighbourhoodName() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood._content;}
	public String getNeighbourhoodPlaceId() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood.place_id;}
	public String getNeighbourhoodWoeId() {return (photo == null || photo.location == null || photo.location.neighbourhood == null) ? null : photo.location.neighbourhood.woeid;}

	public String getLocalityName() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality._content;}
	public String getLocalityPlaceId() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality.place_id;}
	public String getLocalityWoeId() {return (photo == null || photo.location == null || photo.location.locality == null) ? null : photo.location.locality.woeid;}

	public String getCountyName() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county._content;}
	public String getCountyPlaceId() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county.place_id;}
	public String getCountyWoeId() {return (photo == null || photo.location == null || photo.location.county == null) ? null : photo.location.county.woeid;}

	public String getRegionName() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region._content;}
	public String getRegionPlaceId() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region.place_id;}
	public String getRegionWoeId() {return (photo == null || photo.location == null || photo.location.region == null) ? null : photo.location.region.woeid;}

	public String getCountryName() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country._content;}
	public String getCountryPlaceId() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country.place_id;}
	public String getCountryWoeId() {return (photo == null || photo.location == null || photo.location.country == null) ? null : photo.location.country.woeid;}

    public Boolean isGeoIsPublic() {return (photo == null || photo.geoperms == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.geoperms.ispublic);}
    public Boolean isGeoIsFriend() {return (photo == null || photo.geoperms == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.geoperms.isfriend);}
    public Boolean isGeoIsFamily() {return (photo == null || photo.geoperms == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.geoperms.isfamily);}
    public Boolean isGeoIsContact() {return (photo == null || photo.geoperms == null) ? null : JinxUtils.flickrBooleanToBoolean(photo.geoperms.iscontact);}

	public List<Url> getUrls() {return (photo == null || photo.urls == null) ? null : photo.urls.url;}

	public String getMedia() {return photo == null ? null : photo.media;}

	/**
	 * See photos.licenses.getInfo() for more information about what the license identifier means;
	 * @return license identifier.
	 */
	public Integer getLicense() {return photo == null ? null : photo.license;}


	private _Photo photo;

	public class Note implements Serializable {
		private static final long serialVersionUID = -4258865056848395409L;
		@SerializedName("id")
		private String noteId;
		private String author;
		@SerializedName("authorname")
		private String authorName;
		private Integer x;
		private Integer y;
		private Integer w;
		private Integer h;
		@SerializedName("_content")
		private String note;

		public String getNoteId() {
			return noteId;
		}

		public String getAuthor() {
			return author;
		}

		public String getAuthorName() {
			return authorName;
		}

		public Integer getX() {
			return x;
		}

		public Integer getY() {
			return y;
		}

		public Integer getW() {
			return w;
		}

		public Integer getH() {
			return h;
		}

		public String getNote() {
			return note;
		}
	}

	public class Url implements Serializable {
		private static final long serialVersionUID = -6439137330902661368L;
		private String type;
		private String _content;

		public String getType() {
			return type;
		}

		public String getUrl() {
			return _content;
		}
	}

	private class _Photo implements Serializable {
		private static final long serialVersionUID = 2186359956589633140L;
		private String id;
		private String secret;
		private String server;
		private Integer farm;
		private String dateuploaded;
		private String isfavorite;  // return as Boolean
		private Integer license;
		private Integer safety_level;
		private Integer rotation;
		private String originalsecret;
		private String originalformat;
		private _Owner owner;
		private _Title title;
		private _Description description;
		private _Visibility visibility;
		private _Dates dates;
		private _Permissions permissions;
		private Integer views;
		private _Editability editability;
		private _PublicEditability publiceditability;
		private _Usage usage;
		private _Comments comments;
		private _Notes notes;
		private _People people;
		private _Tags tags;
		private _Location location;
		private _Geoperms geoperms;
		private _Urls urls;
		private String media;
	}

	private class _Owner implements Serializable {
		private static final long serialVersionUID = -8774092189194323037L;
		private String nsid;
		private String username;
		private String realname;
		private String location;
		private String iconserver;
		private Integer iconfarm;
		private String path_alias;
	}

	private class _Title implements Serializable {
		private static final long serialVersionUID = 1325517432682318228L;
		private String _content;
	}

	private class _Description implements Serializable {
		private static final long serialVersionUID = -5201598437177863925L;
		private String _content;
	}

	private class _Visibility implements Serializable {
		private static final long serialVersionUID = 1916660155513194928L;
		private String ispublic;    // return as Boolean
		private String isfriend;    // return as Boolean
		private String isfamily;    // return as Boolean
	}

	private class _Dates implements Serializable {
		private static final long serialVersionUID = -2596291733985274263L;
		private String posted;
		private String taken;
		private Integer takengranularity;
		private String lastupdate;
	}

	private class _Permissions implements Serializable {
		private static final long serialVersionUID = 3231299253148372329L;
		private Integer permcomment;
		private Integer permaddmeta;
	}

	private class _Editability implements Serializable {
		private static final long serialVersionUID = -2484601450795158560L;
		private String cancomment;  // return as Boolean
		private String canaddmeta;  // return as Boolean
	}

	private class _PublicEditability implements Serializable {
		private static final long serialVersionUID = -6529885893051609809L;
		private String cancomment;  // return as Boolean
		private String canaddmeta;  // return as Boolean
	}

	private class _Usage implements Serializable {
		private static final long serialVersionUID = 3931928919540862693L;
		private String candownload; // return as Boolean
		private String canblog;     // return as Boolean
		private String canprint;    // return as Boolean
		private String canshare;    // return as Boolean
	}

	private class _Comments implements Serializable {
		private static final long serialVersionUID = -6560403848595653620L;
		private Integer _content;
	}

	private class _Notes implements Serializable {
		private static final long serialVersionUID = -5443635950618176627L;
		private List<Note> note;
	}

	private class _People implements Serializable {
		private static final long serialVersionUID = 218573278396709989L;
		private String haspeople;   // return as Boolean
	}

	private class _Tags implements Serializable {
		private static final long serialVersionUID = 7406071869132753047L;
		private List<Tag> tag;
	}

	private class _Location implements Serializable {
		private Float latitude;
		private Float longitude;
		private Integer accuracy;
		private Integer context;
		private String place_id;
		private String woeid;
		private _Neighbourhood neighbourhood;
		private _Locality locality;
		private _County county;
		private _Region region;
		private _Country country;
	}

	private class _Neighbourhood implements Serializable {
		private static final long serialVersionUID = 6724897671356226631L;
		private String _content;
		private String place_id;
		private String woeid;
	}

	private class _Locality implements Serializable {
		private static final long serialVersionUID = -8435819406983465600L;
		private String _content;
		private String place_id;
		private String woeid;
	}

	private class _County implements Serializable {
		private static final long serialVersionUID = -1172097554785385626L;
		private String _content;
		private String place_id;
		private String woeid;
	}

	private class _Region implements Serializable {
		private static final long serialVersionUID = -4653221067985285374L;
		private String _content;
		private String place_id;
		private String woeid;
	}

	private class _Country implements Serializable {
		private static final long serialVersionUID = 806105457740153224L;
		private String _content;
		private String place_id;
		private String woeid;
	}

	private class _Geoperms implements Serializable {
		private static final long serialVersionUID = -6287218745851143224L;
		private String ispublic;    // return as Boolean
		private String iscontact;   // return as Boolean
		private String isfriend;    // return as Boolean
		private String isfamily;    // return as Boolean
	}

	private class _Urls implements Serializable {
		private static final long serialVersionUID = 5825167340550590244L;
		private List<Url> url;
	}
}
