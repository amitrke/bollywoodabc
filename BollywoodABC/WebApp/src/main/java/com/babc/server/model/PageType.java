package com.babc.server.model;

import org.apache.log4j.Logger;

public enum PageType {
	
	HOME, STORY, BOLLYWOOD_LIST, HOLLYWOOD_LIST, PHOTOGALLERY_HOME, PHOTOGALLERY_DETAIL, PHOTO_DETAIL, STORY_ALL_LIST, TAG_DETAIL, TWEET_LIST, STORY_ARCHIVE, 
	TWEET_ARCHIVE, SYNDICATION_MAIN;
	
	private static final Logger LOGGER = Logger.getLogger(PageType.class.getName());
	
	public Long getPageCacheId(String url){
		Long pageId = new Long(0);
		Long pageCacheId = new Long(0);
		if (this == STORY || this == PHOTOGALLERY_DETAIL || this == PHOTO_DETAIL || this == TAG_DETAIL ){
			pageCacheId = getPageId(url);
		}
		else if(this == BOLLYWOOD_LIST || this == STORY_ALL_LIST || this == HOLLYWOOD_LIST || this == TWEET_LIST || this == STORY_ARCHIVE){
			pageId = getPageId(url);
			String mostSigDigits = Integer.toString(this.ordinal()*10);
			String restOfTheDigits = pageId.toString();
			pageCacheId = Long.parseLong(mostSigDigits+restOfTheDigits);
		}
		else if(this == HOME || this == SYNDICATION_MAIN ){
			pageId = 1L;
			String mostSigDigits = Integer.toString(this.ordinal()*10);
			String restOfTheDigits = pageId.toString();
			pageCacheId = Long.parseLong(mostSigDigits+restOfTheDigits);
		}
		else{
			LOGGER.error("Page type not defined correctly.");
		}
		return pageCacheId;
	}
	
	private Long getPageId(String url){
		switch (this){
			case STORY:
			case PHOTOGALLERY_DETAIL:
			case TAG_DETAIL:
				return Long.parseLong(url.split("/")[4]);
			case HOLLYWOOD_LIST:
			case STORY_ALL_LIST:
			case BOLLYWOOD_LIST:
			case TWEET_LIST:
				return Long.parseLong(url.split("/")[5].split("\\.")[0]);
			case PHOTO_DETAIL:
				return Long.parseLong(url.split("/")[6].split("\\.")[0]);
			case STORY_ARCHIVE:
				String[] ar = url.split("/")[4].split("\\.")[0].split("-");
				String b = ar[1].substring(1)+ar[2];
				return Long.parseLong(b);
			default: return new Long(0);
		}
	}
}
