package com.babc.server.web.admin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

@Controller
@RequestMapping("/mq")
public class TaskQueueController {
	private static transient final Log logger = LogFactory.getLog(TaskQueueController.class);
	
	@RequestMapping(value="/downloadTwitterTimeline.htm", method = RequestMethod.GET)
	public ModelAndView downloadTwitterTimeline(){
		Queue queue = QueueFactory.getDefaultQueue();
		logger.debug("Batch Process to download twitter timeline Initiated.");
		queue.add(TaskOptions.Builder.withUrl("/batch/downloadTwitterTimeline.htm"));
		return new ModelAndView("admin.message", "message", "Batch Process Initiated.");
	}
	
}
