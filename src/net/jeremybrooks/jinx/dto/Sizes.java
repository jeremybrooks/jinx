/*
 * Jinx is Copyright 2010 by Jeremy Brooks
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
package net.jeremybrooks.jinx.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about the available sizes of a given photo.
 *
 * @author jeremyb
 */
public class Sizes {

    
    /*
     <?xml version="1.0" encoding="utf-8" ?>
     <rsp stat="ok">
	<sizes canblog="0" canprint="0" candownload="1">
	    <size label="Square" width="75" height="75" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff_s.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/sq/" media="photo" />
	    <size label="Thumbnail" width="100" height="100" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff_t.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/t/" media="photo" />
	    <size label="Small" width="240" height="240" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff_m.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/s/" media="photo" />
	    <size label="Medium" width="500" height="500" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/m/" media="photo" />
	    <size label="Medium 640" width="640" height="640" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff_z.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/z/" media="photo" />
	    <size label="Large" width="1024" height="1024" source="http://farm5.static.flickr.com/4033/4682390041_dc3773e3ff_b.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/l/" media="photo" />
	    <size label="Original" width="3744" height="3744" source="http://farm5.static.flickr.com/4033/4682390041_f25e31107e_o.jpg" url="http://www.flickr.com/photos/jeremybrooks/4682390041/sizes/o/" media="photo" />
	</sizes>
     </rsp>
     */

    private String id;
    private boolean canBlog;
    private boolean canPrint;
    private boolean canDownload;
    
    private List<Size> sizeList;

    public Sizes() {
	this.sizeList = new ArrayList<Size>();
    }

    public void addSize(String label, int width, int height, String source, String url, String media) {
	Size s = new Size();
	s.setLabel(label);
	s.setWidth(width);
	s.setHeight(height);
	s.setSource(source);
	s.setUrl(url);
	s.setMedia(media);

	this.sizeList.add(s);
    }


    /**
     * @return the sizeList
     */
    public List<Size> getSizeList() {
	return sizeList;
    }


    /**
     * @param sizeList the sizeList to set
     */
    public void setSizeList(List<Size> sizeList) {
	this.sizeList = sizeList;
    }


    /**
     * @return the canBlog
     */
    public boolean isCanBlog() {
	return canBlog;
    }


    /**
     * @param canBlog the canBlog to set
     */
    public void setCanBlog(boolean canBlog) {
	this.canBlog = canBlog;
    }


    /**
     * @return the canPrint
     */
    public boolean isCanPrint() {
	return canPrint;
    }


    /**
     * @param canPrint the canPrint to set
     */
    public void setCanPrint(boolean canPrint) {
	this.canPrint = canPrint;
    }


    /**
     * @return the canDownload
     */
    public boolean isCanDownload() {
	return canDownload;
    }


    /**
     * @param canDownload the canDownload to set
     */
    public void setCanDownload(boolean canDownload) {
	this.canDownload = canDownload;
    }


    /**
     * @return the id
     */
    public String getId() {
	return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
	this.id = id;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Sizes [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("canBlog=").append(this.canBlog).append(" | ");
	sb.append("canPrint=").append(this.canPrint).append(" | ");
	sb.append("canDownload=").append(this.canDownload).append(" | ");
	sb.append("sizeList=");
	for (Size size : this.sizeList) {
	    sb.append('<').append(size.toString()).append('>');
	}

	sb.append(" ]");
	return sb.toString();
    }

    public class Size {
	private String label;
	private int width;
	private int height;
	private String source;
	private String url;
	private String media;


	/**
	 * @return the label
	 */
	public String getLabel() {
	    return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
	    this.label = label;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
	    return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
	    this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
	    return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
	    this.height = height;
	}


	/**
	 * @return the source
	 */
	public String getSource() {
	    return source;
	}


	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
	    this.source = source;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
	    return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
	    this.url = url;
	}


	/**
	 * @return the media
	 */
	public String getMedia() {
	    return media;
	}


	/**
	 * @param media the media to set
	 */
	public void setMedia(String media) {
	    this.media = media;
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder(" [ ");

	    sb.append("label=").append(this.label).append(" | ");
	    sb.append("width=").append(this.width).append(" | ");
	    sb.append("height=").append(this.height).append(" | ");
	    sb.append("source=").append(this.source).append(" | ");
	    sb.append("url=").append(this.url).append(" | ");
	    sb.append("media=").append(this.media);
	    
	    sb.append(" ]");
	    return sb.toString();
	}

    }
}
