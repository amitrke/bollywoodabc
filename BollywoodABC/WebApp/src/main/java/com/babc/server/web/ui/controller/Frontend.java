package com.babc.server.web.ui.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
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
import com.babc.server.model.CategoryEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.vo.StoryVo;
import com.babc.server.service.CategoryService;
import com.babc.server.service.PictureService;
import com.babc.server.service.StoryService;
import com.babc.server.service.TwitterService;
import com.babc.server.web.admin.model.AdminUpdtStoryModel;
import com.babc.server.web.admin.model.KeyValuePair;
import com.babc.server.web.model.HtmlPage;
import com.babc.server.web.model.UiPaging;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/news")
public class Frontend extends AbstractBaseController{
	
	private @Autowired StoryService storyService;
	private @Autowired CategoryService categoryService;
	private @Autowired TwitterService twitterService;
	private @Autowired PictureService pictureService;
	
	private final int noOfStoriesPerPage = 10;
	private final int noOfTweetsPerPage = 20;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/add.htm", method = RequestMethod.GET)
	public String addNews(Map model, HttpServletRequest request, HttpServletResponse response){
		authCheck(request, response);
		model.put("storyModel", new AdminUpdtStoryModel());
		return "ui.story.add";
	}
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public ModelAndView displayHomePage(){
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("date", new Date());
		home.put("homePageStories", storyService.getStoryAndImages(new Paging(AppConstants.noOfStoriesOnFirstPage, 0)));
		home.put("twitterTimeline", twitterService.getTimeline(10));
		home.put("recentPics", pictureService.getAllPictures(new Paging(22, 0)));
		home.put("mainPic", pictureService.getPicture(AppConstants.HOME_PAGE_MAIN_PIC));
		HtmlPage htmlPage = new HtmlPage("Bollywood News - Wallpapers, Songs, Videos, Movies, Stars", 
				"Latest Bollywood gossip news. Get the latest celebrity gossip, entertainment gossip, celeb gossip news, new movie trailers, TV, movie reviews from Bollywood", 
				"Bollywood, News, Hollywood, Wallpapers, Hi Resolution pics", 
				1, "BollywoodABC", home);
		return new ModelAndView("ui.homepage", "page", htmlPage);
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
				10, "BollywoodABC", storyVo);
		return new ModelAndView("ui.story.display", "page", htmlPage);
	}
	
	@RequestMapping(value="/jetty.htm", method = RequestMethod.GET)
	public void jetty(HttpServletResponse httpServletResponse) throws IOException{ 
		PrintWriter out = httpServletResponse.getWriter();
		out.println("Hello World !");
	    out.close();
	}
	
	
	
	@RequestMapping(value="/tweets/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView displayTweets(@PathVariable("pageNo") int pageNo, HttpServletResponse response){
		if (pageNo > 2){
			response.setHeader("Location", "/news/archive.htm");
			response.setStatus(301);
			return null;
		}
		Integer tweetCount = 40;//twitterService.getTweetList(new Paging(Integer.MAX_VALUE, 0)).size();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setTweetsUiPaging(pageNo, noOfTweetsPerPage, tweetCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfTweetsPerPage, ((pageNo-1)*noOfTweetsPerPage));
		home.put("date", new Date());
		home.put("tweets", twitterService.getTweetList(paging));
		HtmlPage htmlPage = new HtmlPage("Bollywood Tweets", 
				"Latest Bollywood gossip news. Get the latest celebrity gossip, entertainment gossip, celeb gossip news, new movie trailers, TV, movie reviews from Bollywood", 
				"Bollywood, News, Hollywood, Wallpapers, Hi Resolution pics", 
				1, "BollywoodABC", home);
		return new ModelAndView("ui.tweet.display", "page", htmlPage);
	}
	
	
	@RequestMapping(value="/latest/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listStories(@PathVariable("pageNo") int pageNo, HttpServletResponse response){
		if (pageNo > 2){
			response.setHeader("Location", "/news/archive.htm");
			response.setStatus(301);
			return null;
		}
		Integer storyCount = 20;//storyService.get(new Paging(Integer.MAX_VALUE, 0)).size();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.getStoryAndImages(paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	@RequestMapping(value="/hollywood/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listHollywoodStories(@PathVariable("pageNo") int pageNo, HttpServletResponse response){
		if (pageNo > 2){
			response.setHeader("Location", "/news/archive.htm");
			response.setStatus(301);
			return null;
		}
		Integer storyCount = 20;//storyService.getByCategory(AppConstants.CAT_HOLLYWOOD, new Paging(Integer.MAX_VALUE, 0)).size();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.getByCategory(AppConstants.CAT_HOLLYWOOD, paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	@RequestMapping(value="/bollywood/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listBollywoodStories(@PathVariable("pageNo") int pageNo, HttpServletResponse response){
		if (pageNo > 2){
			response.setHeader("Location", "/news/archive.htm");
			response.setStatus(301);
			return null;
		}
		Integer storyCount = 20;//storyService.getByCategory(AppConstants.CAT_BOLLYWOOD, new Paging(Integer.MAX_VALUE, 0)).size();
		Map<String, Object> home = new HashMap<String, Object>();
		UiPaging uiPaging = new UiPaging();
		uiPaging.setStoryUiPaging(pageNo, noOfStoriesPerPage, storyCount);
		home.put("paging", uiPaging);
		Paging paging = new Paging(noOfStoriesPerPage, (((pageNo-1)*noOfStoriesPerPage))+AppConstants.noOfStoriesOnFirstPage);
		home.put("storyList", storyService.getByCategory(AppConstants.CAT_BOLLYWOOD, paging));
		return new ModelAndView("ui.story.list", "page", home);
	}
	
	@RequestMapping(value="/archive-{quarter}-{year}.htm", method = RequestMethod.GET)
	public ModelAndView archive(@PathVariable("quarter") String quarter,
			@PathVariable("year") String year){
		
		DateRange dateRange = quarterToDateRange(quarter, year);
		
		List<StoryEntity> storyEntities = storyService.getLite(dateRange.startDate, dateRange.endDate);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yy");
		
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("storyList", storyEntities);
		home.put("range", dateFormat.format(dateRange.startDate)+" to "+dateFormat.format(dateRange.endDate));
		return new ModelAndView("ui.archive.story.detail", "page", home);
	}
	
	@RequestMapping(value="/archive.htm", method = RequestMethod.GET)
	public ModelAndView archiveHome(){
		int startYear = 2006;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		List<Integer> years = new ArrayList<Integer>();
		for (int i=startYear; i<= currentYear; i++){
			years.add(i);
		}
				
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("years", years);
		return new ModelAndView("ui.archive.story.home", "page", home);
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
	
	
	private DateRange quarterToDateRange(String quarter, String year){
		Calendar calendar = Calendar.getInstance();
		
		if (quarter.equals("Q1")){
			return setDateRange(calendar, Integer.parseInt(year), Calendar.JANUARY, Calendar.MARCH);
		}
		else if (quarter.equals("Q2")){
			return setDateRange(calendar, Integer.parseInt(year), Calendar.APRIL, Calendar.JUNE);
		}
		else if (quarter.equals("Q3")){
			return setDateRange(calendar, Integer.parseInt(year), Calendar.JULY, Calendar.SEPTEMBER);
		}
		else {
			return setDateRange(calendar, Integer.parseInt(year), Calendar.OCTOBER, Calendar.DECEMBER);
		}
	}
	
	private DateRange setDateRange(Calendar calendar, int year, int startMonth, int endMonth){
		DateRange dateRange = new DateRange();
		calendar.set(year, startMonth, 1, 00, 00);
		dateRange.startDate = new Date(calendar.getTimeInMillis());
		calendar.set(year, endMonth, 31, 23, 59);
		dateRange.endDate = new Date(calendar.getTimeInMillis());
		return dateRange;
	}
	
	class DateRange{
		Date startDate;
		Date endDate;
	}
}
