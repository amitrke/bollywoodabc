package com.babc.server.web.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private transient static final Logger LOGGER = Logger.getLogger(StoryController.class.getName());
	private @Autowired StoryService storyService;	
	private @Autowired CategoryService categoryService;
	private @Autowired Validator validator;

	@SuppressWarnings("unchecked")
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
					'A');
		}
		else{
			pictureEntity = new PictureEntity(form.getImageId());
		}
		
		StoryVo storyVo = new StoryVo(form.getTitle(), form.getCategory(), form.getAuthor(),
				new Text(form.getBody()), form.getIntro(), new Date(), form.getPriority(),
				'A', pictureEntity, form.getVideo());
		
		storyService.add(storyVo);
		
		return "redirect:/admin/story/list.htm";
	}
	
	@RequestMapping(value="/list.htm", method = RequestMethod.GET)
	public ModelAndView storyList(){
		List<StoryEntity> storyEntities = storyService.get(new Paging());
		return new ModelAndView("admin.listStories", "data", storyEntities);
	}
	
	/*
	@RequestMapping(value="/importData.htm", method = RequestMethod.GET)
	public String importDataForm(){
		return "admin.importDataForm";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/importData.htm", method = RequestMethod.POST)
	public String importDataForm(@ModelAttribute("storyModel") AdminUpdtStoryModel form)throws IOException, JAXBException, ParseException{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(form.getImageData().getBytes());
		JAXBContext ctx = JAXBContext.newInstance("com.babc.jaxb.importdata");
	    Unmarshaller um = ctx.createUnmarshaller();
	    JAXBElement<CricketBollywoodabcType> jaxbElement =  (JAXBElement<CricketBollywoodabcType>) um.unmarshal(byteArrayInputStream);
	    CricketBollywoodabcType cricketBollywoodabcType = jaxbElement.getValue();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    for(TblcontentType tblcontentType: cricketBollywoodabcType.getTblcontent()){	
	    	if (storyService.getImportOldToNewKey(new Long(tblcontentType.getFldId())) > 1){
	    		LOGGER.info("Key "+tblcontentType.getFldId()+" Exists..");
	    	}
	    	else{
	    		StoryEntity storyEntity = storyService.add(
		    			new StoryVo(tblcontentType.getFldTitle(), 
		    					1001L, 1L, 
		    					new Text(tblcontentType.getFldBody()), 
		    					tblcontentType.getFldIntro(), 
		    					formatter.parse(tblcontentType.getFldUpdationDate()),
		    					5, AppConstants.STATUS_ACTIVE, 
		    					new PictureEntity(42001L), ""));
		    	
		    	storyService.addDataImportKey(new DataImportKey(new Long(tblcontentType.getFldId()), storyEntity.getId()));
	    	}
	    }
		return "redirect:/admin/story/list.htm";
	}
	*/
}
