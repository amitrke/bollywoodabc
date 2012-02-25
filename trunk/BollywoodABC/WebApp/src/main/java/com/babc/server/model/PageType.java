package com.babc.server.model;

import org.apache.log4j.Logger;

public enum PageType {
	
	HOME, STORY, BOLLYWOOD_LIST, HOLLYWOOD_LIST, PHOTOGALLERY_HOME, PHOTOGALLERY_DETAIL, PHOTO_DETAIL, STORY_ALL_LIST, TAG_DETAIL, TWEET_LIST;
	
	private static final Logger LOGGER = Logger.getLogger(PageType.class.getName());
	
	public Long getPageCacheId(String url){
		Long pageId = new Long(0);
		if (this == STORY || this == BOLLYWOOD_LIST || this == STORY_ALL_LIST || this == HOLLYWOOD_LIST || this == PHOTOGALLERY_DETAIL || this == PHOTO_DETAIL
				|| this == TAG_DETAIL || this == TWEET_LIST){
			pageId = getPageId(url);
			LOGGER.debug("url="+url+";pageId="+pageId);
		}
		String firstDigit = Integer.toString(this.ordinal());
		String restOfTheDigits = pageId.toString();
		Long pageCacheId = Long.parseLong(firstDigit+restOfTheDigits);
		LOGGER.debug("Page cache ID created="+pageCacheId);
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
				
			default: return new Long(0);
		}
	}
}
