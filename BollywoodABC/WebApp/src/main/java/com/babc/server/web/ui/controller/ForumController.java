package com.babc.server.web.ui.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * @author amit
 *
 */
@Controller
@RequestMapping("/forum")
public class ForumController {
	
	@RequestMapping(value="/main.htm", method = RequestMethod.GET)
	public String forumHome(HttpServletRequest request, Map<String, Object> model){
		UserService userService = UserServiceFactory.getUserService();
		String thisURL = request.getRequestURI();
		model.put("userPrincipal", request.getUserPrincipal());
		model.put("loginURL",userService.createLoginURL(thisURL));
		
		/*
		if (request.getUserPrincipal() != null) {
			model.put("userPrincipal", request.getUserPrincipal());
            
			response.getWriter().println("<p>Hello, " +
                                         request.getUserPrincipal().getName() +
                                         "!  You can <a href=\"" +
                                         userService.createLogoutURL(thisURL) +
                                         "\">sign out</a>.</p>");
            
        } else {
        	model.put("userPrincipal", request.getUserPrincipal());
        	
            response.getWriter().println("<p>Please <a href=\"" +
                                         userService.createLoginURL(thisURL) +
                                         "\">sign in</a>.</p>");
            
        }
        */
		return "ui.forum.home";
	}
	
}
