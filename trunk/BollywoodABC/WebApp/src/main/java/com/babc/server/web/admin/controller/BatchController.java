package com.babc.server.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.model.PageCacheEntity;
import com.babc.server.service.MailService;
import com.babc.server.service.TwitterService;
import com.babc.server.utils.EntityCache;

@Controller
@RequestMapping("/batch")
public class BatchController {
	
	private @Autowired TwitterService twitterService;
	private @Autowired MailService mailService;
	private @Autowired EntityCache entityCache;
	
	@RequestMapping(value="/downloadTwitterTimeline.htm", method = RequestMethod.POST)
	public ModelAndView downloadTwitterTimeline(){
		twitterService.downloadTimelineToDb(50);
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	@RequestMapping(value="/processMailQueue.htm", method = RequestMethod.POST)
	public ModelAndView processMailQueue(HttpServletRequest request){
		int priority = Integer.parseInt(request.getParameter("priority"));
		mailService.processMailQueue(priority);
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	@RequestMapping(value="/createNewsletter.htm", method = RequestMethod.POST)
	public ModelAndView createNewsletter(){
		mailService.sendWeeklyNewsletter();
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	@RequestMapping(value="/clearCache.htm", method = RequestMethod.POST)
	public ModelAndView clearCache(){
		entityCache.removeEntities(PageCacheEntity.class);
		return new ModelAndView("admin.message", "message", "Batch Process Completed.");
	}
	
	
}
