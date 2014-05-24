package net.jeremybrooks.jinx.response.photosets;

import net.jeremybrooks.jinx.response.Response;

/**
 *
 * @author Jeremy Brooks
 */
public class PhotosetInfo extends Response {
	private static final long serialVersionUID = -6706772513615251921L;
	private Photoset photoset;
	public Photoset getPhotoset() {return photoset;}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("classname=").append(this.getClass().getName());
		sb.append(",photoset=").append(photoset);
		return sb.toString();
	}
}
