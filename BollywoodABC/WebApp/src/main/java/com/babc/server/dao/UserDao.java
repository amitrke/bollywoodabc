package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.UserEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;
@Repository("userDao")
public class UserDao extends AbstractBaseDao<UserEntity>{
	
	@Autowired
	public UserDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, UserEntity.class);
	}

	@Override
	protected Query newQuery() {
		return super.newQuery();
	}
	
	public UserEntity getByEmail(String email){
		Query query = newQuery();
		query.addFilter("email", EQUAL, email);
		return selectOne(query, "selectUserByEmailId", params(email));
	}
}
