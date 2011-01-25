package com.babc.server.web.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.vo.CategoryVo;
import com.babc.server.service.CategoryService;
import com.babc.server.web.admin.model.AdminUpdtCategoryModel;
import com.babc.server.web.admin.model.KeyValuePair;
import com.google.appengine.api.datastore.Blob;

@Controller
@RequestMapping("/admin/category")
public class CategoryController{

	private transient static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/list.htm", method = RequestMethod.GET)
	public ModelAndView categoryList(){
		List<CategoryVo> categoryVos = categoryService.get(new Paging());
		return new ModelAndView("admin.listCategories", "data", categoryVos);
	}
	
	@ModelAttribute("types")
	public List<KeyValuePair> getTypes(){
		List<KeyValuePair> types = new ArrayList<KeyValuePair>();
		types.add(new KeyValuePair(1L, "Story"));
		types.add(new KeyValuePair(2L, "Photogallery"));
		types.add(new KeyValuePair(3L, "Story Links"));
		return types;
	}
	
	@RequestMapping(value="/new.htm", method = RequestMethod.GET)
	public ModelAndView newCategory(){
		return new ModelAndView("admin.updateCategory", "categoryModel", new AdminUpdtCategoryModel());
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
			
	} 
	
	@RequestMapping(method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("type") String type,
	        @RequestParam("file") MultipartFile file) {

		PictureEntity pictureEntity = null;
	
		if (file != null){ //Pic uploaded
			try{
				pictureEntity = new PictureEntity("abc", "none", new Blob(file.getBytes()), 'A');
			}
			catch(IOException ioException){
				LOGGER.error("IOException has occured :"+ioException.getMessage());
			}
		}
		
		CategoryVo categoryVo = new CategoryVo(name, description, type, pictureEntity, 'A');
		categoryVo = categoryService.add(categoryVo);
		
		return "redirect:/admin/category/list.htm";
	}
}
