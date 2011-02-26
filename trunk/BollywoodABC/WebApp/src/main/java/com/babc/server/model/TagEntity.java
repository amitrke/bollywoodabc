package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.babc.server.AppConstants;
import com.babc.server.web.admin.model.TagUpdtModel;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TagEntity extends BaseEntityImpl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ACTOR=1;
	public static final int EVENT=2; // Movie or Party event
	public static final int ALIAS=3;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String tag;
	
	@Persistent
	private int type;
	
	@Persistent
	private Date createDate;
	
	private String description;
	
	private int status;
	
	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}
	
	/**
	 * @param tag
	 * @param type
	 * @param createDate
	 * @param description
	 * @param status
	 */
	public TagEntity(String tag, int type, Date createDate, String description,
			int status) {
		super();
		this.tag = tag;
		this.type = type;
		this.createDate = createDate;
		this.description = description;
		this.status = status;
	}

	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "tag", tag, false);
		setProperty(entity, "type", type, true);
		setProperty(entity, "createDate", createDate, true);
		setProperty(entity, "status", status, true);
		setProperty(entity, "description", description, false);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		tag = getStringProperty(entity, "tag");
		type = getIntegerProperty(entity, "type", ALIAS);
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
		createDate = getDateProperty(entity, "createDate");
		description = getStringProperty(entity, "description");
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TagEntity() {
		super();
	}
	
	public TagEntity(TagUpdtModel tagUpdtModel) {
		super();
		tag = tagUpdtModel.getTag();
		type = tagUpdtModel.getType();
		createDate = new Date();
		description = tagUpdtModel.getDescription();
		status = AppConstants.ENTITY_STATUS_ENABLED;
	}

	@Override
	public String toString() {
		return "TagEntity [id=" + id + ", tag=" + tag + ", type=" + type + "]";
	}
	
}
