package com.babc.server.model;

import java.io.Serializable;
import java.util.Date;

public class SubscriptionEntity extends BaseEntityImpl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private int subscriptionId;
	
	private Date createDate;

	/**
	 * @param userId
	 * @param subscriptionId
	 */
	public SubscriptionEntity(Long userId, int subscriptionId) {
		this.userId = userId;
		this.subscriptionId = subscriptionId;
		this.createDate = new Date();
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
