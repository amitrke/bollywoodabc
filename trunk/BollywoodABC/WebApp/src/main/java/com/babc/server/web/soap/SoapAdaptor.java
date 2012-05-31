package com.babc.server.web.soap;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.babc.server.web.soap.entity.Story;
import com.babc.server.web.soap.jaxws.GetRelatedStoriesResponse;
import com.babc.server.web.soap.jaxws.GetTweetsResponse;
import com.babc.server.web.soap.jaxws.VersionResponse;

public class SoapAdaptor {
	
	private Soap soap;
	
	public SoapAdaptor(ApplicationContext context) {
		soap = new Soap(context);
	}

	public VersionResponse version(){
		String version = soap.version();
		VersionResponse response = new VersionResponse();
		response.setReturn(version);
		return response;
	}
	
	public GetRelatedStoriesResponse getRelatedStories(Long tagId){
		List<Story> stories = soap.getRelatedStories(tagId);
		GetRelatedStoriesResponse response = new GetRelatedStoriesResponse();
		response.setReturn(stories);
		return response;
	}
	
	public GetTweetsResponse getTweets(Long tagId){
		List<String> tweets = soap.getTweets(tagId);
		GetTweetsResponse response = new GetTweetsResponse();
		response.setReturn(tweets);
		return response;
	}
}
