package com.babc.server.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.TweetEntity;
import com.babc.server.service.TwitterService;

@Controller
@RequestMapping("/admin/twitter")
public class TwitterController {
	
	private @Autowired TwitterService twitterService;
	
	@RequestMapping(value="/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView tweetList(@PathVariable("pageNo") int pageNo){
		List<TweetEntity> tweets = twitterService.getDbTweets(new Paging(
				AppConstants.DATA_DEFAULT_LIMIT, 
				(pageNo-1)*AppConstants.DATA_DEFAULT_LIMIT));
		return new ModelAndView("admin.listTweets", "data", tweets);
	}
}
