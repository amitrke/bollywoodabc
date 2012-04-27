package com.babc.server;


public class AppConstants {
	
	
	public static final String APP_NAME = "BollywoodABC.com";
	
	public static final Character STATUS_INACTIVE = 'I';
	public static final String STATUS_ACTIVE = "A";
	
	public static final String HTML_DEFAULT_TITLE = APP_NAME + " : Bollywood News, Bollywood Pictures, Gossips, Events, Movie Releases";
	public static final String HTML_DEFAULT_DESCRIPTION = HTML_DEFAULT_TITLE;
	public static final String HTML_DEFAULT_KEYWORDS = "Bollywood News, Bollywood Pictures, Gossips, Events, Movie Releases";
	
	public static final int DATA_DEFAULT_LIMIT = 10;
	public static final int RELATED_PICS_LIMIT = 25;
	
	/*
	 * Category Types
	 */
	public static final String CATEGORY_TYPE_CONTENT = "1";
	public static final String CATEGORY_TYPE_IMAGE = "2";
	
	public static final int IMAGE_THUMB_X = 90;
	public static final int IMAGE_THUMB_Y = 65;
	
	public static final int noOfStoriesOnFirstPage = 5;
	
	public static final Long CAT_HOLLYWOOD = 141009L;
	public static final Long CAT_BOLLYWOOD = 1001L;
	
	public static final Long TAG_FACEPIC = 567001L;
	
	public static final int ENTITY_STATUS_ENABLED = 65;
	public static final int ENTITY_STATUS_DISABLED = 46;
	
	public static final long BROWSER_CACHE_EXPIRY_ONE_DAY = 24*60*60*1000;
	
	/**
	 * One Week expiry
	 */
	public static final long BROWSER_CACHE_DEF_EXPIRY = 7*BROWSER_CACHE_EXPIRY_ONE_DAY;
	public static final long PAGE_DB_CACHE_DEF_EXPIRY = 30*BROWSER_CACHE_EXPIRY_ONE_DAY;
		
	public static final int MAIL_Q_STATUS_INQUEUE = 1;
	public static final int MAIL_Q_STATUS_DELIVERED = 2;
	
	public static final int USER_TYPE_SUBSCRIBER = 1;
	public static final int USER_TYPE_CJ = 2;
	
	public static final int SUBSCRIPTION_WEEKLY_NEWSLETTER = 1;
	public static final int SUBSCRIPTION_WEEKLY_WALLPAPERS = 2;
	
	public static final Long HOME_PAGE_MAIN_PIC = 14001L;
	
	public static final Long HOME_PAGE_PAGECACHE_ID = 1L;
	
}
