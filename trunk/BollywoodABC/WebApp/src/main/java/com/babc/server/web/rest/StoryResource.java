package com.babc.server.web.rest;

import org.restlet.resource.Get;

import com.babc.server.model.StoryEntity;

public interface StoryResource {
	
	@Get
	public StoryEntity retrieve();
	
}
