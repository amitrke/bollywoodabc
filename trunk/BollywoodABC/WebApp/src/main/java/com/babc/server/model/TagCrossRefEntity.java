package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.babc.server.AppConstants;
import com.babc.server.web.admin.model.TagCrossRefUpdtModel;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TagCrossRefEntity extends BaseEntityImpl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final int STORY = 1;
	public static final int PICTURE = 2;
	public static final int PHOTOGALLERY = 3;
	public static final int TWITTER_USER = 4;
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private Long tagId;
	
	@Persistent
	private Long entityId;
	
	@Persistent
	private int entityType;
	
	private Date createDate;
	
	private int status;

	/**
	 * @param tagId
	 * @param entityId
	 * @param entityType
	 * @param createDate
	 * @param status
	 */
	public TagCrossRefEntity(Long tagId, Long entityId, int entityType,
			Date createDate, int status) {
		super();
		this.tagId = tagId;
		this.entityId = entityId;
		this.entityType = entityType;
		this.createDate = createDate;
		this.status = status;
	}


	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}


	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "tagId", tagId, true);
		setProperty(entity, "entityId", entityId, true);
		setProperty(entity, "entityType", entityType, true);
		setProperty(entity, "createDate", createDate, true);
		setProperty(entity, "status", status, true);
	}


	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		tagId = getLongProperty(entity, "tagId");
		entityId = getLongProperty(entity, "entityId");
		entityType = getIntegerProperty(entity, "entityType", STORY);
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
		createDate = getDateProperty(entity, "createDate");
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public TagCrossRefEntity() {
		super();
	}
	
	public TagCrossRefEntity(TagCrossRefUpdtModel crossRefUpdtModel) {
		super();
		tagId = crossRefUpdtModel.getTagId();
		entityId = crossRefUpdtModel.getEntityId();
		entityType = crossRefUpdtModel.getEntityType();
		status = AppConstants.ENTITY_STATUS_ENABLED;
		createDate = new Date();
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	@Override
	public String toString() {
		return "TagCrossRefEntity [tagId=" + tagId + ", entityId=" + entityId
				+ ", entityType=" + entityType + "]";
	}

}
