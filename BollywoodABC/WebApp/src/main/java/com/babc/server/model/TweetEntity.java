package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.io.Serializable;
import java.util.Date;

import twitter4j.Status;

import com.google.appengine.api.datastore.Entity;

public class TweetEntity extends BaseEntityImpl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date createdAt;
	
	private String text;
	
	private Long inReplyToStatusId;
	
	private Long inReplyToUserId;
	
	private int favorited;
	
	private Long userId;
	
	private int retweet;
	
	private int retweetedByMe;
	
	public TweetEntity() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public TweetEntity(Status status) {
		this.setId(status.getId());
		this.createdAt = status.getCreatedAt();
		this.text = status.getText();
		this.inReplyToStatusId = status.getInReplyToStatusId();
		this.inReplyToUserId = status.getInReplyToUserId();
		if (status.isFavorited()){
			this.favorited = 1;
		}
		else{
			this.favorited = 0;
		}
		
		this.userId = status.getUser().getId();
		
		if (status.isRetweet()){
			this.retweet = 1;
		}
		else{
			this.retweet = 0;
		}
		
		if (status.isRetweetedByMe()){
			this.retweetedByMe = 1;
		}
		else{
			this.retweetedByMe = 0;
		}
	}
	
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "createdAt", createdAt, true);
		setProperty(entity, "text", text, false);
		setProperty(entity, "inReplyToStatusId", inReplyToStatusId, false);
		setProperty(entity, "inReplyToUserId", inReplyToUserId, true);
		setProperty(entity, "favorited", favorited, true);
		setProperty(entity, "userId", userId, true);
		setProperty(entity, "retweet", retweet, true);
		setProperty(entity, "retweetedByMe", retweetedByMe, true);
	}

	@Override
	public void load(Entity entity) {
		super.load(entity);
		createdAt = getDateProperty(entity, "createdAt");
		text = getStringProperty(entity, "text");
		inReplyToStatusId = getLongProperty(entity, "inReplyToStatusId");
		inReplyToUserId = getLongProperty(entity, "inReplyToUserId");
		favorited = getIntegerProperty(entity, "favorited", 0);
		userId = getLongProperty(entity, "userId");
		retweet = getIntegerProperty(entity, "retweet", 0);
		retweetedByMe = getIntegerProperty(entity, "retweetedByMe", 0);
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getText() {
		return text;
	}

	public Long getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public Long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public int getFavorited() {
		return favorited;
	}

	public int getRetweet() {
		return retweet;
	}

	public int getRetweetedByMe() {
		return retweetedByMe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TweetEntity [");
		if (text != null) {
			builder.append("text=");
			builder.append(text);
			builder.append(", ");
		}
		if (getId() != null) {
			builder.append("getId()=");
			builder.append(getId());
		}
		builder.append("]");
		return builder.toString();
	}	
}