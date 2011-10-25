package com.babc.server.web.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.service.TwitterService;

@Controller
@RequestMapping("/batch")
public class BatchController {
	
	private static transient final Log logger = LogFactory.getLog(BatchController.class);
	
	private @Autowired TwitterService twitterService;
	
	@RequestMapping(value="/downloadTwitterTimeline.htm", method = RequestMethod.GET)
	public ModelAndView downloadTwitterTimeline(){
		logger.debug("Downloading Twitter timeline STARTED.");
		twitterService.downloadTimelineToDb(50);
		logger.debug("Downloading Twitter timeline FINISHED.");
		
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
}
