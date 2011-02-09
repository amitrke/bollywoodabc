package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.babc.server.AppConstants;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Photogallery extends BaseEntityImpl{
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private Long categoryId;
	
	@Persistent
	private Long picId;
	
	@Persistent
	private int hits;
	
	@Persistent
	private int status;

	public Photogallery() {
		super();
	}

	public Photogallery(Long categoryId, Long picId, int hits) {
		this.categoryId = categoryId;
		this.picId = picId;
		this.hits = hits;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public Long getPicId() {
		return picId;
	}

	public int getHits() {
		return hits;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "categoryId", categoryId, true);
		setProperty(entity, "picId", picId, false);
		setProperty(entity, "hits", hits, true);
		setProperty(entity, "status", status, true);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		categoryId = getLongProperty(entity, "categoryId");
		picId = getLongProperty(entity, "picId");
		hits = getIntegerProperty(entity, "hits", 0);
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((picId == null) ? 0 : picId.hashCode());
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
		Photogallery other = (Photogallery) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (picId == null) {
			if (other.picId != null)
				return false;
		} else if (!picId.equals(other.picId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photogallery [");
		if (categoryId != null) {
			builder.append("categoryId=");
			builder.append(categoryId);
			builder.append(", ");
		}
		if (picId != null) {
			builder.append("picId=");
			builder.append(picId);
		}
		builder.append("]");
		return builder.toString();
	}
}
