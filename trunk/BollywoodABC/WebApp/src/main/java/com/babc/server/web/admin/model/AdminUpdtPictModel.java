package com.babc.server.web.admin.model;

import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class AdminUpdtPictModel {
	
	@NotBlank(message="Please enter a caption")
	private String caption;
	
	private MultipartFile file;
	
	private Long existingPicId;
	
	public AdminUpdtPictModel() {
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getExistingPicId() {
		return existingPicId;
	}

	public void setExistingPicId(Long existingPicId) {
		this.existingPicId = existingPicId;
	}
	
}
