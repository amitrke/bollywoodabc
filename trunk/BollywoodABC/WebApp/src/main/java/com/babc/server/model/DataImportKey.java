package com.babc.server.model;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import static com.babc.server.utils.EntityUtil.getLongProperty;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DataImportKey extends BaseEntityImpl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent
    private Long importId;
	
	@Persistent
	private Long currentSystemId;

	public DataImportKey(Long importId, Long currentSystemId) {
		this.importId = importId;
		this.currentSystemId = currentSystemId;
	}
	
	public DataImportKey() {
	}


	@Override
	public void setKey(Key key) {
		super.setKey(key);
		importId = key.getId();
	}


	@Override
	public void save(Entity entity) {
		super.save(entity);
	}


	@Override
	public void load(Entity entity) {
		super.load(entity);
		currentSystemId = getLongProperty(entity, "currentSystemId", 1L);
	}


	public Long getImportId() {
		return importId;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public Long getCurrentSystemId() {
		return currentSystemId;
	}

	public void setCurrentSystemId(Long currentSystemId) {
		this.currentSystemId = currentSystemId;
	}

}
