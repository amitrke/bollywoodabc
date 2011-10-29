package com.babc.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.TwitterUserDetailEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;

@Repository("twitterUserDetailDao")
public class TwitterUserDetailDao extends AbstractBaseDao<TwitterUserDetailEntity>{
	
	@Autowired
	public TwitterUserDetailDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TwitterUserDetailEntity.class);
	}
	
	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		return query;
	}
}
