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

import java.io.Serializable;
import java.util.Date;

/**
 * @author jeremyb
 */
public class Comment implements Serializable {

    private String commentId;
    private String author;
    private String authorName;
    private Date dateCreate;
    private String permalink;
    private String comment;


    /**
     * @return the commentId
     */
    public String getCommentId() {
	return commentId;
    }


    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(String commentId) {
	this.commentId = commentId;
    }


    /**
     * @return the author
     */
    public String getAuthor() {
	return author;
    }


    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
	this.author = author;
    }


    /**
     * @return the authorName
     */
    public String getAuthorName() {
	return authorName;
    }


    /**
     * @param authorName the authorName to set
     */
    public void setAuthorName(String authorName) {
	this.authorName = authorName;
    }


    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
	return dateCreate;
    }


    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
	this.dateCreate = dateCreate;
    }


    /**
     * @return the permalink
     */
    public String getPermalink() {
	return permalink;
    }


    /**
     * @param permalink the permalink to set
     */
    public void setPermalink(String permalink) {
	this.permalink = permalink;
    }


    /**
     * @return the comment
     */
    public String getComment() {
	return comment;
    }


    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
	this.comment = comment;
    }

    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder(this.getClass().getName());

	sb.append(" [ ");
	sb.append("commentId=").append(this.commentId).append(" | ");
	sb.append("author=").append(this.author).append(" | ");
	sb.append("authorName=").append(this.authorName).append(" | ");
	sb.append("dateCreate=").append(this.dateCreate).append(" | ");
	sb.append("permalink=").append(this.permalink).append(" | ");
	sb.append("comment=").append(this.comment);

	sb.append(" ]");
	return sb.toString();
    }
}
