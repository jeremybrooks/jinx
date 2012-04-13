/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
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
package net.jeremybrooks.jinx.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;
import net.jeremybrooks.jinx.dto.Blog;
import net.jeremybrooks.jinx.dto.BlogService;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * This class provides access to the Flickr Blogs API.
 *
 * @see http://www.flickr.com/services/api/
 * @author jeremyb
 */
public class BlogsApi {

    private static BlogsApi instance = null;


    private BlogsApi() {
    }


    /**
     * Get a reference to the only instance of the BlogsApi class.
     *
     * @return reference to the instance of this class.
     */
    public static BlogsApi getInstance() {
	if (BlogsApi.instance == null) {
	    BlogsApi.instance = new BlogsApi();
	}

	return BlogsApi.instance;
    }



    /**
     * Get a list of configured blogs for the calling user.
     *
     * This method requires authentication with 'read' permission.
     *
     * @param serviceId (Optional) Optionally only return blogs for a given
     *	      service id. You can get a list of from BlogsApi.getServices().
     * @return a list of configured blogs for the calling user.
     * @throws JinxException if there are any errors.
     */
    public List<Blog> getList(String serviceId) throws JinxException {
	List<Blog> list = new ArrayList<Blog>();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.blogs.getList");
	params.put("api_key", Jinx.getInstance().getApiKey());

	if (!JinxUtils.isEmpty(serviceId)) {
	    params.put("service", serviceId.trim());
	}

	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <blogs>
		<blog id="72157594526571385"
		    name="Jeremy Brooks"
		    service="MetaWeblogAPI"
		    needspassword="1"
		    url="http://www.whirljack.net/jeremybrooks/" />
	    </blogs>
	</rsp>
	*/
	Document doc = Jinx.getInstance().callFlickr(params, true);

	NodeList nodes = doc.getElementsByTagName("blog");
	if (nodes != null) {
	    for (int i = 0; i < nodes.getLength(); i++) {
		Blog b = new Blog();
		Node blogNode = nodes.item(i);
		NamedNodeMap attrs = blogNode.getAttributes();

		b.setId(JinxUtils.getAttribute(attrs, "id"));
		b.setName(JinxUtils.getAttribute(attrs, "name"));
		b.setService(JinxUtils.getAttribute(attrs, "service"));
		b.setNeedsPassword(JinxUtils.getAttributeAsBoolean(attrs, "needspassword"));
		b.setUrl(JinxUtils.getAttribute(attrs, "url"));

		list.add(b);
	    }
	}

	return list;
    }


    /**
     * Return a list of Flickr supported blogging services
     *
     * This method does not require authentication.
     *
     * @return a list of BlogService objects.
     * @throws JinxException if there are any errors.
     */
    public List<BlogService> getServices() throws JinxException {
	List<BlogService> list = new ArrayList<BlogService>();

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.blogs.getServices");
	params.put("api_key", Jinx.getInstance().getApiKey());


	/*
	<?xml version="1.0" encoding="utf-8" ?>
	<rsp stat="ok">
	    <services>
		<service id="beta.blogger.com">Blogger</service>
		<service id="Typepad">Typepad</service>
		<service id="MovableType">Movable Type</service>
		<service id="LiveJournal">LiveJournal</service>
		<service id="MetaWeblogAPI">Wordpress</service>
		<service id="MetaWeblogAPI">MetaWeblogAPI</service>
		<service id="Manila">Manila</service>
		<service id="AtomAPI">AtomAPI</service>
		<service id="BloggerAPI">BloggerAPI</service>
		<service id="Vox">Vox</service>
		<service id="Twitter">Twitter</service>
	    </services>
	</rsp>
	 */
	Document doc = Jinx.getInstance().callFlickr(params, false);

	NodeList nodes = doc.getElementsByTagName("service");
	if (nodes != null) {
	    for (int i=0; i < nodes.getLength(); i++) {
		BlogService bs = new BlogService();

		Node node = nodes.item(i);
		NamedNodeMap attrs = node.getAttributes();
		bs.setId(JinxUtils.getAttribute(attrs, "id"));
		bs.setDescription(JinxUtils.getFirstChildTextContent(node));

		list.add(bs);
	    }
	}
	return list;
    }


    /**
     * Post a photo to a blog.
     *
     * This method requires authentication with 'write' permission.
     *
     * Note: This method requires an HTTP POST request.

     * @param blogId (Optional) The id of the blog to post to.
     * @param photoId (Required) The id of the photo to blog.
     * @param title The blog post title.
     * @param description The blog post body.
     * @param blogPassword (Optional) The password for the blog (used when the
     *	      blog does not have a stored password).
     * @param serviceId (Optional) A Flickr supported blogging service. Instead
     *	      of passing a blog id you can pass a service id and we'll post to
     *	      the first blog of that service we find.
     * @throws JinxException if there are any errors.
     */
    public void postPhoto(String blogId, String photoId, String title,
	    String description, String blogPassword, String serviceId) throws JinxException {
	
	if (JinxUtils.isEmpty(photoId)) {
	    throw new JinxException("Parameter photoId is required.");
	}

	Map<String, String> params = new TreeMap<String, String>();
	params.put("method", "flickr.blogs.postPhoto");
	params.put("api_key", Jinx.getInstance().getApiKey());

	params.put("photo_id", photoId);

	if (JinxUtils.isEmpty(title)) {
	    title = "";
	}
	params.put("title", title);

	if (JinxUtils.isEmpty(description)) {
	    description = "";
	}
	params.put("description", description);

	if (! JinxUtils.isEmpty(blogId)) {
	    params.put("blog_id", blogId);
	}
	if (! JinxUtils.isEmpty(blogPassword)) {
	    params.put("blog_password", blogPassword);
	}
	if (! JinxUtils.isEmpty(serviceId)) {
	    params.put("service", serviceId);
	}

	Jinx.getInstance().callFlickr(params, true, true);
    }

}
