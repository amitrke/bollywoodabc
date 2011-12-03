package com.babc.server.web.ui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.dao.UserDao;
import com.babc.server.model.UserEntity;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private @Autowired UserDao userDao;
	
	@RequestMapping(value="/login.htm", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response){
		String targetUrl = "/user/account.htm";
		if(request.getUserPrincipal() != null){
			if (userDao.getByEmail(request.getUserPrincipal().getName()) != null){
				targetUrl = "/user/account.htm";
			}
			else{
				targetUrl = "/user/register.htm";
			}
		}
		else{
			UserService userService = UserServiceFactory.getUserService();
			String loginUrl = userService.createLoginURL(request.getRequestURI());
			targetUrl = loginUrl;
		}
		response.setHeader("Location", targetUrl);
		response.setStatus(301);
	}
	
	@RequestMapping(value="/register.htm", method = RequestMethod.GET)
	public String registerForm(Map<String, Object> model){
		model.put("userModel", new UserEntity());
		return "ui.user.register";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
			
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
	
	@RequestMapping(value="/register.htm", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		UserEntity userEntity = new UserEntity(name, request.getUserPrincipal().getName());
		if (userDao.save(userEntity) != null){
			response.setHeader("Location", "/user/account.htm");
			response.setStatus(301);
			return null;
		}
		else{
			return "ui.user.register";
		}
	}
	
	@RequestMapping(value="/account.htm", method = RequestMethod.GET)
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> model = new HashMap<String, Object>();
		if(request.getUserPrincipal() != null){
			UserEntity userEntity = userDao.getByEmail(request.getUserPrincipal().getName());
			model.put("userEntity", userEntity);
		}
		else{
			model.put("error", "Not logged in, Please visit the login page first.");
		}
		return new ModelAndView("ui.user.account", "data", model);
	}
}
