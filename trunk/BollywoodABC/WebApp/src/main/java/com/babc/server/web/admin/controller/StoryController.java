package com.babc.server.web.admin.controller;

import java.io.IOException;
import java.util.Date;
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
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.vo.StoryVo;
import com.babc.server.service.CategoryService;
import com.babc.server.service.StoryService;
import com.babc.server.web.admin.model.AdminUpdtStoryModel;
import com.babc.server.web.admin.model.KeyValuePair;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Text;

@Controller
@RequestMapping("/admin/story")
public class StoryController {
	
	private @Autowired StoryService storyService;	
	private @Autowired CategoryService categoryService;
	private @Autowired Validator validator;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/new.htm", method = RequestMethod.GET)
	public String addNew(Map model){
		model.put("storyModel", new AdminUpdtStoryModel());
		return "admin.updateStory";
	}
	
	@ModelAttribute("categories")
	public List<KeyValuePair> getCategoriesList(){
		return categoryService.getAsKeyVal(AppConstants.CATEGORY_TYPE_CONTENT, new Paging());
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
			
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String addNew(@ModelAttribute("storyModel") AdminUpdtStoryModel form, BindingResult result)throws IOException{
		
		validator.validate(form, result);
		
		if(result.hasErrors()){  
			return "admin.updateStory";  
		}  
		
		PictureEntity pictureEntity = null;
		if (form.getImageData().getName().length() > 0){
			pictureEntity = new PictureEntity(form.getImageData().getOriginalFilename(),
					form.getImageCaption(), new Blob(form.getImageData().getBytes()), 
					AppConstants.ENTITY_STATUS_ENABLED);
		}
		else{
			pictureEntity = new PictureEntity(form.getImageId());
		}
		
		StoryVo storyVo = new StoryVo(form.getTitle(), form.getCategory(), form.getAuthor(),
				new Text(form.getBody()), form.getIntro(), new Date(), form.getPriority(),
				AppConstants.ENTITY_STATUS_ENABLED, pictureEntity, form.getVideo());
		
		storyService.add(storyVo);
		
		return "redirect:/admin/story/list/1.htm";
	}
	
	@RequestMapping(value="/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView storyList(@PathVariable("pageNo") int pageNo){
		List<StoryEntity> storyEntities = storyService.get(new Paging(
				AppConstants.DATA_DEFAULT_LIMIT, 
				(pageNo-1)*AppConstants.DATA_DEFAULT_LIMIT));
		return new ModelAndView("admin.listStories", "data", storyEntities);
	}
}
