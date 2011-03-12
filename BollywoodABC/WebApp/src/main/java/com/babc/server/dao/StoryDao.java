package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.AppConstants;
import com.babc.server.model.CategoryEntity;
import com.babc.server.model.DataImportKey;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

@Repository("storyDao")
public class StoryDao extends AbstractBaseDao<StoryEntity> {

	private transient static final Logger LOGGER = Logger.getLogger(StoryDao.class.getName());
	
	/**
	 * @param entityCache
	 * @param queryCache
	 * @param clazz
	 */
	@Autowired
	public StoryDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, StoryEntity.class);
	}

	/**
	 * Gets the total number of stores in the database.
	 * 
	 * @return
	 */
	public Integer getCount(List<CategoryEntity> categories){
		Integer count = 0;
		for(CategoryEntity category: categories){
			count += getCount(category.getId());
		}
		return count;
	}
	
	/**
	 * Get total number of stories for a particular category.
	 * 
	 * @param category
	 * @return
	 */
	public Integer getCount(Long category){
		return getCountFromDatabase(category);
		/*
		Object countCache = getEntityCache().getEntity(HashMap.class, "storycount");
		if (countCache != null){
			Map<Long, Integer> countCacheMap = (Map<Long, Integer>) countCache;
			if (countCacheMap.containsKey(category)){
				return countCacheMap.get(category);
			}
			else{
				return refreshCountMapCache(countCacheMap, category);
			}
		}
		else{
			return refreshCountMapCache(new HashMap<Long, Integer>(), category);
		}
		*/
	}
	
	private Integer refreshCountMapCache(Map<Long, Integer> countCacheMap, Long category){
		Integer count = getCountFromDatabase(category);
		countCacheMap.put(category, count);
		getEntityCache().putEntity(HashMap.class, "storycount", countCacheMap);
		LOGGER.info("Count cache map refreshed :"+countCacheMap.toString());
		return count;
	}
	
	/**
	 * Gets the story count from database, this function should not have cache enabled.
	 * 
	 * @param category
	 * @return
	 */
	private Integer getCountFromDatabase(Long category){
		List<StoryEntity> storyEntities = get(category, new Paging(Integer.MAX_VALUE,0));
		return storyEntities.size();
	}	
	
	public DataImportKey addDataImportKey(DataImportKey dataImportKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		DataImportKey response = null;
		try {
			response = pm.makePersistent(dataImportKey);
			LOGGER.info("New DataImportKey added "+response);
		} finally {
			pm.close();
		}
		
		return response;
	}
	
	@Override
	protected Query newQuery() {
		Query query =  super.newQuery();
		query.addFilter("status", EQUAL, AppConstants.ENTITY_STATUS_ENABLED);
		query.addSort("createDate", SortDirection.DESCENDING);
		query.addSort("priority", SortDirection.DESCENDING);
		return query;
	}
	
	public List<StoryEntity> get(Long category, Paging paging) {
		Query q = newQuery();
		q.addFilter("categoryId",EQUAL, category);
		List<StoryEntity> result = select(q, "getStoriesByCategory", params(paging.getLimit(), 
				paging.getOffset(), category), paging.getLimit(), paging.getOffset());
		return result;
	}
	
	private List<StoryEntity> copy(List<StoryEntity> storyEntities){
		List<StoryEntity> entities = new ArrayList<StoryEntity>();
		entities.addAll(storyEntities);
		return entities;
	}
}
