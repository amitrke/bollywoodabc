package com.babc.server.web.rest;

import java.util.Date;

import org.restlet.resource.ServerResource;

import com.babc.server.model.StoryEntity;
import com.google.appengine.api.datastore.Text;

public class StoryServerResource extends ServerResource implements StoryResource {
	
	@Override
	public StoryEntity retrieve() {
		StoryEntity storyEntity = new StoryEntity("Sample Title", 1L, 1L, new Text("SampleBody"), "Sample Intro", new Date(), 1, 'a', 1L, "a");
		return storyEntity;
	}

}
