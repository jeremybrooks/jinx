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

package net.jeremybrooks.jinx;

/**
 * @author jeremyb
 */
public class PhotoUtils {
//
//    /**
//     * Get the URL for a specific photo size.
//     *
//     * @param size  the desired size.
//     * @param photo
//     * @return URL for the photo at the requested size.
//     * @throws JinxException if there are any errors, or if some of the
//     *                       fields required to build the URL do not exist.
//     */
//    public static String getUrlForSize(int size, Photo photo) throws JinxException {
//        if (JinxUtils.isEmpty(photo.getSecret())) {
//            throw new JinxException("URL for size " + size + " not available: no secret available.");
//        }
//        if (JinxUtils.isEmpty(photo.getFarm())) {
//            throw new JinxException("URL for size " + size + " not available: no farm available.");
//        }
//        if (JinxUtils.isEmpty(photo.getServer())) {
//            throw new JinxException("URL for size " + size + " not available: no server available.");
//        }
//        if (size == JinxConstants.SIZE_ORIGINAL) {
//            if (JinxUtils.isEmpty(photo.getOriginalFormat()) || JinxUtils.isEmpty(photo.getOriginalSecret())) {
//                throw new JinxException("URL for original size not available.");
//            }
//        }
//
//        StringBuilder sb = new StringBuilder("http://farm");
//        sb.append(photo.getFarm());
//        sb.append(".static.flickr.com/");
//        sb.append(photo.getServer()).append("/");
//        sb.append(photo.getId()).append('_');
//
//        switch (size) {
//            case JinxConstants.SIZE_SMALL_SQUARE:
//                sb.append(photo.getSecret()).append("_s.jpg");
//                break;
//
//            case JinxConstants.SIZE_LARGE_SQUARE:
//                sb.append(photo.getSecret()).append("_q.jpg");
//                break;
//
//            case JinxConstants.SIZE_THUMBNAIL:
//                sb.append(photo.getSecret()).append("_t.jpg");
//                break;
//
//            case JinxConstants.SIZE_SMALL:
//                sb.append(photo.getSecret()).append("_m.jpg");
//                break;
//
//            case JinxConstants.SIZE_SMALL_320:
//                sb.append(photo.getSecret()).append("_n.jpg");
//                break;
//
//            case JinxConstants.SIZE_MEDIUM:
//                sb.append(photo.getSecret()).append(".jpg");
//                break;
//
//            case JinxConstants.SIZE_MEDIUM_640:
//                sb.append(photo.getSecret()).append("_z.jpg");
//                break;
//
//            case JinxConstants.SIZE_MEDIUM_800:
//                sb.append(photo.getSecret()).append("_c.jpg");
//                break;
//
//            case JinxConstants.SIZE_LARGE:
//                sb.append(photo.getSecret()).append("_b.jpg");
//                break;
//
//			case JinxConstants.SIZE_LARGE_1600:
//				sb.append(photo.getSecret()).append("_h.jpg");
//				break;
//
//			case JinxConstants.SIZE_LARGE_2048:
//				sb.append(photo.getSecret()).append("_k.jpg");
//				break;
//
//            case JinxConstants.SIZE_ORIGINAL:
//                sb.append(photo.getOriginalSecret()).append("_o");
//                sb.append('.').append(photo.getOriginalFormat());
//                break;
//
//            default:
//                throw new JinxException("Undefined size: " + size);
//
//        }
//
//        return sb.toString();
//    }
}
