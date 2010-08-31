package net.jeremybrooks.jinx.dto;

import java.awt.Image;
import java.net.URL;
import java.util.Date;
import javax.imageio.ImageIO;
import net.jeremybrooks.jinx.JinxConstants;
import net.jeremybrooks.jinx.JinxException;
import net.jeremybrooks.jinx.JinxUtils;

/**
 *
 * @author jeremyb
 */
public class PhotoBase {
    private String id;
    private String secret;
    private String server;
    private String farm;
    private String license;
    private String originalSecret;
    private String originalFormat;
    private int views;
    private String media;
    private String title;
    private String description;
    private boolean isPublic;
    private boolean isFriend;
    private boolean isFamily;
    private Date dateTaken;
    private String dateTakenGranularity;
    private Date dateLastUpdate;
    private Date dateUploaded;
    private String ownerNsid;
    private String ownerUsername;


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
     * @return the farm
     */
    public String getFarm() {
	return farm;
    }


    /**
     * @param farm the farm to set
     */
    public void setFarm(String farm) {
	this.farm = farm;
    }


    /**
     * @return the license
     */
    public String getLicense() {
	return license;
    }


    /**
     * @param license the license to set
     */
    public void setLicense(String license) {
	this.license = license;
    }


    /**
     * @return the originalSecret
     */
    public String getOriginalSecret() {
	return originalSecret;
    }


    /**
     * @param originalSecret the originalSecret to set
     */
    public void setOriginalSecret(String originalSecret) {
	this.originalSecret = originalSecret;
    }


    /**
     * @return the originalFormat
     */
    public String getOriginalFormat() {
	return originalFormat;
    }


    /**
     * @param originalFormat the originalFormat to set
     */
    public void setOriginalFormat(String originalFormat) {
	this.originalFormat = originalFormat;
    }


    /**
     * @return the views
     */
    public int getViews() {
	return views;
    }


