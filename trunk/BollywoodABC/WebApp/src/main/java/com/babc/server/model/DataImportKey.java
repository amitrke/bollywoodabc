package com.babc.server.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DataImportKey {
	
	@PrimaryKey
    @Persistent
    private Long importId;
	
	@Persistent
	private Long currentSystemId;

	public DataImportKey(Long importId, Long currentSystemId) {
		this.importId = importId;
		this.currentSystemId = currentSystemId;
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
