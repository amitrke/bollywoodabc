package com.babc.server.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;

public class TagListVo {
	
	List<TagVo> tags;

	public TagListVo(List<TagVo> tags) {
		this.tags = tags;
	}

	public List<TagVo> getTags() {
		return tags;
	}
	
	public List<PictureEntity> getRelatedPictures(){
		List<PictureEntity> pictureEntities = new ArrayList<PictureEntity>();
		for (TagVo tag:tags){
			for (PictureEntity pictureEntity:tag.getRelatedPictures()){
				if (!pictureEntities.contains(pictureEntity)){
					pictureEntities.add(pictureEntity);
				}
			}
		}
		return pictureEntities;
	}
	
	public List<StoryEntity> getRelatedStories(){
		List<StoryEntity> storyEntities = new ArrayList<StoryEntity>();
		for (TagVo tag:tags){
			for (StoryEntity storyEntity:tag.getRelatedStories()){
				if (!storyEntities.contains(storyEntity)){
					storyEntities.add(storyEntity);
				}
			}
		}
		return storyEntities;
	}
	
	public String tagCsv(){
		String tagCsv = "";
		for(TagVo tag:tags){
			tagCsv += tag.getTag()+",";
		}
		return tagCsv;
	}
}
