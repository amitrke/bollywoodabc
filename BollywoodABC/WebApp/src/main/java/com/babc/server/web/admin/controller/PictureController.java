package com.babc.server.web.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.babc.server.model.Photogallery;
import com.babc.server.model.PictureEntity;
import com.babc.server.service.CategoryService;
import com.babc.server.service.PictureService;
import com.babc.server.web.admin.model.AdminAddPicsToGlryModel;
import com.babc.server.web.admin.model.AdminUpdtPictModel;
import com.babc.server.web.admin.model.KeyValuePair;
import com.google.appengine.api.datastore.Blob;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {
	
	private @Autowired Validator validator;
	private @Autowired CategoryService categoryService;
	private @Autowired PictureService pictureService;
	
	private final int noOfPicsPerPage = 10;
	
	@RequestMapping(value="/album/addPics.htm", method = RequestMethod.GET)
	public ModelAndView addPicsToAlbum(){
		return new ModelAndView("admin.addPicsToGallery", "galleryModel", new AdminAddPicsToGlryModel());
	}
	
	@RequestMapping(value="/album/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listPhotoGallries(@PathVariable("pageNo") int pageNo){
		return new ModelAndView("admin.listPhotogalleries", "data", categoryService.getAsKeyVal("2", new Paging(noOfPicsPerPage, (pageNo-1)*noOfPicsPerPage)));
	}
	
	@RequestMapping(value="/album/details/{albumId}.htm", method = RequestMethod.GET)
	public ModelAndView displayPhotoGallery(@PathVariable("albumId") Long albumId){
		return new ModelAndView("admin.photoGalleryDetails", "galleryModel", pictureService.getPhotogallery(albumId, new Paging()));
	}
	
	@RequestMapping(value="/album/addPics.htm", method = RequestMethod.POST)
	public String updatePictureGallery(@ModelAttribute("galleryModel") AdminAddPicsToGlryModel glryModel, 
			BindingResult result) throws IOException{
		
		//validator.validate(glryModel, result);
		if (result.hasErrors()){
			return "admin.addPicsToGallery";
		}
		
		List<Photogallery> gallery = new ArrayList<Photogallery>();
		
		for(String picId: glryModel.getPicIds()){
			if (picId != null && (picId.length() > 0)){
				gallery.add(new Photogallery(new Long(glryModel.getCategoryId()), new Long(picId), 0));
			}
		}
		
		pictureService.addPicsToAlbum(gallery);
		
		return "redirect:/admin/picture/album/list/1.htm";
	}
	
	@RequestMapping(value="/new.htm", method = RequestMethod.GET)
	public ModelAndView newPicture(){
		return new ModelAndView("admin.updatePicture", "pictureModel", new AdminUpdtPictModel());
	}
	
	@ModelAttribute("categories")
	public List<KeyValuePair> getCategoriesList(){
		return categoryService.getAsKeyVal(AppConstants.CATEGORY_TYPE_IMAGE, new Paging(Integer.MAX_VALUE, 0));
	}
	
	@RequestMapping(value="/update/{imageId}.htm", method = RequestMethod.GET)
	public ModelAndView newPicture(@PathVariable("imageId") Long picId){
		PictureEntity pictureEntity = pictureService.getPicture(picId);
		AdminUpdtPictModel adminUpdtPictModel = new AdminUpdtPictModel();
		adminUpdtPictModel.setCaption(pictureEntity.getCaption());
		adminUpdtPictModel.setExistingPicId(pictureEntity.getId());
		return new ModelAndView("admin.updatePicture", "pictureModel", adminUpdtPictModel);
	}
	
	@RequestMapping(value="/update/{imageId}.htm", method = RequestMethod.POST)
	public String updatePicture(@PathVariable("imageId") Long picId, 
			@ModelAttribute("pictureModel") AdminUpdtPictModel pictModel, 
			BindingResult result) throws IOException{
		
		validator.validate(pictModel, result);
		if (result.hasErrors()){
			return "admin.updatePicture";
		}
		
		PictureEntity pictureEntity = new PictureEntity(pictModel.getFile().getName(), 
				pictModel.getCaption(), new Blob(pictModel.getFile().getBytes()), 'A');
		pictureEntity.setId(picId);
		
		pictureService.savePicture(pictureEntity);
		return "redirect:/admin/picture/list/1.htm";
	}
	
	@RequestMapping(value="/list/{pageNo}.htm", method = RequestMethod.GET)
	public ModelAndView listPictures(@PathVariable("pageNo") int pageNo){
		List<PictureEntity> pictureEntities = pictureService.getAllPictures(new Paging(noOfPicsPerPage, (pageNo-1)*noOfPicsPerPage));
		return new ModelAndView("admin.listPictures", "pictureModel", pictureEntities);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	} 
	
	@RequestMapping(value="/new.htm", method = RequestMethod.POST)
	public String addNew(@ModelAttribute("pictureModel") AdminUpdtPictModel pictModel, BindingResult result) throws IOException{
		
		validator.validate(pictModel, result);
		if (result.hasErrors()){
			return "admin.updatePicture";
		}
		
		PictureEntity pictureEntity = new PictureEntity(pictModel.getFile().getName(), pictModel.getCaption(), new Blob(pictModel.getFile().getBytes()), 'A');
		pictureService.savePicture(pictureEntity);
		return "redirect:/admin/picture/list/1.htm";
	}
}
