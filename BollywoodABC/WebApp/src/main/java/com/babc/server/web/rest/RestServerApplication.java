package com.babc.server.web.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

public class RestServerApplication extends Application {

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());

		router.attachDefault(new Directory(getContext(), "war:///"));
		router.attach("/rest/story/123", StoryServerResource.class);

		return router;
	}

}
