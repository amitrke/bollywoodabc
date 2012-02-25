package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getTextProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.util.Date;

import com.babc.server.AppConstants;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

public class PageCacheEntity extends BaseEntityImpl{
	
	private static final long serialVersionUID = 1L;
	
	private Date createDate;
	private String data;
	

	public PageCacheEntity() {
	}

	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "createDate", createDate, true);
		setProperty(entity, "data", new Text(data));
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		createDate = getDateProperty(entity, "createDate");
		data = getTextProperty(entity, "data");
	}

	public PageCacheEntity(Long pageCacheId, String data) {
		super();
		this.setId(pageCacheId);
		this.data = data;
		this.createDate = new Date();
	}
	
	public boolean isValid(){
		if (createDate.after(new Date(System.currentTimeMillis()-AppConstants.PAGE_DB_CACHE_DEF_EXPIRY))){
			if (data.trim().length() > 80 ){
				if (!data.contains("SERVER ERROR") ){
					return true;
				}
			}
		}
		return false;
	}
	
	public String getData() {
		return data;
	}

	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageCache [");
		if (createDate != null) {
			builder.append("createDate=");
			builder.append(createDate);
			builder.append(", ");
		}
		if (data != null) {
			builder.append("data=");
			builder.append(data);
			builder.append(", ");
		}
		if (getId() != null) {
			builder.append("getId()=");
			builder.append(getId());
		}
		builder.append("]");
		return builder.toString();
	}	
}
