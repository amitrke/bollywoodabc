package com.babc.server.web.ui.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.vo.StoryVo;
import com.babc.server.service.CategoryService;
import com.babc.server.service.StoryService;
import com.babc.server.web.admin.model.AdminUpdtStoryModel;
import com.babc.server.web.admin.model.KeyValuePair;
import com.babc.server.web.model.HtmlPage;
import com.babc.server.web.model.UiPaging;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/news")
public class Frontend {
	
	private @Autowired StoryService storyService;
	private @Autowired CategoryService categoryService;
	
	private final int noOfStoriesPerPage = 10;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/add.htm", method = RequestMethod.GET)
	public String addNews(Map model, HttpServletRequest request, HttpServletResponse response){
		authCheck(request, response);
		model.put("storyModel", new AdminUpdtStoryModel());
		return "ui.story.add";
	}
	
	@ModelAttribute("categories")
	public List<KeyValuePair> getCategoriesList(){
		return categoryService.getAsKeyVal(AppConstants.CATEGORY_TYPE_CONTENT, new Paging());
	}
	
	@RequestMapping(value="/disclaimer.htm", method = RequestMethod.GET)
	public String disclaimer(){
		return "ui.disclaimer";
	}
		
	@RequestMapping(value="/{storyId}/*.htm", method = RequestMethod.GET)
	public ModelAndView displayStory(@PathVariable("storyId") Long storyId){
		StoryVo storyVo = storyService.get(storyId, new Paging());
		HtmlPage htmlPage = new HtmlPage(storyVo.getTitle(),
				storyVo.getIntro(), "bollywood, news, gossip",
				AppConstants.metaExpiryNextYear, 
				"BollywoodABC", storyVo);
		return new ModelAndView("ui.story.display", "page", htmlPage);
	}
	
	@RequestMapping(value="/jetty.htm", method = RequestMethod.GET)
	public void jetty(HttpServletResponse httpServletResponse) throws IOException{ 
		PrintWriter out = httpServletResponse.getWriter();
		out.println("Hello World !");
	    out.close();
	}
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public ModelAndView displayHomePage(){
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("date", new Date());
		home.put("homePageStories", storyService.get(new Paging(AppConstants.noOfStoriesOnFirstPage, 0)));
		home.put("babcExtra", (storyService.get(new Paging(1, AppConstants.noOfStoriesOnFirstPage+1))).get(0));
		
		HtmlPage htmlPage = new HtmlPage("Bollywood News - Wallpapers, Songs, Videos, Movies, Stars", 
				"Latest Bollywood gossip news. Get the latest celebrity gossip, entertainment gossip, celeb gossip news, new movie trailers, TV, movie reviews from Bollywood", 
				"Bollywood, News, Hollywood, Wallpapers, Hi Resolution pics", 
				AppConstants.metaExpiryExpired, "BollywoodABC", home);
		return new ModelAndView("ui.homepage", "page", htmlPage);
	}
	
	@RequestMapping(value="/latest/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listStories(@PathVariable("pageNo") int pageNo){
		Integer storyCount = storyService.getCount();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.get(paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	@RequestMapping(value="/hollywood/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listHollywoodStories(@PathVariable("pageNo") int pageNo){
		Integer storyCount = storyService.getCount();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.getByCategory(AppConstants.CAT_HOLLYWOOD, paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	@RequestMapping(value="/bollywood/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listBollywoodStories(@PathVariable("pageNo") int pageNo){
		Integer storyCount = storyService.getCount();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.getByCategory(AppConstants.CAT_BOLLYWOOD, paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	private void authCheck(HttpServletRequest request, HttpServletResponse response){
		UserService userService = UserServiceFactory.getUserService();
		String thisURL = request.getRequestURI();
		if (request.getUserPrincipal() != null) {
			return;
		}
		else{
			response.setHeader("Location", userService.createLoginURL(thisURL));
			response.setStatus(301);
		}
	}
}
