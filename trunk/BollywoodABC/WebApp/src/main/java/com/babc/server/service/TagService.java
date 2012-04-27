package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.AppConstants;
import com.babc.server.dao.CategoryDao;
import com.babc.server.dao.PageCacheDao;
import com.babc.server.dao.PictureDao;
import com.babc.server.dao.StoryDao;
import com.babc.server.dao.TagCrossRefDao;
import com.babc.server.dao.TagDao;
import com.babc.server.model.CategoryEntity;
import com.babc.server.model.PageType;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.TagEntity;
import com.babc.server.model.vo.TagListVo;
import com.babc.server.model.vo.TagVo;
import com.babc.server.model.vo.TweetsVo;
import com.babc.server.web.admin.model.KeyValuePair;

@Service("tagService")
public class TagService {
	
	private @Autowired TagDao tagDao;
	private @Autowired TagCrossRefDao tagCrossRefDao;
	private @Autowired CategoryDao categoryDao;
	private @Autowired StoryDao storyDao;
	private @Autowired PictureDao pictureDao;
	private @Autowired TwitterService twitterService;
	private @Autowired PageCacheDao pageCacheDao;
	
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
			setFacePic(tagVo);
			setRelatedPhotogalleries(tagVo);
			setRelatedStories(tagVo, entityId);
			setRelatedPictures(tagVo);
			tags.add(tagVo);
		}
		return tags;
	}
	
	public TagListVo getTagsList(Long entityId, int entityType){
		return new TagListVo(getTags(entityId, entityType));
	}
	
	public List<Long> getTagIds(Long entityId, int entityType){
		List<TagCrossRefEntity> tagCrossRefEntities = tagCrossRefDao.get(entityId, entityType);
		List<Long> tags = new ArrayList<Long>();
		for(TagCrossRefEntity crossRefEntity: tagCrossRefEntities){
			tags.add(crossRefEntity.getTagId());
		}
		return tags;
	}
	
	public TagCrossRefEntity saveCrossRef(TagCrossRefEntity crossRefEntity){
		List<TagCrossRefEntity> existingRecs = tagCrossRefDao.getByTagIdEntIdEntTyp(crossRefEntity.getTagId(), 
				crossRefEntity.getEntityId(), crossRefEntity.getEntityType());
		TagCrossRefEntity refEntity;
		if (existingRecs!=null && existingRecs.size()<1){
			
			//Remove related cache
			Long pageId = crossRefEntity.getTagId();
			String mostSigDigits = Integer.toString(PageType.TAG_DETAIL.ordinal()*10);
			String restOfTheDigits = pageId.toString();
			Long pageCacheId = Long.parseLong(mostSigDigits+restOfTheDigits);
			pageCacheDao.remove(pageCacheId);
			
			refEntity = tagCrossRefDao.save(crossRefEntity);
		}
		else{
			refEntity = existingRecs.get(0);
		}
		return refEntity;
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
		setFacePic(tagVo);
		setRelatedPictures(tagVo);
		tagVo.setTweetsVo(getTwitterDetails(tagId));
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
	
	public void setFacePic(TagVo tag){
		List<TagCrossRefEntity> facePics = tagCrossRefDao.getByTagId(AppConstants.TAG_FACEPIC, 
				TagCrossRefEntity.PICTURE, Integer.MAX_VALUE);
		List<TagCrossRefEntity> tagPics = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.PICTURE, Integer.MAX_VALUE);
		
		for(TagCrossRefEntity facePic: facePics){
			for(TagCrossRefEntity tagPic: tagPics){
				if (facePic.getEntityId().equals(tagPic.getEntityId())){
					tag.setFacePic(facePic.getEntityId());
					break;
				}
				if (tag.getFacePic()!=null) break;
			}
		}
	}
	
	private List<TagCrossRefEntity> getFacePics(){
		List<TagCrossRefEntity> intersectedResult = new ArrayList<TagCrossRefEntity>();
		List<TagCrossRefEntity> facePics = tagCrossRefDao.getByTagId(AppConstants.TAG_FACEPIC, 
				TagCrossRefEntity.PICTURE, Integer.MAX_VALUE);
		List<TagCrossRefEntity> tagPics = tagCrossRefDao.getByTagType(TagCrossRefEntity.PICTURE, 
				Integer.MAX_VALUE);
		for(TagCrossRefEntity facePic: facePics){
			for (TagCrossRefEntity tagPic: tagPics){
				if (facePic.getEntityId().equals(tagPic.getEntityId()) && !tagPic.getTagId().equals(AppConstants.TAG_FACEPIC)){
					intersectedResult.add(tagPic);
					break;
				}
			}
		}
		
		return intersectedResult;
	}
	
	public List<KeyValuePair> getFacePicsAsKeyVal(){
		List<KeyValuePair> keyValuePairs = new ArrayList<KeyValuePair>();
		List<TagCrossRefEntity> facePics = getFacePics();
		for(TagCrossRefEntity facePic: facePics){
			TagEntity tagEntity = tagDao.getById(facePic.getTagId());
			keyValuePairs.add(new KeyValuePair(tagEntity.getId(), tagEntity.getTag()+"#"+facePic.getEntityId()));
		}
		return keyValuePairs;
	}
	
	public List<KeyValuePair> getTagsAsKeyVal(){
		List<KeyValuePair> keyValuePairs = new ArrayList<KeyValuePair>();
		List<TagEntity> tagEntities = tagDao.get(new Paging(Integer.MAX_VALUE, 0));
		for(TagEntity tagEntity: tagEntities){
			keyValuePairs.add(new KeyValuePair(tagEntity.getId(), tagEntity.getTag()));
		}
		return keyValuePairs;
	}
	
	private void setRelatedPhotogalleries(TagVo tag){
		List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.PHOTOGALLERY, 5);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			categoryEntities.add(categoryDao.getById(crossRefEntity.getEntityId()));
		}
		tag.setRelatedPhotogalleries(categoryEntities);
	}
	
	private Long getTwitterUserId(Long tagid){
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tagid, 
				TagCrossRefEntity.TWITTER_USER, 1);
		if (crossRefEntities.size()>0){
			return crossRefEntities.get(0).getEntityId();
		}
		else{
			return null;
		}
	}
	
	public TweetsVo getTwitterDetails(Long tagId){
		Long twitterUserId = getTwitterUserId(tagId);
		if (twitterUserId==null){
			return null;
		}
		return twitterService.getUserTweets(twitterUserId);
	}
	
	private void setRelatedPictures(TagVo tag){
		List<PictureEntity> pictureEntities = new ArrayList<PictureEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.PICTURE, AppConstants.RELATED_PICS_LIMIT);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			if (tag.getFacePic()!=null && crossRefEntity.getEntityId().equals(tag.getFacePic())){
				continue;
			}
			if (pictureEntities.size() >= 15){
				break;
			}
			pictureEntities.add(pictureDao.getById(crossRefEntity.getEntityId()));
		}
		tag.setRelatedPictures(pictureEntities);
	}
	
	private void setRelatedStories(TagVo tag, Long storyToSkip){
		List<StoryEntity> relatedStories = new ArrayList<StoryEntity>();
		List<TagCrossRefEntity> crossRefEntities = tagCrossRefDao.getByTagId(tag.getTagId(), 
				TagCrossRefEntity.STORY, 10);
		
		for(TagCrossRefEntity crossRefEntity: crossRefEntities){
			StoryEntity storyEntity = storyDao.getById(crossRefEntity.getEntityId());
			if (!relatedStories.contains(storyEntity) &&  storyEntity!=null && 
					storyEntity.getId()!=storyToSkip ){
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
