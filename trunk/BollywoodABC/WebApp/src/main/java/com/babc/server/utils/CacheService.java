package com.babc.server.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.cache.Cache;
import javax.cache.CacheEntry;
import javax.cache.CacheException;
import javax.cache.CacheListener;
import javax.cache.CacheManager;
import javax.cache.CacheStatistics;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("cacheService")
public class CacheService implements Cache {
	
	private transient static final Logger LOGGER = Logger.getLogger(CacheService.class.getName());
	private static final long LOCAL_CACHE_TTL = 5000;
	
	private Cache cache;
	private Map<String, Object> localCache;
	private long localCacheTime;
	private int localHits;
	private int cacheHits;

	public CacheService() {
		try {
            cache = CacheManager.getInstance().getCacheFactory().createCache(
            		Collections.emptyMap());
        } catch (CacheException e) {
            LOGGER.error("Can't init cache manager. " + e.getMessage());
        }
        localCache = new HashMap<String, Object>();
        localCacheTime = System.currentTimeMillis();
	}
	
	
	public void resetLocalCache() {
		if (System.currentTimeMillis() - localCacheTime > LOCAL_CACHE_TTL) {
			localCache.clear(); 
	        localCacheTime = System.currentTimeMillis();
		}
	}

	
	public void addListener(CacheListener arg0) {
		LOGGER.error("addListener(CacheListener arg0) not implemented");		
	}

	
	public void evict() {
		LOGGER.error("evict() not implemented");		
	}

	
	@SuppressWarnings("unchecked")
	public Map getAll(Collection arg0) {
		LOGGER.error("getAll(Collection arg0) not implemented");		
		return null;
	}

	
	public CacheEntry getCacheEntry(Object arg0) {
		LOGGER.error("getCacheEntry(Object arg0) not implemented");		
		return null;
	}

	
	public CacheStatistics getCacheStatistics() {
		LOGGER.error("getCacheStatistics() not implemented");		
		return null;
	}

	
	public void load(Object arg0) {
		LOGGER.error("load(Object arg0) not implemented");		
	}

	
	@SuppressWarnings("unchecked")
	public void loadAll(Collection arg0) {
		LOGGER.error("loadAll(Collection arg0) not implemented");		
	}

	
	public Object peek(Object arg0) {
		LOGGER.error("peek(Object arg0) not implemented");		
		return null;
	}

	
	public void removeListener(CacheListener arg0) {
		LOGGER.error("removeListener(CacheListener arg0) not implemented");		
	}

	
	public void clear() {
		localCache.clear();
		cache.clear();		
	}

	
	public boolean containsKey(Object arg0) {
		if (localCache.containsKey(arg0)) {
			return true;
		}
		return cache.containsKey(arg0);
	}

	
	public boolean containsValue(Object arg0) {
		LOGGER.error("containsValue(Object arg0) not implemented");		
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public Set entrySet() {
		LOGGER.error("entrySet() not implemented");		
		return null;
	}

	
	public Object get(Object arg0) {
		if (localCache.containsKey(arg0)) {
			localHits++;
			return localCache.get(arg0);
		}
		Object value = cache.get(arg0);
		if (value != null) {
			cacheHits++;
			localCache.put((String)arg0, value);
		}
		return value;
	}

	
	public boolean isEmpty() {
		LOGGER.error("isEmpty() not implemented");		
		return false;
	}

	
	@SuppressWarnings("unchecked")
	public Set keySet() {
		LOGGER.error("keySet() not implemented");		
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public Object put(Object arg0, Object arg1) {
		localCache.put((String)arg0, arg1);
		return cache.put(arg0, arg1);
	}

	
	@SuppressWarnings("unchecked")
	public void putAll(Map arg0) {
		LOGGER.error("putAll(Map arg0) not implemented");		
	}

	
	public Object remove(Object arg0) {
		localCache.remove(arg0);
		return cache.remove(arg0);
	}

	
	public int size() {
		LOGGER.error("size() not implemented");		
		return 0;
	}

	
	@SuppressWarnings("unchecked")
	public Collection values() {
		LOGGER.error("values() not implemented");		
		return null;
	}

	
	public int getLocalHits() {
		return localHits;
	}

	public int getCacheHits() {
		return cacheHits;
	}
}
