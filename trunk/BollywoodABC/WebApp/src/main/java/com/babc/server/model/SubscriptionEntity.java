package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class SubscriptionEntity extends BaseEntityImpl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private int subscriptionId;
	
	private Date createDate;

	public SubscriptionEntity() {
	}


	/**
	 * @param userId
	 * @param subscriptionId
	 */
	public SubscriptionEntity(Long userId, int subscriptionId) {
		this.userId = userId;
		this.subscriptionId = subscriptionId;
		this.createDate = new Date();
	}
	
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "userId", userId, true);
		setProperty(entity, "subscriptionId", subscriptionId, true);
		setProperty(entity, "createDate", createDate, false);
	}


	@Override
	public void load(Entity entity) {
		super.load(entity);
		userId = getLongProperty(entity, "userId");
		subscriptionId = getIntegerProperty(entity, "subscriptionId", 0);
		createDate = getDateProperty(entity, "createDate");
	}


	public Long getUserId() {
		return userId;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public Date getCreateDate() {
		return createDate;
	}
}
