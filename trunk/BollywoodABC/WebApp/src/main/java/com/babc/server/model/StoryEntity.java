package com.babc.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import static com.babc.server.utils.EntityUtil.setProperty;
import static com.babc.server.utils.EntityUtil.setTextProperty;

import com.babc.server.AppConstants;
import com.babc.server.model.vo.StoryVo;
import com.babc.server.utils.AppUtils;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.getTextProperty;
import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class StoryEntity extends BaseEntityImpl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	
	@Persistent
	private String title;
	
	@Persistent
	private Long authorId;
	
	@Persistent
	private Long categoryId;
	
	@Persistent
	private Text body;
	
	@Persistent
	private String intro;
	
	@Persistent
	private Date createDate;
	
	@Persistent
	private int priority;
	
	@Persistent
	private int status;
	
	@Persistent
	private Long pictureId;
	
	@Persistent
	private String video;
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "title", title, false);
		setProperty(entity, "authorId", authorId, true);
		setProperty(entity, "categoryId", categoryId, true);
		setTextProperty(entity, "body", body.getValue());
		setProperty(entity, "intro", intro, false);
		setProperty(entity, "createDate", createDate, true);
		setProperty(entity, "priority", priority, true);
		setProperty(entity, "status", status, true);
		setProperty(entity, "pictureId", pictureId, false);
		setProperty(entity, "video", video, false);
	}
	
	@Override
	public void setKey(Key key) {
		super.setKey(key);
		id = key.getId();
	}
	
	@Override
	public void load(Entity entity) {
		super.load(entity);
		id = getKey().getId();
		title = getStringProperty(entity, "title");
		authorId = getLongProperty(entity, "authorId");
		categoryId = getLongProperty(entity, "categoryId");
		body = new Text(getTextProperty(entity, "body"));
		intro = getStringProperty(entity, "intro");
		createDate = getDateProperty(entity, "createDate");
		priority = getIntegerProperty(entity, "priority", 10);
		status = getIntegerProperty(entity, "status", AppConstants.ENTITY_STATUS_ENABLED);
		pictureId = getLongProperty(entity, "pictureId");
		video = getStringProperty(entity, "video");
	}


	public StoryEntity() {
		super();
	}

	public StoryEntity(String title, Long authorId, Long categoryId, Text body,
			String intro, Date createDate, int priority, char status,
			Long pictureId, String video) {
		super();
		this.title = title;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.body = body;
		this.intro = intro;
		this.createDate = createDate;
		this.priority = priority;
		this.status = status;
		this.pictureId = pictureId;
		this.video = video;
	}

	public StoryEntity(StoryVo storyVo) {
		super();
		this.id = storyVo.getId();
		this.title = storyVo.getTitle();
		this.authorId = storyVo.getAuthor().getId();
		this.body = new Text(storyVo.getBody());
		this.intro = storyVo.getIntro();
		this.createDate = storyVo.getCreateDate();
		this.priority = storyVo.getPriority();
		this.status = storyVo.getStatus();
		this.pictureId = storyVo.getPicture().getId();
		this.video = storyVo.getVideo();
		this.categoryId = storyVo.getCategory().getId();
	}
	
	public String getTitleUrl(){
		return AppUtils.urlFormat(title);
	}
	
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Text getBody() {
		return body;
	}
	
	public String getBodyNl2br(){
		return body.toString().replace("\n","<br>");
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

	public Long getPictureId() {
		return pictureId;
	}

	public String getVideo() {
		return video;
	}

	public Long getCategoryId() {
		return categoryId;
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
		StoryEntity other = (StoryEntity) obj;
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
		builder.append("StoryEntity [");
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
		if (pictureId != null) {
			builder.append("pictureId=");
			builder.append(pictureId);
			builder.append(", ");
		}
		builder.append("priority=");
		builder.append(priority);
		builder.append(", status=");
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
