package com.babc.server.web.ui.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babc.jaxb.rss.ChannelListType;
import com.babc.jaxb.rss.ImageType;
import com.babc.jaxb.rss.ItemType;
import com.babc.jaxb.rss.ObjectFactory;
import com.babc.jaxb.rss.RssType;
import com.babc.server.AppConstants;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.service.StoryService;
import com.babc.server.utils.EntityCache;

@Controller
@RequestMapping("/feed")
public class XmlSyndication{
	
	private transient static final Logger LOGGER = Logger.getLogger(XmlSyndication.class.getName());
	
	private @Autowired StoryService storyService;
	private @Autowired EntityCache entityCache;
	
	private final int noOfStories = 15;
	
	@RequestMapping(value="/rss.htm", method = RequestMethod.GET)
	public void rss(HttpServletResponse httpServletResponse) throws IOException{
		PrintWriter out = httpServletResponse.getWriter();
		httpServletResponse.setDateHeader("Expires",
				   System.currentTimeMillis(  ) + AppConstants.BROWSER_CACHE_EXPIRY_ONE_DAY);
		String rssData = null;
		Object rssCachedData = entityCache.getEntity(String.class, "rss");
		if (rssCachedData != null){
			rssData = (String) rssCachedData;
		}
		else{
			rssData = createRss();
			entityCache.putEntity(String.class, "rss", rssData);
		}
		out.println(rssData);
	    out.close();
	}
	
	private String createRss(){
		LOGGER.info("Creating RSS from DB");
		ObjectFactory objectFactory = new ObjectFactory();
		RssType rssType = objectFactory.createRssType();
		rssType.setVersion("2.0");
		ChannelListType channelListType = objectFactory.createChannelListType();
		setRssMeta(channelListType);
		setStories(channelListType, storyService.get(new Paging(noOfStories, 0)));
		rssType.setChannel(channelListType);
		StringWriter stringWriter = new StringWriter();
		try{
			JAXBElement<RssType> jaxbElement = objectFactory.createRss(rssType);
			JAXBContext jaxbContext = JAXBContext.newInstance("com.babc.jaxb.rss");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(jaxbElement, stringWriter);
		}
		catch(JAXBException jaxbException){
			LOGGER.error("JAXBException occured while creating rss:"+jaxbException.getMessage());
		}
		return stringWriter.toString();
	}
	
	private void setStories(ChannelListType channelListType, List<StoryEntity> storyEntities){
		for(StoryEntity storyEntity: storyEntities){
			ItemType itemType = new ItemType();
			itemType.setTitle(storyEntity.getTitle());
			String url = "http://www.bollywoodabc.com/news/"+storyEntity.getId()+"/"+storyEntity.getTitleUrl()+".htm";
			itemType.setLink(url);
			itemType.setGuid(url);
			itemType.setPubDate(storyEntity.getCreateDate().toString());
			itemType.setDescription(storyEntity.getIntro());
			channelListType.getItem().add(itemType);
		}
	}
	
	private void setRssMeta(ChannelListType channelListType){
		channelListType.setCopyright("Copyright:(C) 2010 BollywoodAbc.com");
		channelListType.setDescription("BollywoodAbc.com organizes Bollywood News, Pictures, Movie Reviews, Gossips at one place");
		ImageType imageType = new ImageType();
		imageType.setLink("http://www.bollywoodabc.com/");
		imageType.setTitle("BollywoodABC");
		imageType.setUrl("http://www.bollywoodabc.com/images/bollywoodabc.gif");
		channelListType.setImage(imageType);
		channelListType.setLanguage("en-us");
		channelListType.setLastBuildDate((new Date()).toString());
		channelListType.setLink("http://www.bollywoodabc.com/");
		channelListType.setTitle("BollywoodABC.com");
	}
}
