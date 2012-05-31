package com.babc.server.web.soap;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.context.ApplicationContext;

import com.babc.server.model.StoryEntity;
import com.babc.server.model.TweetEntity;
import com.babc.server.model.vo.TweetsVo;
import com.babc.server.service.TagService;
import com.babc.server.web.soap.entity.Story;

@WebService
public class Soap {
	
	private TagService tagService;
	
	public Soap() {
	}

	public Soap(ApplicationContext context) {
		tagService = (TagService) context.getBean("tagService");
	}

	@WebMethod
	public String version(){
		return "1.0";
	}
	
	@WebMethod
	public List<Story> getRelatedStories(Long tagId){
		List<StoryEntity> storyEntities = tagService.getRelatedStories(tagId);
		if (storyEntities!=null && storyEntities.size() > 0){
			List<Story> stories = new ArrayList<Story>();
			for(StoryEntity storyEntity: storyEntities){
				stories.add(new Story(storyEntity));
			}
			return stories;
		}
		return null;
	}
	
	@WebMethod
	public List<String> getTweets(Long tagId){
		TweetsVo tweetsVo = tagService.getTwitterDetails(tagId);
		List<String> tweets = new ArrayList<String>();
		for (TweetEntity tweet: tweetsVo.getTweets()){
			tweets.add(tweet.getText());
		}
		return tweets;
	}
}
