package com.babc.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.TweetEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;

@Repository("tweetsDao")
public class TweetsDao extends AbstractBaseDao<TweetEntity>{
	
	@Autowired
	public TweetsDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TweetEntity.class);
	}

	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		return query;
	}
}
