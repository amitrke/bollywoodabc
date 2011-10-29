package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.TagCrossRefDao;
import com.babc.server.dao.TweetsDao;
import com.babc.server.dao.TwitterUserDetailDao;
import com.babc.server.model.Paging;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.TweetEntity;
import com.babc.server.model.TwitterUserDetailEntity;
import com.babc.server.model.vo.TagVo;
import com.babc.server.model.vo.TweetListVo;
import com.babc.server.model.vo.TweetsVo;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service("twitterService")
public class TwitterService {
	
	private @Autowired TweetsDao tweetsDao;
	private @Autowired TwitterUserDetailDao twitterUserDetailDao;
	private @Autowired TagService tagService;
	private @Autowired TagCrossRefDao tagCrossRefDao;
	
	protected static final Log logger = LogFactory.getLog(TwitterService.class);
	
	private Twitter getTwitterInstance(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("SV4Us4oWLvJZ8uOb8vt66A")
		  .setOAuthConsumerSecret("EI3VQATcq5MF9mupq3PVHOeq49gr6bnilh7KJFad0dY")
		  .setOAuthAccessToken("162978861-vj1vCzgKF4PdZMspzJAt6wEJXywq3oGQaT3c4")
		  .setOAuthAccessTokenSecret("SrMqtTiEjbN3AVp2YhdBfvn7guHai3NZkiFxIpnjDA8");
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
	
	public TweetsVo getUserTweets(Long userId){
		return new TweetsVo(twitterUserDetailDao.getById(userId), 
				tweetsDao.getByUserId(userId, new Paging(10, 0)));
	}
	
	public boolean post(String message){
		Twitter twitter = getTwitterInstance();
	    Status status = null;
		try {
			status = twitter.updateStatus(message);
			logger.info("Successfully updated twitter status to [" + status.getText() + "].");
			return true;
		} catch (TwitterException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public List<TweetListVo> getTweetList(Paging paging){
		List<TweetListVo> tweetListVos = new ArrayList<TweetListVo>();
		List<TweetEntity> tweetEntities = tweetsDao.get(paging);
		for(TweetEntity tweetEntity: tweetEntities){
			TwitterUserDetailEntity twitterUserDetailEntity = twitterUserDetailDao.getById(tweetEntity.getUserId());
			List<TagCrossRefEntity> tagCrossRefEntities = tagCrossRefDao.getByEntIdEntTyp(tweetEntity.getUserId(), TagCrossRefEntity.TWITTER_USER);
			if (tagCrossRefEntities.size() > 0){
				Long tagId = tagCrossRefEntities.get(0).getTagId();
				TagVo tagVo = new TagVo(tagId);
				tagService.setFacePic(tagVo);
				TweetListVo tweetListVo = new TweetListVo(twitterUserDetailEntity.getName(),
						tagVo.getFacePic(), tagId, tweetEntity.getText(),
						twitterUserDetailEntity.getDescription());
				tweetListVos.add(tweetListVo);
			}
			else{
				TweetListVo tweetListVo = new TweetListVo(twitterUserDetailEntity.getName(), null, null, 
						tweetEntity.getText(), twitterUserDetailEntity.getDescription());
				tweetListVos.add(tweetListVo);
			}
		}
		return tweetListVos;
	}
	
	public List<TweetEntity> getDbTweets(Paging paging){
		return tweetsDao.get(paging);
	}
	
	public void downloadTimelineToDb(int limit){
		Twitter twitter = getTwitterInstance();
	    List<Status> statuses;
	    int count = 0;
		try {
			statuses = twitter.getFriendsTimeline();
			logger.debug("Fetched "+statuses.size()+" statuses from Twitter, will try to process "
					+limit+" of them as per the limit set.");
		    for (Status status : statuses) {
		    	
		    	tweetsDao.save(new TweetEntity(status));
		    	twitterUserDetailDao.save(new TwitterUserDetailEntity(status));
		    	
		    	count++;
		    	if (count>=limit){
		    		break;
		    	}
		    }
		} catch (TwitterException e) {
			logger.error(e);
		}
	}
	
	public List<String> getTimeline(int limit){
		Twitter twitter = getTwitterInstance();
		List<String> timeline = new ArrayList<String>();
	    List<Status> statuses;
	    int count = 0;
		try {
			statuses = twitter.getFriendsTimeline();
		    for (Status status : statuses) {
		    	timeline.add(status.getUser().getName() + ":" +
                        status.getText());
		    	count++;
		    	if (count>=limit){
		    		break;
		    	}
		    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}   
		return timeline;
	}
}
