package com.babc.server;

public class AppConstants {
	
	public static final String APP_NAME = "BollywoodABC.com";
	
	public static final Character STATUS_INACTIVE = 'I';
	public static final String STATUS_ACTIVE = "A";
	
	public static final String HTML_DEFAULT_TITLE = APP_NAME + " : Bollywood News, Bollywood Pictures, Gossips, Events, Movie Releases";
	public static final String HTML_DEFAULT_DESCRIPTION = HTML_DEFAULT_TITLE;
	public static final String HTML_DEFAULT_KEYWORDS = "Bollywood News, Bollywood Pictures, Gossips, Events, Movie Releases";
	
	public static final int DATA_DEFAULT_LIMIT = 10; 
	/*
	 * Category Types
	 */
	public static final String CATEGORY_TYPE_CONTENT = "1";
	public static final String CATEGORY_TYPE_IMAGE = "2";
	
	public static final int IMAGE_THUMB_X = 90;
	public static final int IMAGE_THUMB_Y = 65;
	
	public static final int noOfStoriesOnFirstPage = 4;
	
	public static final String metaExpiryNextYear = "Mon, 22 Jul 2011 11:12:01 GMT";
	public static final String metaExpiryExpired = "0";
	
	public static final Long CAT_HOLLYWOOD = 141009L;
	public static final Long CAT_BOLLYWOOD = 1001L;
	
	public static final int ENTITY_STATUS_ENABLED = 45;
	public static final int ENTITY_STATUS_DISABLED = 46;
}