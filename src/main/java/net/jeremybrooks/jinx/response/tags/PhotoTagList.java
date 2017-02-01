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
