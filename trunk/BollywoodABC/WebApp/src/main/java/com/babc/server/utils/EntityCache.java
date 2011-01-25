package com.babc.server.utils;

import java.util.HashSet;
import java.util.Set;

import javax.cache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("entityCache")
public class EntityCache {
	
	private CacheService cacheService;
	private long calls;
	private long hits;
	
	@Autowired
	public EntityCache(CacheService cacheService) {
		this.cacheService = cacheService;
		calls = 0;
		hits = 0;
	}
	
	@SuppressWarnings("unchecked")
	private String getEntityKey(Class clazz, Object id) {
		return "entity:" + clazz.getName() + id.toString();
	}
	
	@SuppressWarnings("unchecked")
	public Object getEntity(Class clazz, Object id) {
		calls++;
		Set<String> entityKeys = getEntityKeySet(clazz);
		String key = getEntityKey(clazz, id);
		if (entityKeys.contains(key) && getCache().containsKey(key)) {
			Object result = getCache().get(key);
			if (result != null) {
				hits++;
			}
			return result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private String getEntityKeySetKey(Class clazz) {
		return "entityList:" + clazz.getName();
	}
	
	@SuppressWarnings("unchecked")
	private Set<String> getEntityKeySet(Class clazz) {
		Set<String> result = (Set<String>) getCache().get(getEntityKeySetKey(clazz));
		if (result == null) {
			result = new HashSet<String>();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void updateEntityKeySet(Class clazz, Set<String> set) {
		getCache().put(getEntityKeySetKey(clazz), set);
	}
	
	@SuppressWarnings("unchecked")
	private void addEntityToKeySet(Class clazz, String key) {
		Set<String> set = getEntityKeySet(clazz);
		set.add(key);
		updateEntityKeySet(clazz, set);
	}
	
	@SuppressWarnings("unchecked")
	private void removeEntityFromKeySet(Class clazz, String key) {
		Set<String> set = getEntityKeySet(clazz);
		set.remove(key);
		updateEntityKeySet(clazz, set);
	}
		
	@SuppressWarnings("unchecked")
	public void putEntity(Class clazz, Object id, Object entity) {
		String key = getEntityKey(clazz, id);
		getCache().put(key, entity);
		addEntityToKeySet(clazz, key);
	}

	@SuppressWarnings("unchecked")
	public void removeEntities(Class clazz) {
		Set<String> keySet = getEntityKeySet(clazz);
		for (String key : keySet) {
			getCache().remove(key);
		}
		keySet.clear();
		updateEntityKeySet(clazz, keySet);
	}

	@SuppressWarnings("unchecked")
	public void removeEntity(Class clazz, Object id) {
		String key = getEntityKey(clazz, id);
		if (getCache().containsKey(key)) {
			getCache().remove(getEntityKey(clazz, id));
		}
		removeEntityFromKeySet(clazz, key);
	}

	private Cache getCache() {
		return cacheService;
	}

	public CacheStat getStat() {
		return new CacheStat(calls, hits);
	}
}
