package com.babc.server.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service("twitterService")
public class TwitterService {
	
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
