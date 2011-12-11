package com.babc.server.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.SubscriptionEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
@Repository("subscriptionDao")
public class SubscriptionDao extends AbstractBaseDao<SubscriptionEntity> {
	
	@Autowired
	public SubscriptionDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, SubscriptionEntity.class);
	}
	
	public SubscriptionEntity get(Long userId, int subscriptionId){
		Query query = newQuery();
		query.addFilter("userId", FilterOperator.EQUAL, userId);
		query.addFilter("subscriptionId", FilterOperator.EQUAL, subscriptionId);
		return selectOne(query, "getSubscriptionByUserIdAndSubscriptionId", params(userId, subscriptionId));
	}
	
	public List<SubscriptionEntity> get(int subscriptionId){
		Query query = newQuery();
		query.addFilter("subscriptionId", FilterOperator.EQUAL, subscriptionId);
		return select(query, "getSubscriptionsBySubscriptionId", params(subscriptionId), Integer.MAX_VALUE, 0);
	}
}
