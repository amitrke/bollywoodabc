package com.babc.server.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("queryCache")
public class QueryCache {
	
	protected static final Log logger = LogFactory.getLog(
			QueryCache.class);

	private CacheService cacheService;
	private long calls;
	private long hits;
	
	@Autowired
	public QueryCache(CacheService cacheService) {
		this.cacheService = cacheService;
		calls = 0;
		hits = 0;
	}
	
	@SuppressWarnings("unchecked")
	private String getQueryMapKey(Class clazz) {
		return "queries:" + clazz.getName();
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Set<String>> getQueryMap(Class clazz) {
		Map<String, Set<String>> result = (Map<String, Set<String>>) cacheService
				.get(getQueryMapKey(clazz));
		if (result == null) {
			result = new HashMap<String, Set<String>>();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void updateQueryMap(Class clazz, Map<String, Set<String>> map) {
		cacheService.put(getQueryMapKey(clazz), map);
	}

	private String getQueryKey(String query, Object[] params) {
		StringBuffer result = new StringBuffer(String.valueOf(query.hashCode()));
		if (params != null) {
			for (Object param : params) {
				result.append(param != null ? 
						String.valueOf(param.toString().hashCode()) : "null"); 
			}
		}
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public Object getQuery(Class clazz, String query, Object[] params) {
		calls++;
		Map<String, Set<String>> map = getQueryMap(clazz);
		Set<String> set = map.get(query);
		String key = getQueryKey(query, params);
		if (set != null && set.contains(key) && cacheService.containsKey(key)) {
			Object result = cacheService.get(key);
			if (result != null) {
				hits++;
			}
			return result;
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public void putQuery(Class clazz, String query, Object[] params, 
			Object value) {
		Map<String, Set<String>> map = getQueryMap(clazz);
		Set<String> set = map.get(query);
		String key = getQueryKey(query, params);
		if (set == null) {
			set = new HashSet<String>();
			map.put(query, set);
		}
		set.add(key);
		cacheService.put(key, value);
		updateQueryMap(clazz, map);
	}

	
	@SuppressWarnings("unchecked")
	public void removeQueries(Class clazz) {
		Map<String, Set<String>> map = getQueryMap(clazz);
		updateQueryMap(clazz, new HashMap<String, Set<String>>());
		for (String query : map.keySet()) {
			Set<String> set = map.get(query);
			if (set != null) {
				removeQueries(set);
			}
		}
	}

	private void removeQueries(Set<String> set) {
		for (String key : set) {
			if (cacheService.containsKey(key)) {
				cacheService.remove(key);
			}
		}
	}

	
	public CacheStat getStat() {
		return new CacheStat(calls, hits);
	}
}
