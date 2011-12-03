package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.io.Serializable;
import java.util.Date;

import com.babc.server.AppConstants;
import com.google.appengine.api.datastore.Entity;

public class UserEntity extends BaseEntityImpl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String email;
	
	private int status;
	
	private int type;
	
	private Date createDate;
	
	public UserEntity() {
		super();
	}
	
	public UserEntity(Long id) {
		setId(id);
	}
	
	public UserEntity(String name, String email) {
		this.name = name;
		this.email = email;
		this.status = AppConstants.ENTITY_STATUS_ENABLED;
		this.type = AppConstants.USER_TYPE_SUBSCRIBER;
		createDate = new Date();
	}
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "name", name, false);
		setProperty(entity, "email", email, true);
		setProperty(entity, "status", status, true);
		setProperty(entity, "type", type, true);
		setProperty(entity, "createDate", createDate, true);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		name = getStringProperty(entity, "name");
		email = getStringProperty(entity, "email");
		status = getIntegerProperty(entity, "status", 0);
		type = getIntegerProperty(entity, "type", 0);
		createDate = getDateProperty(entity, "createDate");
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getStatus() {
		return status;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEntity [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", type=");
		builder.append(type);
		builder.append(", ");
		if (createDate != null) {
			builder.append("createDate=");
			builder.append(createDate);
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
