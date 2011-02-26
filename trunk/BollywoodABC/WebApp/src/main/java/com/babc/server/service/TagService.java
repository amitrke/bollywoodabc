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
			setRelatedStories(tagVo);
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
	
	private void setRelatedStories(TagVo tag){
		List<StoryEntity> relatedStories = new ArrayList<StoryEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.STORY);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			relatedStories.add(storyDao.getById(crossRefEntity.getEntityId()));
		}
		tag.setRelatedStories(relatedStories);
	}
}
