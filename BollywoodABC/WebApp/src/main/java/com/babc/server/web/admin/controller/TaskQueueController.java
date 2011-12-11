package com.babc.server.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

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
	
	@RequestMapping(value="/sessionCleanup.htm", method = RequestMethod.GET)
	public ModelAndView sessionCleanup(){
		Queue queue = QueueFactory.getDefaultQueue();
		logger.debug("Batch Process to clean session table Initiated.");
		queue.add(TaskOptions.Builder.withUrl("/_ah/sessioncleanup").param("clear", "1").method(Method.GET));
		return new ModelAndView("admin.message", "message", "Batch Process Initiated.");
	}
	
	@RequestMapping(value="/processMailQueue.htm", method = RequestMethod.GET)
	public ModelAndView processMailQueue(HttpServletRequest request){
		String priority = request.getParameter("priority");
		Queue queue = QueueFactory.getDefaultQueue();
		logger.debug("Batch Process to process mail queue Initiated.");
		queue.add(TaskOptions.Builder.withUrl("/batch/processMailQueue.htm").param("priority", priority).method(Method.POST));
		return new ModelAndView("admin.message", "message", "Batch Process Initiated.");
	}
	
	@RequestMapping(value="/createWeeklyNewsletter.htm", method = RequestMethod.GET)
	public ModelAndView createWeeklyNewsletter(){
		Queue queue = QueueFactory.getDefaultQueue();
		logger.debug("Batch Process to create newsletter Initiated.");
		queue.add(TaskOptions.Builder.withUrl("/batch/createNewsletter.htm").method(Method.POST));
		return new ModelAndView("admin.message", "message", "Batch Process Initiated.");
	}
}
