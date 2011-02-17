package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.AppConstants;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@Repository("tagCrossRefDao")
public class TagCrossRefDao extends AbstractBaseDao<TagCrossRefEntity> {
	
	@Autowired
	public TagCrossRefDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TagCrossRefEntity.class);
	}

	@Override
	protected Query newQuery() {
		Query query =  super.newQuery();
		query.addFilter("status", EQUAL, AppConstants.ENTITY_STATUS_ENABLED);
		query.addSort("createDate", SortDirection.DESCENDING);
		return query;
	}
	
}
