package com.babc.server.model;

import static com.babc.server.utils.EntityUtil.getDateProperty;
import static com.babc.server.utils.EntityUtil.getTextProperty;
import static com.babc.server.utils.EntityUtil.getIntegerProperty;
import static com.babc.server.utils.EntityUtil.getLongProperty;
import static com.babc.server.utils.EntityUtil.getStringProperty;
import static com.babc.server.utils.EntityUtil.setProperty;

import java.util.Date;

import com.babc.server.AppConstants;
import com.google.appengine.api.datastore.Entity;
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
	
	public MailQueueEntity() {
	}

	/**
	 * @param userId
	 * @param body
	 * @param encoding
	 * @param subject
	 * @param deliveryDate
	 * @param priority
	 */
	public MailQueueEntity(Long userId, String body, String encoding,
			String subject, int priority) {
		super();
		this.userId = userId;
		this.body = new Text(body);
		this.encoding = encoding;
		this.subject = subject;
		this.createDate = new Date();
		this.status = AppConstants.MAIL_Q_STATUS_INQUEUE;
		this.priority = priority;
	}
	
	@Override
	public void save(Entity entity) {
		super.save(entity);
		setProperty(entity, "userId", userId, false);
		setProperty(entity, "body", body);
		setProperty(entity, "encoding", encoding, false);
		setProperty(entity, "subject", subject, false);
		setProperty(entity, "createDate", createDate, true);
		setProperty(entity, "status", status, true);
		setProperty(entity, "priority", priority, true);
		setProperty(entity, "deliveryDate", deliveryDate, false);
	}


	@Override
	public void load(Entity entity) {
		super.load(entity);
		userId = getLongProperty(entity, "userId");
		body = new Text(getTextProperty(entity, "body"));
		encoding = getStringProperty(entity, "encoding");
		subject = getStringProperty(entity, "subject");
		createDate = getDateProperty(entity, "createDate");
		status = getIntegerProperty(entity, "status", 0);
		priority = getIntegerProperty(entity, "prority", 0);
		deliveryDate = getDateProperty(entity, "deliveryDate");
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
