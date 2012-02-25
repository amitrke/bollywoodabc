package com.babc.server.web.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.model.StoryEntity;
import com.babc.server.model.vo.TagVo;
import com.babc.server.service.StoryService;
import com.babc.server.service.TagService;
import com.babc.server.web.model.HtmlPage;

@Controller
@RequestMapping("/")
public class PhpController extends AbstractBaseController{
	
	private @Autowired StoryService storyService;
	private @Autowired TagService tagService;
	
	@RequestMapping(value="/index.htm", method = RequestMethod.GET)
	public void displayHomePage(HttpServletResponse response){
		String url = "/news/home.htm";
		response.setHeader("Location", url);
		response.setStatus(301);
	}
	
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
	
	@RequestMapping(value="/t/{tagId}/*.htm", method = RequestMethod.GET)
	public ModelAndView tag(@PathVariable("tagId") Long tagId){
		TagVo tagVo = tagService.getTagDetails(tagId);
		HtmlPage htmlPage = new HtmlPage(tagVo.getTag(),
				tagVo.getDescription(), tagVo.getTag()+", bollywood, news, gossip",
				10, 
				"BollywoodABC", tagVo);
		return new ModelAndView("ui.tag.display", "page", htmlPage);
	}
	
}
