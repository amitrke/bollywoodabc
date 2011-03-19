package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.CategoryDao;
import com.babc.server.dao.StoryDao;
import com.babc.server.dao.TagCrossRefDao;
import com.babc.server.dao.TagDao;
import com.babc.server.model.CategoryEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.TagEntity;
import com.babc.server.model.vo.TagVo;

@Service("tagService")
public class TagService {
	
	private @Autowired TagDao tagDao;
	private @Autowired TagCrossRefDao tagCrossRefDao;
	private @Autowired CategoryDao categoryDao;
	private @Autowired StoryDao storyDao;
	
	/**
	 * Get tags
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public List<TagVo> getTags(Long entityId, int entityType){
		List<TagCrossRefEntity> tagCrossRefEntities = tagCrossRefDao.get(entityId, entityType);
		List<TagVo> tags = new ArrayList<TagVo>();
		TagVo tagVo = null;
		TagEntity tag = null;
		for(TagCrossRefEntity crossRefEntity: tagCrossRefEntities){
			tag = tagDao.getById(crossRefEntity.getTagId());
			tagVo = new TagVo(tag.getType(), tag.getId(), tag.getDescription(), tag.getTag());
			setRelatedPhotogalleries(tagVo);
			setRelatedStories(tagVo, entityId);
			tags.add(tagVo);
		}
		return tags;
	}
	
	/**
	 * Get tags
	 * @param entityId
	 * @return
	 */
	public TagVo getTagDetails(Long tagId){
		TagEntity tagEntity = tagDao.getById(tagId);
		TagVo tagVo = null;
		tagVo = new TagVo(tagEntity.getType(), tagEntity.getId(), tagEntity.getDescription(),
				tagEntity.getTag());
		setRelatedPhotogalleries(tagVo);
		setRelatedStories(tagVo, 0L);
		return tagVo;
	}
	
	public List<TagVo> getTags(Paging paging){
		List<TagCrossRefEntity> tagCrossRefEntities = tagCrossRefDao.get(paging);
		List<TagVo> tags = new ArrayList<TagVo>();
		TagVo tagVo = null;
		TagEntity tag = null;
		for(TagCrossRefEntity crossRefEntity: tagCrossRefEntities){
			tag = tagDao.getById(crossRefEntity.getTagId());
			if (tag==null){
				tagVo = new TagVo(0, crossRefEntity.getEntityType(),0L, 
						crossRefEntity.getEntityId(), "", "");
			}
			else{
				tagVo = new TagVo(tag.getType(), crossRefEntity.getEntityType(), 
					crossRefEntity.getEntityId(), tag.getId(), tag.getDescription(), tag.getTag());
			}
			tags.add(tagVo);
		}
		return tags;
	}
	
	private void setRelatedPhotogalleries(TagVo tag){
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.PHOTOGALLERY);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			categoryEntities.add(categoryDao.getById(crossRefEntity.getEntityId()));
		}
		tag.setRelatedPhotogalleries(categoryEntities);
	}
	
	private void setRelatedStories(TagVo tag, Long storyToSkip){
		List<StoryEntity> relatedStories = new ArrayList<StoryEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.STORY);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			StoryEntity storyEntity = storyDao.getById(crossRefEntity.getEntityId());
			if (!relatedStories.contains(storyEntity) &&  storyEntity.getId()!=storyToSkip ){
				relatedStories.add(storyEntity);
			}
		}
		tag.setRelatedStories(relatedStories);
	}
	
	/**
	 * Get list of tags
	 * @param paging
	 * @return
	 */
	public List<TagEntity> getTagList(Paging paging){
		return tagDao.get(paging);
	}
}
