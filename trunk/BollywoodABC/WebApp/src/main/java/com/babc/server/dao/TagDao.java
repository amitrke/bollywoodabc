package com.babc.server.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.TagEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("tagDao")
public class TagDao extends AbstractBaseDao<TagEntity>{
	
	private transient static final Logger LOGGER = Logger.getLogger(TagDao.class.getName());
	
	@Autowired
	public TagDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, TagEntity.class);
	}
	
	
}
