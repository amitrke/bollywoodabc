/**
 * 
 */
package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.Photogallery;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Query;

/**
 * @author amit
 *
 */
@Repository("photogalleryDao")
public class PhotogalleryDao extends AbstractBaseDao<Photogallery> {
	
	@Autowired
	public PhotogalleryDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, Photogallery.class);
	}

	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		//query.addFilter("status", EQUAL, AppConstants.ENTITY_STATUS_ENABLED);
		//query.addSort("id", SortDirection.DESCENDING);
		return query;
	}
	
	/**
	 * Get Photogallery by Category (Album Id).
	 * @param category
	 * @param paging
	 * @return
	 */
	public List<Photogallery> get(Long category, Paging paging){
		Query q = newQuery();
		q.addFilter("categoryId",EQUAL, category);
		List<Photogallery> result = select(q, "getPhotogalleryByCategory", params(paging.getLimit(), 
				paging.getOffset(), category), paging.getLimit(), paging.getOffset());
		return result;
	}
	
	
}
