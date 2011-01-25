package com.babc.server.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.CommentEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.SortOrder;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("commentDao")
public class CommentDao extends AbstractBaseDao {
	
	@Autowired
	public CommentDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, CommentEntity.class);
	}
/*
	public CommentEntity add(CommentEntity category){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CommentEntity response = null;
		try {
			response = pm.makePersistent(category);
		} finally {
			pm.close();
		}
		return response;
	}
	
	public CommentEntity get(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return pm.getObjectById(CommentEntity.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentEntity> get(Paging paging) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(CommentEntity.class);
		setPagingParameterValues(query, paging);
		List<CommentEntity> results = null;
		try {
			results = (List<CommentEntity>) query.execute();
		} finally {
			query.closeAll();
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<CommentEntity> get(Paging paging, Long storyId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(CommentEntity.class);
		paging.setSortColumn("postedDate");
		paging.setSortOrder(SortOrder.desc);
		setPagingParameterValues(query, paging);
		query.setFilter("storyId==idParam");
		query.declareParameters("Long idParam");
		List<CommentEntity> results = null;
		try {
			results = (List<CommentEntity>) query.execute(storyId);
		} finally {
			query.closeAll();
		}
		return results;
	}
	
	public void delete(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(CommentEntity.class, id));
	}
	*/
}
