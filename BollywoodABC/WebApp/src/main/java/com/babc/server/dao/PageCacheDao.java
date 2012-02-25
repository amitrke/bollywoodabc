package com.babc.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.PageCacheEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("pageCacheDao")
public class PageCacheDao extends AbstractBaseDao<PageCacheEntity> {
	
	@Autowired
	public PageCacheDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, PageCacheEntity.class);
	}

}
