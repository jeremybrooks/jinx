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

/**
 * A single Exif element representing a unit of Exif information.
 *
 * This calss does not contain any information that identifies the photo. The
 * photo identification should be provided by classes that include this class as
 * part of the information.
 * 
 * @author jeremyb
 */
public class ExifElement {

    /** Tagspace value from the returned exif element. */
    private String tagspace;

    /** Tagspaceid value from the returned exif element. */
    private String tagspaceid;

    /** Tag value from the returned exif element. */
    private String tag;

    /** Label value from the returned exif element. */
    private String label;

    /** Raw value from the returned exif element. */
    private String raw;

    /** Clean value from the returned exif element. */
    private String clean;



    /**
     * @return the tagspace
     */
    public String getTagspace() {
	return tagspace;
    }


    /**
     * @param tagspace the tagspace to set
     */
    public void setTagspace(String tagspace) {
	this.tagspace = tagspace;
    }


    /**
     * @return the tagspaceid
     */
    public String getTagspaceid() {
	return tagspaceid;
    }


    /**
     * @param tagspaceid the tagspaceid to set
     */
    public void setTagspaceid(String tagspaceid) {
	this.tagspaceid = tagspaceid;
    }


    /**
     * @return the tag
     */
    public String getTag() {
	return tag;
    }


    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
	this.tag = tag;
    }


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
     * @return the raw
     */
    public String getRaw() {
	return raw;
    }


    /**
     * @param raw the raw to set
     */
    public void setRaw(String raw) {
	this.raw = raw;
    }


    /**
     * @return the clean
     */
    public String getClean() {
	return clean;
    }


    /**
     * @param clean the clean to set
     */
    public void setClean(String clean) {
	this.clean = clean;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("ExifElement [ ");

	sb.append("tagspace=").append(this.tagspace).append(" | ");
	sb.append("tagspaceid=").append(this.tagspaceid).append(" | ");
	sb.append("tag=").append(this.tag).append(" | ");
	sb.append("label=").append(this.label).append(" | ");
	sb.append("raw=").append(this.raw).append(" | ");
	sb.append("clean=").append(this.clean);

	sb.append(" ]");
	return sb.toString();
    }
}
