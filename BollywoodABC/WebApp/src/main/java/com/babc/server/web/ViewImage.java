package com.babc.server.web;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babc.server.AppConstants;
import com.babc.server.dao.PictureDao;
import com.babc.server.model.PictureEntity;
import com.babc.server.model.StoryEntity;
import com.babc.server.utils.EntityCache;
import com.babc.server.utils.QueryCache;

@Controller
@RequestMapping("/dimage")
public class ViewImage {

	private transient static final Logger LOGGER = Logger
			.getLogger(ViewImage.class.getName());

	private @Autowired PictureDao pictureDao;
	private @Autowired EntityCache entityCache;
	private @Autowired QueryCache queryCache;
	
	@RequestMapping(value="/{imageId}.jpeg", method = RequestMethod.GET)
	public void loadImage(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {

		PictureEntity pictureEntity = pictureDao.getById(picId);
		if (pictureEntity!=null){
			byte[] bytes = pictureEntity.getData().getBytes();
			OutputStream os = response.getOutputStream();
			response.setContentType("image/jpg");
			response.setDateHeader("Expires",
					   System.currentTimeMillis(  ) + AppConstants.BROWSER_CACHE_DEF_EXPIRY);
			os.write(bytes);
		}
	}
	
	@RequestMapping(value="/{imageId}.htm", method = RequestMethod.GET)
	public void loadOldHtmImage(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {
		loadImage(picId, response);
	}
	
	@RequestMapping(value="/thumb/{imageId}.jpeg", method = RequestMethod.GET)
	public void loadThumb(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {
		
		byte[] bytes;
		
		Object cacheData = entityCache.getEntity(PictureEntity.class, "thumb-"+picId);
		if ( cacheData != null){
			bytes = (byte[]) cacheData;
		}
		else{
			LOGGER.info("Loding thumbnail picid " + picId);
			try{
				PictureEntity pictureEntity = pictureDao.getById(picId);
				bytes = pictureEntity.getThumbData().getBytes();
				entityCache.putEntity(PictureEntity.class, "thumb-"+picId, bytes);
			}
			catch(NullPointerException exception){
				LOGGER.info("Could not load thumbnail id " + picId);
				bytes = new byte[0];
			}
		}
		
		OutputStream os = response.getOutputStream();
		response.setDateHeader("Expires",
				   System.currentTimeMillis(  ) + AppConstants.BROWSER_CACHE_DEF_EXPIRY);
		response.setContentType("image/jpg");
		os.write(bytes);
	}
	
	@RequestMapping(value="/thumb/{imageId}.htm", method = RequestMethod.GET)
	public void loadOldHtmThumb(@PathVariable("imageId") Long picId,
			HttpServletResponse response) throws Exception {
		loadThumb(picId, response);
	}
	
	@RequestMapping(value="/rmCacheStory.htm", method = RequestMethod.GET)
	public void rmCacheStory(){
		queryCache.removeQueries(StoryEntity.class);
	}

}
