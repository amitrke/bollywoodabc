package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;
import twitter4j.Status;

import com.google.appengine.api.datastore.Entity;

public class TwitterUserDetailEntity extends BaseEntityImpl {

	private static final long serialVersionUID = 1L;

	private String screenName;
	
	private String name;
	
	private String description;
	
	private String profileImageUrl;
	
	public TwitterUserDetailEntity() {
	}
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "screenName", screenName, false);
		setProperty(entity, "name", name, false);
		setProperty(entity, "description", description, false);
		setProperty(entity, "profileImageUrl", profileImageUrl, false);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		screenName = getStringProperty(entity, "screenName");
		name = getStringProperty(entity, "name");
		description = getStringProperty(entity, "description");
		profileImageUrl = getStringProperty(entity, "profileImageUrl");
	}


	public TwitterUserDetailEntity(Status status) {
		this.setId(status.getUser().getId());
		this.screenName = status.getUser().getScreenName();
		this.name = status.getUser().getName();
		this.description = status.getUser().getDescription();
		this.profileImageUrl = status.getUser().getProfileImageURL().getRef();
	}

	public String getScreenName() {
		return screenName;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwitterUserDetailEntity [");
		if (screenName != null) {
			builder.append("screenName=");
			builder.append(screenName);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
			builder.append(", ");
		}
		if (profileImageUrl != null) {
			builder.append("profileImageUrl=");
			builder.append(profileImageUrl);
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
