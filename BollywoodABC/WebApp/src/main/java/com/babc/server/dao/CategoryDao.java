package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.CategoryEntity;
import com.babc.server.model.Paging;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;

@Repository("categoryDao")
public class CategoryDao extends AbstractBaseDao<CategoryEntity>{
	
	/**
	 * @param entityCache
	 * @param queryCache
	 * @param clazz
	 */
	@Autowired
	public CategoryDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, CategoryEntity.class);
	}
	
	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		return query;
	}
	
	public List<CategoryEntity> get(String type, Paging paging) {
		Query q = newQuery();
		q.addFilter("type",EQUAL, type);
		List<CategoryEntity> result = select(q, "getCategoryByType", params(type, paging.getLimit(), 
				paging.getOffset()), paging.getLimit(), paging.getOffset());
		return result;
	}
}
