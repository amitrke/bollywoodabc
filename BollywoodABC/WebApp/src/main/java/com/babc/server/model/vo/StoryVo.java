package com.babc.server.model.vo;

import java.util.Date;
import java.util.List;

import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.UserEntity;
import com.google.appengine.api.datastore.Text;

public class StoryVo {
	
	private Long id;
	
	private String title;
	
	private CategoryVo category;
	
	private UserEntity author;
	
	private String body;
	
	private String intro;
	
	private Date createDate;
	
	private int priority;
	
	private int status;
	
	private PictureEntity picture;
	
	private String video;
	
	private List<CommentVo> comments;
	
	private TagListVo tags;

	public StoryVo(String title, Long categoryId, Long userId,
			Text body, String intro, Date createDate, int priority,
			int status, PictureEntity picture, String video) {
		super();
		this.title = title;
		this.category = new CategoryVo(categoryId);
		this.author = new UserEntity(userId);
		this.body = body.getValue();
		this.intro = intro;
		this.createDate = createDate;
		this.priority = priority;
		this.status = status;
		this.picture = picture;
		this.video = video;
	}
	
	
	public StoryVo(StoryEntity storyEntity, CategoryVo categoryVo, UserEntity userEntity,
			PictureEntity pictureEntity, List<CommentVo> commentVos, List<TagVo> tags) {
		super();
		this.id = storyEntity.getId();
		this.title = storyEntity.getTitle();
		this.category = categoryVo;
		this.author = userEntity;
		this.body = storyEntity.getBody().getValue();
		this.intro = storyEntity.getIntro();
		this.createDate = storyEntity.getCreateDate();
		this.priority = storyEntity.getPriority();
		this.status = storyEntity.getStatus();
		this.picture = pictureEntity;
		this.video = storyEntity.getVideo();
		this.comments = commentVos;
		this.tags = new TagListVo(tags);
	}
	
	
	public void setPicture(PictureEntity picture) {
		this.picture = picture;
	}
	
	public String getTitleUrl(){
		return title.replace(" ", "-").replace("\'", "");
	}
	
	public String getBodyNl2br(){
		return body.toString().replace("\n","<br>");
	}
	
	public List<CommentVo> getComments() {
		return comments;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public String getBody() {
		return body;
	}

	public String getIntro() {
		return intro;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getPriority() {
		return priority;
	}

	public int getStatus() {
		return status;
	}

	public PictureEntity getPicture() {
		return picture;
	}

	public String getVideo() {
		return video;
	}

	public CategoryVo getCategory() {
		return category;
	}
	
	public TagListVo getTags() {
		return tags;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		StoryVo other = (StoryVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		builder.append("StoryVo [");
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
		if (picture != null) {
			builder.append("picture=");
			builder.append(picture);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (title != null) {
			builder.append("title=");
			builder.append(title);
		}
		builder.append("]");
		return builder.toString();
	}

}
