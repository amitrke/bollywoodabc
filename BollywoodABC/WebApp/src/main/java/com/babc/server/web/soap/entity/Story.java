package com.babc.server.web.soap.entity;

import java.util.Date;

import com.babc.server.model.StoryEntity;

public class Story {
	
	private String title;
	
	//private Date createDate;
	
	private String intro;
	
	private String body;

	public Story() {
	}

	/**
	 * @param title
	 * @param createDate
	 * @param intro
	 * @param body
	 */
	public Story(String title, Date createDate, String intro, String body) {
		super();
		this.title = title;
		//this.createDate = createDate;
		this.intro = intro;
		this.body = body;
	}
	
	public Story(StoryEntity storyEntity) {
		super();
		this.title = storyEntity.getTitle();
		//this.createDate = storyEntity.getCreateDate();
		this.intro = storyEntity.getIntro();
		this.body = storyEntity.getBody().getValue();
	}
	
	public String getTitle() {
		return title;
	}

//	public Date getCreateDate() {
//		return createDate;
//	}

	public String getIntro() {
		return intro;
	}

	public String getBody() {
		return body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Story [");
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (intro != null) {
			builder.append("intro=");
			builder.append(intro);
			builder.append(", ");
		}
		if (body != null) {
			builder.append("body=");
			builder.append(body);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
