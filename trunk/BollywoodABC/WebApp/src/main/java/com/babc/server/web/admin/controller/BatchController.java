package com.babc.server.web.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.service.MailService;
import com.babc.server.service.TwitterService;

@Controller
@RequestMapping("/batch")
public class BatchController {
	
	private static transient final Log logger = LogFactory.getLog(BatchController.class);
	
	private @Autowired TwitterService twitterService;
	private @Autowired MailService mailService;
	
	@RequestMapping(value="/downloadTwitterTimeline.htm", method = RequestMethod.POST)
	public ModelAndView downloadTwitterTimeline(){
		twitterService.downloadTimelineToDb(50);
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	@RequestMapping(value="/processDayMailQueue.htm", method = RequestMethod.POST)
	public ModelAndView processDayMailQueue(){
		mailService.processDayMailQueue();		
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	@RequestMapping(value="/processNightMailQueue.htm", method = RequestMethod.POST)
	public ModelAndView processNightMailQueue(){
		mailService.processNightMailQueue();
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
}
