package com.babc.server.web.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babc.server.model.StoryEntity;
import com.babc.server.service.StoryService;

@Controller
@RequestMapping("/")
public class PhpController {
	
	private @Autowired StoryService storyService;
	
	@RequestMapping(value="/story.php", method = RequestMethod.GET)
	public void displayStory(HttpServletRequest request, HttpServletResponse response){
		Long oldStoryId = new Long(request.getParameter("id"));
		Long newStoryId = storyService.getImportOldToNewKey(oldStoryId);
		StoryEntity storyEntity = storyService.get(newStoryId);
		String url = "/news/" +  newStoryId+"/"+storyEntity.getTitleUrl()+".htm";
		response.setHeader("Location", url);
		response.setStatus(301);
	}
	
	@RequestMapping(value="/photogallery/main.php", method = RequestMethod.GET)
	public void photogallery(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Location", "/photogallery/main.htm");
		response.setStatus(301);
	}
	
	@RequestMapping(value="/index.php", method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Location", "/");
		response.setStatus(301);
	}
	
	
}
