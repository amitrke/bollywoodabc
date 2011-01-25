package com.babc.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.babc.server.model.vo.CommentVo;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class CommentEntity {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private Long storyId;
	
	@Persistent
	private String title;
	
	@Persistent
	private String body;
	
	@Persistent
	private Long authorId;
	
	@Persistent
	private Date postedDate;
	
	@Persistent
	private char status;

	public CommentEntity(Long storyId, String title, String body, Long authorId,
			char status) {
		super();
		this.storyId = storyId;
		this.title = title;
		this.body = body;
		this.authorId = authorId;
		this.status = status;
		this.postedDate = new Date();
	}
	
	
	public CommentEntity(CommentVo commentVo) {
		this.id = commentVo.getId();
		this.storyId = commentVo.getStoryId();
		this.title = commentVo.getTitle();
		this.body = commentVo.getBody();
		this.authorId = commentVo.getAuthor().getId();
		this.postedDate = commentVo.getPostedDate();
		this.status = commentVo.getStatus();
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

	public Long getAuthorId() {
		return authorId;
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
		result = prime * result
				+ ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + status;
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
		CommentEntity other = (CommentEntity) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
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
		builder.append("CommentEntity [");
		if (authorId != null) {
			builder.append("authorId=");
			builder.append(authorId);
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
