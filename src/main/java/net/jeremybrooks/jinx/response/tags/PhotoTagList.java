/*
 * Jinx is Copyright 2010-2023 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.tags;

import com.google.gson.annotations.SerializedName;
import net.jeremybrooks.jinx.response.Response;
import net.jeremybrooks.jinx.response.photos.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jeremy Brooks
 */
public class PhotoTagList extends Response {
  private static final long serialVersionUID = -1773616627504413325L;
  private _Photo photo;

  public String getPhotoId() { return photo == null ? null : photo.photoId; }
  public List<Tag> getTagList() { return photo == null ? null : photo.getTags(); }


  private class _Photo implements Serializable {
    private static final long serialVersionUID = 6296453585943214930L;
    @SerializedName("id")
    private String photoId;
    private _Tags tags;
    private List<Tag> getTags() { return tags == null ? null : tags.tagList; }

  }

  private class _Tags implements Serializable {
    private static final long serialVersionUID = -679158645686975590L;
    @SerializedName("tag")
    private List<Tag> tagList;
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.tags.PhotoTagList{" +
        "photoId='" + getPhotoId() + '\'' +
        ", tags=" + getTagList() +
        '}';
  }
}