    /**
     * @param views the views to set
     */
    public void setViews(int views) {
	this.views = views;
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
     * @return the isPublic
     */
    public boolean isIsPublic() {
	return isPublic;
    }


    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(boolean isPublic) {
	this.isPublic = isPublic;
    }


    /**
     * @return the isFriend
     */
    public boolean isIsFriend() {
	return isFriend;
    }


    /**
     * @param isFriend the isFriend to set
     */
    public void setIsFriend(boolean isFriend) {
	this.isFriend = isFriend;
    }


    /**
     * @return the isFamily
     */
    public boolean isIsFamily() {
	return isFamily;
    }


    /**
     * @param isFamily the isFamily to set
     */
    public void setIsFamily(boolean isFamily) {
	this.isFamily = isFamily;
    }


    /**
     * @return the dateTaken
     */
    public Date getDateTaken() {
	return dateTaken;
    }


    /**
     * @param dateTaken the dateTaken to set
     */
    public void setDateTaken(Date dateTaken) {
	this.dateTaken = dateTaken;
    }


    /**
     * @return the dateTakenGranularity
     */
    public String getDateTakenGranularity() {
	return dateTakenGranularity;
    }


    /**
     * @param dateTakenGranularity the dateTakenGranularity to set
     */
    public void setDateTakenGranularity(String dateTakenGranularity) {
	this.dateTakenGranularity = dateTakenGranularity;
    }


    /**
     * @return the dateLastUpdate
     */
    public Date getDateLastUpdate() {
	return dateLastUpdate;
    }


    /**
     * @param dateLastUpdate the dateLastUpdate to set
     */
    public void setDateLastUpdate(Date dateLastUpdate) {
	this.dateLastUpdate = dateLastUpdate;
    }


    /**
     * @return the dateUploaded
     */
    public Date getDateUploaded() {
	return dateUploaded;
    }


    /**
     * @param dateUploaded the dateUploaded to set
     */
    public void setDateUploaded(Date dateUploaded) {
	this.dateUploaded = dateUploaded;
    }


    /**
     * @return the ownerNsid
     */
    public String getOwnerNsid() {
	return ownerNsid;
    }


    /**
     * @param ownerNsid the ownerNsid to set
     */
    public void setOwnerNsid(String ownerNsid) {
	this.ownerNsid = ownerNsid;
    }


    /**
     * @return the ownerUsername
     */
    public String getOwnerUsername() {
	return ownerUsername;
    }


    /**
     * @param ownerUsername the ownerUsername to set
     */
    public void setOwnerUsername(String ownerUsername) {
	this.ownerUsername = ownerUsername;
    }


    /**
     * Get the URL for a specific photo size.
     *
     * @param size the desired size.
     * @param photo
     * @return URL for the photo at the requested size.
     * @throws JinxException if there are any errors, or if some of the
     *         fields required to build the URL do not exist.
     */
    public String getUrlForSize(int size) throws JinxException {
	if (JinxUtils.isEmpty(this.secret)) {
	    throw new JinxException("URL for size " + size + " not available: no secret available.");
	}
	if (JinxUtils.isEmpty(this.farm)) {
	    throw new JinxException("URL for size " + size + " not available: no farm available.");
	}
	if (JinxUtils.isEmpty(this.server)) {
	    throw new JinxException("URL for size " + size + " not available: no server available.");
	}
	if (size == JinxConstants.SIZE_ORIGINAL) {
	    if (JinxUtils.isEmpty(this.originalFormat) || JinxUtils.isEmpty(this.originalSecret)) {
		throw new JinxException("URL for original size not available.");
	    }
	}

	StringBuilder sb = new StringBuilder("http://farm");
	sb.append(this.farm);
	sb.append(".static.flickr.com/");
	sb.append(this.server).append("/");
	sb.append(this.id).append('_');

	switch (size) {
	    case JinxConstants.SIZE_SMALL_SQUARE:
		sb.append(this.secret).append("_s.jpg");
		break;

	    case JinxConstants.SIZE_THUMBNAIL:
		sb.append(this.secret).append("_t.jpg");
		break;

	    case JinxConstants.SIZE_SMALL:
		sb.append(this.secret).append("_m.jpg");
		break;

	    case JinxConstants.SIZE_MEDIUM:
		sb.append(this.secret).append(".jpg");
		break;

	    case JinxConstants.SIZE_LARGE:
		sb.append(this.secret).append("_b.jpg");
		break;

	    case JinxConstants.SIZE_ORIGINAL:
		sb.append(this.originalSecret).append("_o");
		sb.append('.').append(this.originalFormat);
		break;

	    default:
		throw new JinxException("Undefined size: " + size);

	}

	return sb.toString();
    }


    /**
     * Get the image at the specified size.
     *
     * @param size the desired size.
     * @return image at the desired size.
     * @throws JinxException if there are any errors.
     */
    public Image getImageForSize(int size) throws JinxException {
	Image image = null;

	try {
	    image = ImageIO.read(new URL(this.getUrlForSize(size)));
	} catch (Exception e) {
	    throw new JinxException("Unable to get image for size " + size, e);
	}

	return image;
    }


    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder("PhotoBase [ ");

	sb.append("id=").append(this.id).append(" | ");
	sb.append("secret=").append(this.secret).append(" | ");
	sb.append("server=").append(this.server).append(" | ");
	sb.append("farm=").append(this.farm).append(" | ");
	sb.append("license=").append(this.license).append(" | ");
	sb.append("originalSecret=").append(this.originalSecret).append(" | ");
	sb.append("originalFormat=").append(this.originalFormat).append(" | ");
	sb.append("views=").append(this.views).append(" | ");
	sb.append("media=").append(this.media).append(" | ");
	sb.append("title=").append(this.title).append(" | ");
	sb.append("description=").append(this.description).append(" | ");
	sb.append("isPublic=").append(this.isPublic).append(" | ");
	sb.append("isFriend=").append(this.isFriend).append(" | ");
	sb.append("isFamily=").append(this.isFamily).append(" | ");
	sb.append("dateTaken=").append(this.dateTaken).append(" | ");
	sb.append("dateTakenGranularity=").append(this.dateTakenGranularity).append(" | ");
	sb.append("dateLastUpdate=").append(this.dateLastUpdate).append(" | ");
	sb.append("dateUploaded=").append(this.dateUploaded).append(" | ");
	sb.append("ownerNsid=").append(this.ownerNsid).append(" | ");
	sb.append("ownerUsername=").append(this.ownerUsername);
	
	sb.append(" ]");

	return sb.toString();
    }


}
