package com.babc.server.model;

import com.babc.server.utils.AppUtils;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class BaseEntityImpl implements BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Key key;
	
	@Override
	public Key getKey() {
		return key;
	}

	@Override
	public void setKey(Key key) {
		this.key = key;

	}

	@Override
	public Long getId() {
		return key == null ? null : key.getId();
	}

	@Override
	public void setId(Long id) {
		if (id != null && id > 0) {
			key = KeyFactory.createKey(AppUtils.getKind(getClass()), id);
		}
		else {
			key = null;
		}

	}

	@Override
	public void save(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(Entity entity) {
		key = entity.getKey();
	}

	@Override
	public boolean isNew() {
		return key == null;
	}

	@Override
	public void copy(BaseEntity entity) {
		Key myKey = getKey(); 
		Entity buf = new Entity("tmp");
		entity.save(buf);
		load(buf);
		setKey(myKey);
	}
}