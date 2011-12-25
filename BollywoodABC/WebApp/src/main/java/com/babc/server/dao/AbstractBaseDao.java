package com.babc.server.dao;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.babc.server.model.BaseEntity;
import com.babc.server.model.Paging;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public abstract class AbstractBaseDao<T extends BaseEntity> {
	
	protected static final Log logger = LogFactory.getLog(AbstractBaseDao.class);

	private EntityCache entityCache;
	private QueryCache queryCache;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;
	private String kind;
	
	/**
	 * @param entityCache
	 * @param queryCache
	 * @param clazz
	 */
	@SuppressWarnings("rawtypes")
	public AbstractBaseDao(EntityCache entityCache, QueryCache queryCache,
			Class clazz) {
		super();
		this.entityCache = entityCache;
		this.queryCache = queryCache;
		this.clazz = clazz;
		kind = createKind();
	}
	
	public T save(T model) {
		
		getQueryCache().removeQueries(clazz);
		Entity entity = null;
		if (model.getId() != null) {
			try {
				DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
				entity = datastoreService.get(getKey(model.getId()));
				getEntityCache().removeEntity(clazz, model.getId());
			}
			catch (EntityNotFoundException e) {
				logger.error("Entity not found " + clazz.getName() + " " 
						+ model.getId());
			}
		}
		if (entity == null) {
			if (model.getId()!=null){
				entity = new Entity(getKind(), model.getId());
			}
			else{
				entity = new Entity(getKind());
			}
		}
		model.save(entity);
		DatastoreServiceFactory.getDatastoreService().put(entity);
		model.setKey(entity.getKey());
		logger.debug("Object persisted: "+model.toString());
		return model;
	}
	
	public void clearCache() {
		try {
			queryCache.removeQueries(clazz);
		}
		catch (Exception e) {
			logger.error("clearCache " + clazz.getName() + " " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		
		if (id == null || id <= 0) {
			return null;
		}
		T model = (T) entityCache.getEntity(clazz, id);
		if (model != null) {
			return model;
		}
		try {
			DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
			model = createModel(datastoreService.get(getKey(id)));
			entityCache.putEntity(clazz, id, model);
			return model;
		}
		catch (EntityNotFoundException e) {
			return null;
		}
	}
	
	public void remove(Long id) {
		if (id == null) {
			return;
		}
		getEntityCache().removeEntity(clazz, id);
		getQueryCache().removeQueries(clazz);
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		datastoreService.delete(getKey(id));
	}
	
	public void remove(List<Long> ids) {
		for (Long id : ids) {
			getEntityCache().removeEntity(clazz, id);
		}
		getQueryCache().removeQueries(clazz);
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		datastoreService.delete(getKeys(ids));
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> select(Query query, String queryId, Object[] params, int limit, int offset) {
		List<T> result = (List<T>) getQueryCache().getQuery(clazz, queryId, 
				params);
		if (result == null) {
			logger.debug("Query getting executed: "+query);
			DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
			PreparedQuery p = datastoreService.prepare(query);
			result = createModels(p.asList(withLimit(limit).offset(offset)));
			getQueryCache().putQuery(clazz, queryId, params, result);
		}
		return result;
	}
	
	protected List<T> selectNotCache(Query query) {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery p = datastoreService.prepare(query);
		int limit = p.countEntities() > 0 ? p.countEntities() : 1;
		List<T> result = createModels(p.asList(withLimit(limit)));
		return result;
	}
	
	public List<T> get(Paging paging) {
		return select(newQuery(), "getAll-"+kind, params(paging.getLimit(), 
				paging.getOffset()), paging.getLimit(), paging.getOffset());
	}
	
	protected T selectOne(Query query, String queryId, Object[] params) {
		List<T> list = select(query, queryId, params, 10, 0);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() > 1) {
			logger.error("May be consistency error. Multiple result for select one query " 
					+ clazz.getName() + " " + queryId + params.toString());
		}
		return list.get(0);
	}
	
	private List<T> createModels(Collection<Entity> list) {
		List<T> result = new ArrayList<T>();
		for (Entity entity : list) {
			result.add(createModel(entity));
		}
		return result;
	}
	
	public List<Key> getKeys(List<Long> ids) {
		List<Key> result = new ArrayList<Key>();
		for (Long id : ids) {
			result.add(getKey(id));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private T createModel(Entity entity) {
		try {
			T model = (T)clazz.newInstance();
			model.load(entity);
			return model;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Key getKey(Long id) {
		return KeyFactory.createKey(kind, (Long)id);
	}
	
	protected Object[] params(Object...objects) {
		return objects;
	}
	
	protected Query newQuery() {
		return new Query(getKind());
	}
	
	protected void setPagingParameterValues(Query query, Paging paging){
		//query.setRange(paging.getOffset(), (paging.getOffset() + paging.getLimit()));
	}

	public EntityCache getEntityCache() {
		return entityCache;
	}

	public QueryCache getQueryCache() {
		return queryCache;
	}

	public Class getClazz() {
		return clazz;
	}

	public String getKind() {
		return kind;
	}
	
	private String createKind() {
		String name = clazz.getName();
		return name.substring(name.lastIndexOf('.') + 1);
	}
}
