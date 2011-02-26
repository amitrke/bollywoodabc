package com.babc.server.web.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.AppConstants;
import com.babc.server.dao.TagCrossRefDao;
import com.babc.server.dao.TagDao;
import com.babc.server.model.Paging;
import com.babc.server.model.TagCrossRefEntity;
import com.babc.server.model.TagEntity;
import com.babc.server.web.admin.model.TagCrossRefUpdtModel;
import com.babc.server.web.admin.model.TagUpdtModel;


@Controller
@RequestMapping("/admin/tag")
public class TagsController {
	
	private @Autowired TagDao tagDao;
	private @Autowired TagCrossRefDao tagCrossRefDao;
	private @Autowired Validator validator;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
			
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
	
	/**
	 * Create new Tag
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/new.htm", method = RequestMethod.GET)
	public String addNew(@SuppressWarnings("rawtypes") Map model){
		model.put("tagModel", new TagUpdtModel());
		return "admin.updateTag";
	}
	
	/**
	 * Create new Tag Cross reference
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/crossref/new.htm", method = RequestMethod.GET)
	public String addNewCrossRef(@SuppressWarnings("rawtypes") Map model){
		model.put("crossRefModel", new TagCrossRefUpdtModel());
		return "admin.updateTagCrossRef";
	}
	
	/**
	 * process form post while creating a new tag
	 * @param form
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String addNew(@ModelAttribute("storyModel") TagUpdtModel form, BindingResult result)throws IOException{
		
		validator.validate(form, result);
		
		if(result.hasErrors()){  
			return "admin.updateTag";  
		}  
		
		TagEntity tagEntity = new TagEntity(form);
		
		tagDao.save(tagEntity);
		
		return "redirect:/admin/tag/list/1.htm";
	}
	
	/**
	 * process form post while creating a new tag cross reference
	 * @param form
	 * @param result
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/crossref/new.htm", method = RequestMethod.POST)
	public String addNewCrossRef(@ModelAttribute("storyModel") TagCrossRefUpdtModel form, BindingResult result)throws IOException{
		
		validator.validate(form, result);
		
		if(result.hasErrors()){  
			return "admin.updateTagCrossRef";  
		}  
		
		TagCrossRefEntity tagCrossRefEntity = new TagCrossRefEntity(form);
		
		tagCrossRefDao.save(tagCrossRefEntity);
		
		return "redirect:/admin/tag/crossref/list/1.htm";
	}
	
	/**
	 * List tags
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listTags(@PathVariable("pageNo") int pageNo){
		List<TagEntity> tagEntities = tagDao.get(new Paging(
				AppConstants.DATA_DEFAULT_LIMIT, 
				(pageNo-1)*AppConstants.DATA_DEFAULT_LIMIT));
		return new ModelAndView("admin.listTags", "data", tagEntities);
	}
	
	/**
	 * List tags cross reference
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value="/crossref/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listTagCrossRef(@PathVariable("pageNo") int pageNo){
		List<TagCrossRefEntity> tagCrossRefEntities = tagCrossRefDao.get(new Paging(
				AppConstants.DATA_DEFAULT_LIMIT, 
				(pageNo-1)*AppConstants.DATA_DEFAULT_LIMIT));
		return new ModelAndView("admin.listTagCrossRef", "data", tagCrossRefEntities);
	}
	
}
