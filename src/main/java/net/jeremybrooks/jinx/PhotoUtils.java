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

package net.jeremybrooks.jinx;

import net.jeremybrooks.jinx.response.photos.Photo;
import net.jeremybrooks.jinx.response.photos.PhotoInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * @author Jeremy Brooks
 */
public class PhotoUtils {

	/**
	 * Get the photo page URL for this photo.
	 * <br>
	 * Photo URL's are in the format
	 * https://www.flickr.com/photos/{user-id}/{photo-id}
	 * <br>
	 *
   * @param info identifies the photo to get the URL for.
	 * @return the url
	 */
	public static String getUrl(PhotoInfo info) {
    return JinxConstants.FLICKR_PHOTO_URL + info.getOwnerUserId() + '/' + info.getPhotoId();
	}

	/**
	 * Get an image for a photo at a specific size.
	 *
	 * @param size  Required. The the desired size.
	 * @param photo Required. The photo to get the image for.
	 * @return buffered image data from Flickr.
	 * @throws JinxException if any parameter is null, or if there are any errors.
	 */
	public static BufferedImage getImageForSize(JinxConstants.PhotoSize size, Photo photo) throws JinxException {
		if (photo == null || size == null) {
			throw new JinxException("Cannot look up null photo or size.");
		}
		BufferedImage image;
		try {
			image = ImageIO.read(getUrlForSize(size, photo));
		} catch (JinxException je) {
			throw je;
		} catch (Exception e) {
			throw new JinxException("Unable to get image for size " + size.toString(), e);
		}
		return image;
	}

	/**
	 * Get an image for photo info at a specific size.
	 *
	 * @param size Required. The the desired size.
	 * @param info Required. The photo info describing the photo.
	 * @return buffered image data from Flickr.
	 * @throws JinxException if any parameter is null, or if there are any errors.
	 */
	public static BufferedImage getImageForSize(JinxConstants.PhotoSize size, PhotoInfo info) throws JinxException {
		if (info == null || size == null) {
			throw new JinxException("Cannot look up null photo or size.");
		}
		BufferedImage image;
		try {
			image = ImageIO.read(getUrlForSize(size, info));
		} catch (JinxException je) {
			throw je;
		} catch (Exception e) {
			throw new JinxException("Unable to get image for size " + size.toString(), e);
		}
		return image;
	}

	/**
	 * @param size           Required. The the desired size.
	 * @param photoId        Required. The ID of the photo.
	 * @param secret         The secret of the photo. Required for all sizes except SIZE_ORIGINAL.
	 * @param farm           Required. The farm of the photo.
	 * @param server         Required. The server of the photo.
	 * @param originalFormat The original format. Only required if size is SIZE_ORIGINAL.
	 * @param originalSecret The original secret. Only required if size is SIZE_ORIGINAL.
	 * @return buffered image data from Flickr.
	 * @throws JinxException if any required parameter is null, or if there are any errors.
	 */
	public static BufferedImage getImageForSize(JinxConstants.PhotoSize size, String photoId, String secret, String farm, String server, String originalFormat, String originalSecret) throws JinxException {
		BufferedImage image;
		try {
			image = ImageIO.read(getUrlForSize(size, photoId, secret, farm, server, originalFormat, originalSecret));
		} catch (JinxException je) {
			throw je;
		} catch (Exception e) {
			throw new JinxException("Unable to get image for size " + size.toString(), e);
		}
		return image;
	}

	public static URL getUrlForSize(JinxConstants.PhotoSize size, Photo photo) throws JinxException {
		JinxUtils.validateParams(size, photo);
		return getUrlForSize(size, photo.getPhotoId(), photo.getSecret(), photo.getFarm(), photo.getServer(), photo.getOriginalFormat(), photo.getOriginalSecret());
	}

	public static URL getUrlForSize(JinxConstants.PhotoSize size, PhotoInfo info) throws JinxException {
		JinxUtils.validateParams(size, info);
		return getUrlForSize(size, info.getPhotoId(), info.getSecret(), info.getFarm(), info.getServer(), info.getOriginalFormat(), info.getOriginalSecret());
	}

	/**
	 * Get the URL for a specific size of photo.
	 *
	 * @param size           Required. The the desired size.
	 * @param photoId        Required. The ID of the photo.
	 * @param secret         The secret of the photo. Required for all sizes except SIZE_ORIGINAL.
	 * @param farm           Required. The farm of the photo.
	 * @param server         Required. The server of the photo.
	 * @param originalFormat The original format. Only required if size is SIZE_ORIGINAL.
	 * @param originalSecret The original secret. Only required if size is SIZE_ORIGINAL.
	 * @return URL for the photo at the specified size.
	 * @throws JinxException if required parameters are missing, or if the URL cannot be created.
	 */
	public static URL getUrlForSize(
			JinxConstants.PhotoSize size, String photoId, String secret, String farm, String server, String originalFormat, String originalSecret) throws JinxException {
		JinxUtils.validateParams(photoId, size, farm, server);
		if (size == JinxConstants.PhotoSize.SIZE_ORIGINAL) {
			JinxUtils.validateParams(originalFormat, originalSecret);
		} else {
			JinxUtils.validateParams(secret);
		}

		StringBuilder sb = new StringBuilder("https://farm");
		sb.append(farm);
		sb.append(".static.flickr.com/");
		sb.append(server).append("/");
		sb.append(photoId).append('_');

		switch (size) {
			case SIZE_SMALL_SQUARE:
				sb.append(secret).append("_s.jpg");
				break;

			case SIZE_LARGE_SQUARE:
				sb.append(secret).append("_q.jpg");
				break;

			case SIZE_THUMBNAIL:
				sb.append(secret).append("_t.jpg");
				break;

			case SIZE_SMALL:
				sb.append(secret).append("_m.jpg");
				break;

			case SIZE_SMALL_320:
				sb.append(secret).append("_n.jpg");
				break;

			case SIZE_MEDIUM:
				sb.append(secret).append(".jpg");
				break;

			case SIZE_MEDIUM_640:
				sb.append(secret).append("_z.jpg");
				break;

			case SIZE_MEDIUM_800:
				sb.append(secret).append("_c.jpg");
				break;

			case SIZE_LARGE:
				sb.append(secret).append("_b.jpg");
				break;

			case SIZE_LARGE_1600:
				sb.append(secret).append("_h.jpg");
				break;

			case SIZE_LARGE_2048:
				sb.append(secret).append("_k.jpg");
				break;

			case SIZE_ORIGINAL:
				sb.append(originalSecret).append("_o");
				sb.append('.').append(originalFormat);
				break;

			default:
				throw new JinxException("Undefined size: " + size);

		}
		try {
			return new URL(sb.toString());
		} catch (Exception e) {
			throw new JinxException("Could not create URL from string " + sb.toString());
		}
	}
}
