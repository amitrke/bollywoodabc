package com.babc.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.model.DataImportKey;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Repository("importDataDao")
public class ImportDataDao extends AbstractBaseDao<DataImportKey> {
	
	@Autowired
	public ImportDataDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, DataImportKey.class);
	}
	
}
