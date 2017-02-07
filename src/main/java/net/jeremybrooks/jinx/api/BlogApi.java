/*
 * Jinx is Copyright 2010-2017 by Jeremy Brooks and Contributors
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

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.response.blogs.BlogList;
import net.jeremybrooks.jinx.response.blogs.BlogServices;
import net.jeremybrooks.jinx.response.Response;

import java.util.Map;
import java.util.TreeMap;

/**
 * Provides access to the flickr.blogs API methods.
 *
 * @author Jeremy Brooks
 * @see <a href="https://www.flickr.com/services/api/">Flickr API documentation</a> for more details.
 */
public class BlogApi {


  private Jinx jinx;

  public BlogApi(Jinx jinx) {
    this.jinx = jinx;
  }


  /**
   * Return a list of Flickr supported blogging services
   * <br>
   * This method does not require authentication.
   *
   * @return object representing the supported blog services.
   * @throws JinxException if there are any errors.
   * @see <a href="http://www.flickr.com/services/api/flickr.blogs.getServices.html">flickr.blogs.getServices</a>
   */
  public BlogServices getServices() throws JinxException {
    Map<String, String> params = new TreeMap<String, String>();
    params.put("method", "flickr.blogs.getServices");

    return jinx.flickrGet(params, BlogServices.class, false);
  }


  /**
   * Get a list of configured blogs for the calling user.
   * <br>
   * This method requires authentication with 'read' permission.
   *
   * @param service (Optional) only return blogs for a given service id. You can get a list of from {@link net.jeremybrooks.jinx.api.BlogApi#getServices()}
   * @return list of blogs for the calling user.
   * @throws JinxException if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.blogs.getList.html">flickr.blogs.getList</a>
   */
  public BlogList getBlogList(String service) throws JinxException {
    Map<String, String> params = new TreeMap<String, String>();
    params.put("method", "flickr.blogs.getList");
    if (service != null) {
      params.put("service", service);
    }
    return jinx.flickrGet(params, BlogList.class);
  }


  /**
   * Post a photo to a blogging service.
   * <br>
   * Authentication
   * <br>
   * This method requires authentication with 'write' permission.
   * <br>
   * Note: This method requires an HTTP POST request.
   * <br>
   * <br>
   * This method has no specific response - It returns an empty success response if it completes without error.
   * <p>
   * The blogId and serviceId are marked optional, but you must provide one of them so that Flickr knows
   * where you want to post the photo.
   * </p>
   *
   * @param blogId       (Optional) the id of the blog to post to.
   * @param photoId      (Required) the id of the photo to blog
   * @param title        (Required) the blog post title
   * @param description  (Required) the blog post body
   * @param blogPassword (Optional) the password for the blog (used when the blog does not have a stored password).
   * @param serviceId    (Optional) a Flickr supported blogging service. Instead of passing a blog id you can pass a service id and we'll post to the first blog of that service we find.
   * @return response object indicating success or fail.
   * @throws JinxException if required parameters are missing or if there are any errors.
   * @see <a href="https://www.flickr.com/services/api/flickr.blogs.postPhoto.html">flickr.blogs.postPhoto</a>
   */
  public Response postPhoto(String blogId, String photoId, String title, String description, String blogPassword, String serviceId) throws JinxException {
    JinxUtils.validateParams(photoId, title, description);

    Map<String, String> params = new TreeMap<String, String>();
    params.put("method", "flickr.blogs.postPhoto");
    params.put("photo_id", photoId);
    params.put("title", title);
    params.put("description", description);
    if (blogId != null) {
      params.put("blog_id", blogId);
    }
    if (blogPassword != null) {
      params.put("blog_password", blogPassword);
    }
    if (serviceId != null) {
      params.put("service", serviceId);
    }

    return jinx.flickrPost(params, Response.class);
  }
}
