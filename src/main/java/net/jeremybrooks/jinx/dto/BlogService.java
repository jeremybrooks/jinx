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
package net.jeremybrooks.jinx.dto;

import java.io.Serializable;

/**
 * This class represents a blogging service as returned by the call to
 * BlogsApi.getServices()
 *
 * @author jeremyb
 */
public class BlogService implements Serializable {
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
    private String id;
    private String description;


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


    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }


    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("BlogService [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("description=").append(this.description);

	sb.append(" ]");
	return sb.toString();
    }
}
