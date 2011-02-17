package com.babc.server.web.admin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babc.server.AppConstants;
import com.babc.server.dao.TagCrossRefDao;
import com.babc.server.dao.TagDao;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.TagEntity;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	private @Autowired TagDao tagDao;
	private @Autowired TagCrossRefDao tagCrossRefDao;
	
	@RequestMapping(value="/home.htm", method = RequestMethod.GET)
	public String home() {
		return "admin.homepage";
	}
	
	
	@RequestMapping(value="/initTags.htm", method = RequestMethod.GET)
	public String initTags() {
		TagEntity tagEntity = tagDao.save(new TagEntity("Kareena Kapoor", TagEntity.ACTOR,
				new Date(), "Actress Kareena Kapoor", AppConstants.ENTITY_STATUS_ENABLED));
		TagCrossRefEntity crossRefEntity = tagCrossRefDao.save(new TagCrossRefEntity(1L, 1L, 1, new Date(), 
				AppConstants.ENTITY_STATUS_ENABLED));
		return "admin.homepage";
	}
}
