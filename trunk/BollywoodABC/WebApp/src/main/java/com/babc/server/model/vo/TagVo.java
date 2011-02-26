package com.babc.server.model.vo;

import java.util.List;

import com.babc.server.model.CategoryEntity;
import com.babc.server.model.StoryEntity;

public class TagVo {
	
	private int type;
	
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


	public Long getTagId() {
		return tagId;
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
