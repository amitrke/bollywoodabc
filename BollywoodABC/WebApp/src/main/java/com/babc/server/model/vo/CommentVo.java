package com.babc.server.model.vo;

import java.util.Date;

import com.babc.server.model.CommentEntity;
import com.babc.server.model.UserEntity;

public class CommentVo {
	
	private Long id;
	
	private Long storyId;
	
	private String title;
	
	private String body;
	
	private UserEntity author;
	
	private Date postedDate;
	
	private char status;

	public CommentVo(Long storyId, String title, String body,
			UserEntity author, Date postedDate, char status) {
		super();
		this.storyId = storyId;
		this.title = title;
		this.body = body;
		this.author = author;
		this.postedDate = postedDate;
		this.status = status;
	}

	public CommentVo(CommentEntity commentEntity, UserEntity author) {
		super();
		this.id = commentEntity.getId();
		this.storyId = commentEntity.getStoryId();
		this.title = commentEntity.getTitle();
		this.body = commentEntity.getBody();
		this.author = author;
		this.postedDate = commentEntity.getPostedDate();
		this.status = commentEntity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public Long getStoryId() {
		return storyId;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public char getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((storyId == null) ? 0 : storyId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVo other = (CommentVo) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (storyId == null) {
			if (other.storyId != null)
				return false;
		} else if (!storyId.equals(other.storyId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentVo [");
		if (author != null) {
			builder.append("author=");
			builder.append(author);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (storyId != null) {
			builder.append("storyId=");
			builder.append(storyId);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
