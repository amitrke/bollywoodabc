package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;
import static com.google.appengine.api.datastore.Query.FilterOperator.LESS_THAN_OR_EQUAL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.AppConstants;
import com.babc.server.model.MailQueueEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@Repository("mailQueueDao")
public class MailQueueDao extends AbstractBaseDao<MailQueueEntity> {

	@Autowired
	public MailQueueDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, MailQueueEntity.class);
	}

	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		query.addFilter("status", EQUAL, AppConstants.MAIL_Q_STATUS_INQUEUE);
		query.addSort("priority", SortDirection.ASCENDING);
		query.addSort("createDate", SortDirection.ASCENDING);
		return query;
	}
	
	public List<MailQueueEntity> getByPriority(int priority){
		Query query = newQuery();
		query.addFilter("priority", LESS_THAN_OR_EQUAL, priority);
		List<MailQueueEntity> mailQueueEntities = selectNotCache(query);
		return mailQueueEntities;
	}
}
