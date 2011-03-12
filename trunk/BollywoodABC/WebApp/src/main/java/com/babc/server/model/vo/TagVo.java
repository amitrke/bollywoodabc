package com.babc.server.model.vo;

import java.util.List;

import com.babc.server.model.CategoryEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.TagEntity;

public class TagVo {
	
	private int type; //Tag Type e.g Actor, Alias, etc.
	
	private int entityType; //Cross Ref type e.g. story, picture, etc.
	
	private Long entityId;
	
	private Long tagId;
	
	private String description;
	
	private String tag;
	
	private String url;
	
	private List<StoryEntity> relatedStories;
	
	private List<CategoryEntity> relatedPhotogalleries;
		

	/**
	 * @param type
	 * @param tagId
	 * @param description
	 * @param tag
	 */
	public TagVo(int type, Long tagId, String description, String tag) {
		super();
		this.type = type;
		this.tagId = tagId;
		this.description = description;
		this.tag = tag;
	}

	/**
	 * @param type
	 * @param crossRefType
	 * @param tagId
	 * @param description
	 * @param tag
	 */
	public TagVo(int type, int entityType, Long tagId, Long entityId, 
			String description,	String tag) {
		super();
		this.type = type;
		this.entityType = entityType;
		this.tagId = tagId;
		this.description = description;
		this.tag = tag;
		this.entityId = entityId;
	}

	
	public Long getEntityId() {
		return entityId;
	}

	public Long getTagId() {
		return tagId;
	}

	public String getTagTypeAsString(){
		return TagEntity.getTypeAsString(type);
	}
	
	public String getEntityTypeAsString(){
		switch(type){
			case 1: return "Story";
			case 2: return "Picture";
			case 3: return "Photogallery";
		}
		return "Not Mapped in Getter";
	}
	
	public int getEntityType() {
		return entityType;
	}

	public List<StoryEntity> getRelatedStories() {
		return relatedStories;
	}


	public void setRelatedStories(List<StoryEntity> relatedStories) {
		this.relatedStories = relatedStories;
	}


	public List<CategoryEntity> getRelatedPhotogalleries() {
		return relatedPhotogalleries;
	}


	public void setRelatedPhotogalleries(List<CategoryEntity> relatedPhotogalleries) {
		this.relatedPhotogalleries = relatedPhotogalleries;
	}


	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public int getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public String toString() {
		return "TagVo [type=" + type + ", description=" + description
				+ ", tag=" + tag + "]";
	}
	
	
}
