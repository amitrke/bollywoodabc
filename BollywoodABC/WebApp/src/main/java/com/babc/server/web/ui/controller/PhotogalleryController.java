package com.babc.server.web.ui.controller;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.vo.CategoryVo;
import com.babc.server.service.CategoryService;
import com.babc.server.service.PictureService;
import com.babc.server.web.model.HtmlPage;

@Controller
@RequestMapping("/photogallery")
public class PhotogalleryController {
	
	private @Autowired CategoryService categoryService;
	private @Autowired PictureService pictureService;
	
	@RequestMapping(value="/{imageId}/*.jpeg", method = RequestMethod.GET)
	public void loadImage(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {

		PictureEntity pictureEntity = pictureService.getPicture(picId);
		if (pictureEntity!=null){
			byte[] bytes = pictureEntity.getData().getBytes();
			OutputStream os = response.getOutputStream();
			response.setContentType("image/jpg");
			os.write(bytes);
		}
	}
	
	@RequestMapping(value="/wallpaper/*/{imageId}.htm", method = RequestMethod.GET)
	public ModelAndView loadImagePage(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {
		PictureEntity pictureEntity = pictureService.getPicture(picId);
		HtmlPage htmlPage = new HtmlPage(pictureEntity.getCaption(), pictureEntity.getCaption(), pictureEntity.getCaption(), AppConstants.metaExpiryNextYear, "BollywoodABC", pictureEntity);
		return new ModelAndView("ui.photogallery.picpage", "page", htmlPage);
	}
	
	@RequestMapping(value="/main.htm", method = RequestMethod.GET)
	public ModelAndView displayPhotogallery(){
		
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("date", new Date());
		home.put("galleries", categoryService.get("2", new Paging(Integer.MAX_VALUE, 0)));
		
		HtmlPage htmlPage = new HtmlPage("Bollywood Photogallery - wallpapers, Hi resolution pictures", 
				"Bollywood Photogallery - wallpapers, Hi resolution pictures of Deepika Padukone, Ashwariya Rai, Kareena Kapoor, Sonam Kapoor", 
				"Wallpapers, Hi Resolution pics, Deepika Padukone, Ashwariya Rai, Kareena Kapoor, Sonam Kapoor", 
				AppConstants.metaExpiryExpired, "BollywoodABC", home);
		
		return new ModelAndView("ui.photogallery.main", "page", htmlPage);
	}
	
	@RequestMapping(value="/{galleryId}/*.htm", method = RequestMethod.GET)
	public ModelAndView displayPhotogalleryDetails(@PathVariable("galleryId") Long galleryId){
		CategoryVo categoryVo = categoryService.get(galleryId);
		Map<String, Object> home = new HashMap<String, Object>();
		home.put("gallery", pictureService.getPhotogalleryGrid(galleryId, 4));
		home.put("category", categoryVo);
		HtmlPage htmlPage = new HtmlPage(categoryVo.getName()+" Photogallery - wallpapers, Hi resolution pictures", 
				categoryVo.getDescription()+" Photogallery - wallpapers, Hi resolution pictures", 
				categoryVo.getName(), 
				AppConstants.metaExpiryExpired, "BollywoodABC", home);
		return new ModelAndView("ui.photogallery.detail", "page", htmlPage);
	}
	
}
