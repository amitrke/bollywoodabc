package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.TweetEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@Repository("tweetsDao")
public class TweetsDao extends AbstractBaseDao<TweetEntity>{
	
	@Autowired
	public TweetsDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TweetEntity.class);
	}

	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		query.addSort("createdAt", SortDirection.DESCENDING);
		return query;
	}
	
	public List<TweetEntity> getByUserId(Long userId, Paging paging){
		Query q = newQuery();
		q.addFilter("userId",EQUAL, userId);
		List<TweetEntity> result = select(q, "getTweetsByUserId", params(paging.getLimit(), 
				paging.getOffset(), userId), paging.getLimit(), paging.getOffset());
		return result;
	}
}
