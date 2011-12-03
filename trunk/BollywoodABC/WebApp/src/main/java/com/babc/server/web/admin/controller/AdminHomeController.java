package com.babc.server.web.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.utils.CacheStat;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	private @Autowired EntityCache entityCache;
	private @Autowired QueryCache queryCache;
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public ModelAndView home() {
		Map<String, CacheStat> map = new HashMap<String, CacheStat>();
		map.put("entity", entityCache.getStat());
		map.put("query", queryCache.getStat());
		return new ModelAndView("admin.homepage", "data", map);
	}
	
}
