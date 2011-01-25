package com.babc.server.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public interface BaseEntity extends Serializable {
	
	Key getKey();

	void setKey(Key key);
	
	Long getId();

	void setId(Long id);
	
	void save(Entity entity);
	
	void load(Entity entity);
	
	boolean isNew();
	
	/**
	 * Copy all fields from entity.
	 * @param entity - data to copy from.
	 */
	void copy(BaseEntity entity);
}
