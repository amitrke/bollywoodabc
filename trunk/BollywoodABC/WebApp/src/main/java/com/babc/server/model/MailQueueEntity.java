package com.babc.server.model;

import java.util.Date;

import com.babc.server.AppConstants;
import com.google.appengine.api.datastore.Text;

public class MailQueueEntity extends BaseEntityImpl {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private Text body;
	
	private String encoding;
	
	private String subject;
	
	private int status;
	
	private Date createDate;
	
	private Date deliveryDate;
	
	private int priority;
	
	/**
	 * @param userId
	 * @param body
	 * @param encoding
	 * @param subject
	 * @param deliveryDate
	 * @param priority
	 */
	public MailQueueEntity(Long userId, Text body, String encoding,
			String subject, int priority) {
		super();
		this.userId = userId;
		this.body = body;
		this.encoding = encoding;
		this.subject = subject;
		this.createDate = new Date();
		this.status = AppConstants.MAIL_Q_STATUS_INQUEUE;
		this.priority = priority;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getPriority() {
		return priority;
	}


	public Long getUserId() {
		return userId;
	}

	public Text getBody() {
		return body;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getSubject() {
		return subject;
	}

	public int getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MailQueueEntity [");
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
			builder.append(", ");
		}
		if (encoding != null) {
			builder.append("encoding=");
			builder.append(encoding);
			builder.append(", ");
		}
		if (subject != null) {
			builder.append("subject=");
			builder.append(subject);
			builder.append(", ");
		}
		builder.append("status=");
		builder.append(status);
		builder.append(", ");
		if (createDate != null) {
			builder.append("createDate=");
			builder.append(createDate);
			builder.append(", ");
		}
		if (deliveryDate != null) {
			builder.append("deliveryDate=");
			builder.append(deliveryDate);
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
