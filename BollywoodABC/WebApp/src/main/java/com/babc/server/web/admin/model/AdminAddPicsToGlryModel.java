package com.babc.server.web.admin.model;

import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;

public class AdminAddPicsToGlryModel {
	
	@Min(value=1, message="Please select a category.")
	private String categoryId;
	
	private String[] picIds;

	public AdminAddPicsToGlryModel() {
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String[] getPicIds() {
		return picIds;
	}

	public void setPicIds(String[] picIds) {
		this.picIds = picIds;
	}
}
