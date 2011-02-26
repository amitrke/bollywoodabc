package com.babc.server.web.admin.model;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;

public class TagCrossRefUpdtModel {
	
	
	@Min(value=1, message="Please enter tag id")
	private Long tagId;
	
	@Min(value=1, message="Please enter entity id")
	private Long entityId;
	
	@Min(value=1, message="Please select a entity type.")
	private int entityType;

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

	public TagCrossRefUpdtModel() {
	}

	@Override
	public String toString() {
		return "TagCrossRefUpdtModel [tagId=" + tagId + ", entityId="
				+ entityId + ", entityType=" + entityType + "]";
	}
	
	
}
