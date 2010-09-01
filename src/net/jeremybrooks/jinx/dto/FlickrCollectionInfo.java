package net.jeremybrooks.jinx.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jeremyb
 */
public class FlickrCollectionInfo implements Serializable {
    private String id;
    private int childCount;
    private Date dateCreate;
    private String iconLarge;
    private String iconSmall;
    private String server;
    private String secret;
    private String title;
    private String description;

    private List<Photo> iconPhotoList;


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
     * @return the childCount
     */
    public int getChildCount() {
	return childCount;
    }


    /**
     * @param childCount the childCount to set
     */
    public void setChildCount(int childCount) {
	this.childCount = childCount;
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
     * @return the iconLarge
     */
    public String getIconLarge() {
	return iconLarge;
    }


    /**
     * @param iconLarge the iconLarge to set
     */
    public void setIconLarge(String iconLarge) {
	this.iconLarge = iconLarge;
    }


    /**
     * @return the iconSmall
     */
    public String getIconSmall() {
	return iconSmall;
    }


    /**
     * @param iconSmall the iconSmall to set
     */
    public void setIconSmall(String iconSmall) {
	this.iconSmall = iconSmall;
    }


    /**
     * @return the server
     */
    public String getServer() {
	return server;
    }


    /**
     * @param server the server to set
     */
    public void setServer(String server) {
	this.server = server;
    }


    /**
     * @return the secret
     */
    public String getSecret() {
	return secret;
    }


    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
	this.secret = secret;
    }


    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }


    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
	this.title = title;
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


    /**
     * @return the iconPhotoList
     */
    public List<Photo> getIconPhotoList() {
	return iconPhotoList;
    }


    /**
     * @param iconPhotoList the iconPhotoList to set
     */
    public void setIconPhotoList(List<Photo> iconPhotoList) {
	this.iconPhotoList = iconPhotoList;
    }


    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("Blog [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("childCount=").append(this.childCount).append(" | ");
	sb.append("dateCreate=").append(this.dateCreate).append(" | ");
	sb.append("iconLarge=").append(this.iconLarge).append(" | ");
	sb.append("iconSmall=").append(this.iconSmall).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("title=").append(this.title).append(" | ");
	sb.append("description=").append(this.description).append(" | ");
	sb.append("iconPhotoList=");
	if (this.iconPhotoList == null) {
	    sb.append("null");
	} else {
	    for (Photo p : this.iconPhotoList) {
		sb.append("<");
		sb.append("id=").append(p.getId()).append(" | ");
		sb.append("ownerNsid=").append(p.getOwnerNsid()).append(" | ");
		sb.append("secret=").append(p.getSecret()).append(" | ");
		sb.append("server=").append(p.getServer()).append(" | ");
		sb.append("farm=").append(p.getFarm()).append(" | " );
		sb.append("title=").append(p.getTitle()).append( " | ");
		sb.append("isPublic=").append(p.isIsPublic()).append(" | ");
		sb.append("isFriend=").append(p.isIsFamily()).append(" | ");
		sb.append("isFamily=").append(p.isIsFamily()).append(">");
	    }
	}
	sb.append(" ]");
	return sb.toString();
    }
}
