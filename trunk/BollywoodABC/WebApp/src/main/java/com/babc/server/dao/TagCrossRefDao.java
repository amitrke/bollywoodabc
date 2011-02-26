package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.List;

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
	
	public List<TagCrossRefEntity> get(Long entityId, int entityType){
		Query query = newQuery();
		query.addFilter("entityId", EQUAL, entityId);
		query.addFilter("entityType", EQUAL, entityType);
		return super.select(query, "getTagCrossRefByEnIdEnTyp", params(entityId, entityType), 
				Integer.MAX_VALUE, 0);
	}
	
	public List<TagCrossRefEntity> getByTagId(Long tagId, int entityType){
		Query query = newQuery();
		query.addFilter("tagId", EQUAL, tagId);
		query.addFilter("entityType", EQUAL, entityType);
		return super.select(query, "getTagCrossRefByTgIdEnTyp", params(tagId, entityType), 
				AppConstants.DATA_DEFAULT_LIMIT, 0);
	}
	
}
