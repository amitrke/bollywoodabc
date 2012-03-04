package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getBlobProperty;
import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.util.Date;

import com.babc.server.AppConstants;
import com.babc.server.utils.AppUtils;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.apache.commons.codec.binary.Base64;

public class PictureEntity extends BaseEntityImpl{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String filename;
	
	private String caption;
	
	private Blob data;
	
	private Blob thumbData;
	
	private int status;
	
	private Date createDate;
	
	private Base64 base64 = new Base64();
	
	public PictureEntity() {}

	public PictureEntity(Long id) {
		this.id = id;
	}

	public PictureEntity(String filename, String caption, Blob data, int status) {
		this.filename = filename;
		this.caption = caption;
		this.data = data;
		this.status = status;
		this.createDate = new Date();
	}
	
	public String getBase64(){
		return base64.encodeToString(getData().getBytes());
	}
	
	public String getThumbBase64(){
		return base64.encodeToString(getThumbData().getBytes());
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFilename() {
		return filename;
	}
	
	public String getUrlFilename(){
		String fileName = AppUtils.urlFormat(filename);
		return fileName.split("\\.")[0];
	}
	
	public String getCaption() {
		return caption;
	}
	
	public String getUrlCaption(){
		return AppUtils.urlFormat(caption);
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Blob getData() {
		return data;
	}

	public Blob getThumbData() {
		return thumbData;
	}

	public int getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	public void setThumbData(Blob thumbData) {
		this.thumbData = thumbData;
	}
	
	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}

	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "filename", filename, false);
		setProperty(entity, "caption", caption, false);
		setProperty(entity, "data", data.getBytes());
		setProperty(entity, "thumbData", thumbData.getBytes());
		setProperty(entity, "status", status, true);
		setProperty(entity, "createDate", createDate, true);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		filename = getStringProperty(entity, "filename");
		caption = getStringProperty(entity, "caption");
		data = new Blob(getBlobProperty(entity, "data"));
		thumbData = new Blob(getBlobProperty(entity, "thumbData"));
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
		createDate = getDateProperty(entity, "createDate");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + status;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PictureEntity other = (PictureEntity) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PictureEntity [");
		if (filename != null) {
			builder.append("filename=");
			builder.append(filename);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
