package com.babc.server.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.Paging;
import com.babc.server.model.UserEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("userDao")
public class UserDao extends AbstractBaseDao{
	
	@Autowired
	public UserDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, UserEntity.class);
	}
	
	/*
	public UserEntity add(UserEntity category){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserEntity response = null;
		try {
			response = pm.makePersistent(category);
		} finally {
			pm.close();
		}
		return response;
	}
	
	public UserEntity get(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return pm.getObjectById(UserEntity.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> get(Paging paging) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(UserEntity.class);
		setPagingParameterValues(query, paging);
		List<UserEntity> results = null;
		try {
			results = (List<UserEntity>) query.execute();
		} finally {
			query.closeAll();
		}
		return results;
	}
	
	public void delete(Long id){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.deletePersistent(pm.getObjectById(UserEntity.class, id));
	}
	*/
}
