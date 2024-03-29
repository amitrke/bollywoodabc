package com.babc.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.AppConstants;
import com.babc.server.dao.ImportDataDao;
import com.babc.server.dao.PageCacheDao;
import com.babc.server.dao.PictureDao;
import com.babc.server.dao.StoryDao;
import com.babc.server.model.DataImportKey;
import com.babc.server.model.PageCacheEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.UserEntity;
import com.babc.server.model.vo.CategoryVo;
import com.babc.server.model.vo.StoryVo;
import com.babc.server.model.vo.TagVo;
import com.babc.server.model.vo.TweetsVo;
import com.babc.server.utils.EntityCache;

@Service("storyService")
public class StoryService {
	
	private @Autowired StoryDao storyDao;
	private @Autowired PictureDao pictureDao;
	private @Autowired CategoryService categoryService;
	private @Autowired TagService tagService;
	private @Autowired ImportDataDao importDataDao;
	private @Autowired EntityCache entityCache;
	private @Autowired PageCacheDao pageCacheDao;
	
	/**
	 * Gets the total number of stores in the database.
	 * 
	 * @return
	 */
	public Integer getCount(){
		return storyDao.getCount(categoryService.getEntityList(new Paging()));
	}
	
	public StoryEntity add(StoryVo storyVo){
		
		if (storyVo.getPicture().getData() != null){ //New image.
			storyVo.setPicture(pictureDao.save(storyVo.getPicture()));
		}
		
		StoryEntity storyEntity = new StoryEntity(storyVo);
		storyEntity = storyDao.save(storyEntity);
		
		//Remove related cache
		pageCacheDao.remove(AppConstants.HOME_PAGE_PAGECACHE_ID);
		entityCache.removeEntities(PageCacheEntity.class);
		
		return storyEntity;
	}
	
	public StoryVo get(Long id, Paging commentPaging){
		StoryEntity storyEntity = get(id);
		return entityToVo(storyEntity, commentPaging);
	}
	
	public DataImportKey addDataImportKey(DataImportKey dataImportKey){
		return storyDao.addDataImportKey(dataImportKey);
	}
	
	public Long getImportOldToNewKey(Long oldKey){
		return importDataDao.getById(oldKey).getCurrentSystemId();
	}
	
	public StoryEntity get(Long id){
		return storyDao.getById(id);
	}
	
	public List<StoryEntity> get(Paging paging){
		return storyDao.get(paging);
	}
	
	public List<StoryVo> getByCategory(Long category, Paging paging){
		List<StoryVo> storyVos = new ArrayList<StoryVo>();
		List<StoryEntity> storyEntities = storyDao.get(category, paging);
		for(StoryEntity storyEntity: storyEntities){
			storyVos.add(entityToVoOnlyImages(storyEntity));
		}
		return storyVos;
	}
	
	public List<StoryVo> get(Paging storyPaging, Paging commentPaging){
		List<StoryVo> storyVos = new ArrayList<StoryVo>();
		List<StoryEntity> storyEntities = storyDao.get(storyPaging);
		for(StoryEntity storyEntity: storyEntities){
			storyVos.add(entityToVo(storyEntity, commentPaging));
		}
		return storyVos;
	}
	
	public List<StoryVo> getStoryAndImages(Paging storyPaging){
		List<StoryVo> storyVos = new ArrayList<StoryVo>();
		List<StoryEntity> storyEntities = storyDao.get(storyPaging);
		for(StoryEntity storyEntity: storyEntities){
			storyVos.add(entityToVoOnlyImages(storyEntity));
		}
		return storyVos;
	}
	
	public void delete(Long id){
		storyDao.remove(id);
	}
	
	private StoryVo entityToVo(StoryEntity storyEntity, Paging commentPaging){
		CategoryVo categoryVo = categoryService.get(storyEntity.getCategoryId());
		//TODO: Fix this later once I have users getting created.
		//UserEntity userEntity = userDao.get(storyEntity.getAuthorId());
		UserEntity userEntity = new UserEntity("Amit Kumar", "amitrke@gmail.com");
		PictureEntity pictureEntity = pictureDao.getById(storyEntity.getPictureId());
		List<TagVo> tags = tagService.getTags(storyEntity.getId(), TagCrossRefEntity.STORY);
		StoryVo storyVo = new StoryVo(storyEntity,categoryVo, userEntity, pictureEntity, tags);
		if (tags.size() > 0){
			List<TweetsVo> tweetsVos = new ArrayList<TweetsVo>();
			for(TagVo tag: tags){
				tweetsVos.add(tagService.getTwitterDetails(tag.getTagId()));
			}
			storyVo.setTweetsVos(tweetsVos);
		}
		return storyVo;
	}
	
	private StoryVo entityToVoOnlyImages(StoryEntity storyEntity){
		UserEntity userEntity = new UserEntity("Amit Kumar", "amitrke@gmail.com");
		PictureEntity pictureEntity = pictureDao.getById(storyEntity.getPictureId());
		StoryVo storyVo = new StoryVo(storyEntity,null, userEntity, pictureEntity, null);
		return storyVo;
	}
	
	public List<StoryVo> get(Date startDate, Date endDate){
		List<StoryEntity> storyEntities = storyDao.get(startDate, endDate);
		List<StoryVo> storyVos = new ArrayList<StoryVo>();
		for(StoryEntity storyEntity: storyEntities){
			storyVos.add(entityToVoOnlyImages(storyEntity));
		}
		return storyVos;
	}
	
	public List<StoryEntity> getLite(Date startDate, Date endDate){
		return storyDao.getAll(startDate, endDate);
	}
}
