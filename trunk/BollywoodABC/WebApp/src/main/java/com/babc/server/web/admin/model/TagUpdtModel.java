package com.babc.server.web.admin.model;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class TagUpdtModel {
	
	@Min(value=1, message="Please select a type.")
	private int type;
	
	@NotBlank(message="Please enter tag name")
	private String tag;
	
	private String description;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 */
	public TagUpdtModel() {
	}

	@Override
	public String toString() {
		return "TagUpdtModel [type=" + type + ", tag=" + tag + ", description="
				+ description + "]";
	}
}
