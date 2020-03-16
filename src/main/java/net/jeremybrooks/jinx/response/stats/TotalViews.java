/*
 * Jinx is Copyright 2010-2020 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.response.stats;

import net.jeremybrooks.jinx.response.Response;

import java.io.Serializable;

/**
 * @author Jeremy Brooks
 */
public class TotalViews extends Response {
  private static final long serialVersionUID = -4120301191244491958L;
  private _ViewStats stats;

  public Integer getTotalViews() {
    return stats == null ? null : stats.getTotal();
  }

  public Integer getPhotosViews() {
    return stats == null ? null : stats.getPhotos();
  }

  public Integer getPhotostreamViews() {
    return stats == null ? null : stats.getPhotostream();
  }

  public Integer getSetsViews() {
    return stats == null ? null : stats.getSets();
  }

  public Integer getCollectionsViews() {
    return stats == null ? null : stats.getCollections();
  }

  public Integer getGalleriesViews() {
    return stats == null ? null : stats.getGalleries();
  }

  private class _ViewStats implements Serializable {
    private static final long serialVersionUID = -6696797604739902788L;
    private _Total total;
    private _Photos photos;
    private _Photostream photostream;
    private _Sets sets;
    private _Collections collections;
    private _Galleries galleries;

    private Integer getTotal() {
      return total == null ? null : total.views;
    }

    private Integer getPhotos() {
      return photos == null ? null : photos.views;
    }

    private Integer getPhotostream() {
      return photostream == null ? null : photostream.views;
    }

    private Integer getSets() {
      return sets == null ? null : sets.views;
    }

    private Integer getCollections() {
      return collections == null ? null : collections.views;
    }

    private Integer getGalleries() {
      return galleries == null ? null : galleries.views;
    }

    private class _Total implements Serializable {
      private static final long serialVersionUID = 8555756671759128349L;
      private Integer views;
    }

    private class _Photos implements Serializable {
      private static final long serialVersionUID = -603402044695756457L;
      private Integer views;
    }

    private class _Photostream implements Serializable {
      private static final long serialVersionUID = -342249384738949579L;
      private Integer views;
    }

    private class _Sets implements Serializable {
      private static final long serialVersionUID = 4154212100884724224L;
      private Integer views;
    }

    private class _Collections implements Serializable {
      private static final long serialVersionUID = 4052697373274624731L;
      private Integer views;
    }

    private class _Galleries implements Serializable {
      private static final long serialVersionUID = 2494477645513260020L;
      private Integer views;
    }
  }

  @Override
  public String toString() {
    return "net.jeremybrooks.jinx.response.stats.TotalViews{" +
        "totalViews=" + getTotalViews() +
        ", photosViews=" + getPhotosViews() +
        ", photostreamViews=" + getPhotostreamViews() +
        ", setsViews=" + getSetsViews() +
        ", collectionsViews=" + getCollectionsViews() +
        ", galleriesViews=" + getGalleriesViews() +
        '}';
  }
}
