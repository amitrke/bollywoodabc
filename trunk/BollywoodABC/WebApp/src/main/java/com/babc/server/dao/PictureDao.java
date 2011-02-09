package com.babc.server.dao;

import static com.google.appengine.api.datastore.Query.FilterOperator.EQUAL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
@Repository("pictureDao")
public class PictureDao extends AbstractBaseDao<PictureEntity> {
	
	/**
	 * @param entityCache
	 * @param queryCache
	 * @param clazz
	 */
	@Autowired
	public PictureDao(EntityCache entityCache, QueryCache queryCache) {
		super(entityCache, queryCache, PictureEntity.class);
	}
	
	@Override
	protected Query newQuery() {
		Query query = super.newQuery();
		query.addFilter("status", EQUAL, AppConstants.ENTITY_STATUS_ENABLED);
		query.addSort("id", SortDirection.DESCENDING);
		return query;
	}	
	
	public List<PictureEntity> getAll(Paging paging) {
		Query q = super.newQuery();
		List<PictureEntity> result = select(q, "getAllPictures", params(paging.getLimit(), 
				paging.getOffset()), paging.getLimit(), paging.getOffset());
		return result;
	}
	
	private void setThumbData(PictureEntity pictureEntity){
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		Image oldImage = ImagesServiceFactory.makeImage(pictureEntity.getData().getBytes());
		Transform resize = ImagesServiceFactory.makeResize(AppConstants.IMAGE_THUMB_X, AppConstants.IMAGE_THUMB_Y);
		Image newImage = imagesService.applyTransform(resize, oldImage);
		pictureEntity.setThumbData(new Blob(newImage.getImageData()));
	}
	
	@Override
	public PictureEntity save(PictureEntity pictureEntity) {
		if (pictureEntity.getThumbData() == null){ //Create Thumbnail
			setThumbData(pictureEntity);
		}
		return super.save(pictureEntity);
	}
}
