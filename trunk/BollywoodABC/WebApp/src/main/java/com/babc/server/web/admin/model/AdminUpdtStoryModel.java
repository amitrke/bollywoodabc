package com.babc.server.web.admin.model;

import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Max;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Min;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

public class AdminUpdtStoryModel {
	
	@Min(value=1, message="Please select a category.")
	private Long category;
	
	@NotBlank(message="Please enter a title")
	private String title;
	
	@Min(value=1, message="Author Id should be > 1")
	private Long author;
	
	@NotBlank(message="Introduction is required.")
	private String intro;
	
	@Min(value=1, message="Priority should be > 1")
	@Max(value=10, message="Priority should be < 10")
	private Integer priority;
	
	@NotBlank(message="Body cannot be blank")
	private String body;
	
	private String video;
	
	private String imageCaption;
	
	private Long imageId;
	
	private MultipartFile imageData;

	public AdminUpdtStoryModel() {
		author=0L;
		priority=10;
		imageId=0L;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public MultipartFile getImageData() {
		return imageData;
	}

	public void setImageData(MultipartFile imageData) {
		this.imageData = imageData;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAuthor() {
		return author;
	}

	public void setAuthor(Long author) {
		this.author = author;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public void setImageCaption(String imageCaption) {
		this.imageCaption = imageCaption;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
}
