package com.babc.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("tagCrossRefDao")
public class TagCrossRefDao extends AbstractBaseDao<TagCrossRefEntity> {
	
	@Autowired
	public TagCrossRefDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TagCrossRefEntity.class);
	}
	
}
