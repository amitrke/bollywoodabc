package com.babc.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.babc.server.AppConstants;
import com.babc.server.dao.PageCacheDao;
import com.babc.server.model.PageCacheEntity;
import com.babc.server.model.PageType;
import com.babc.server.utils.EntityCache;

public class CacheFilter extends AbstractFilter {
	
	private EntityCache entityCache;
	private PageCacheDao pageCacheDao;
	private transient static final Logger LOGGER = Logger.getLogger(CacheFilter.class.getName());
	private boolean cacheEnabled = true;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(request, response, chain);
		if (entityCache == null){
			entityCache = (EntityCache) applicationContext.getBean("entityCache");
			pageCacheDao = (PageCacheDao) applicationContext.getBean("pageCacheDao");
		}
		
		PrintWriter out = response.getWriter();
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		LOGGER.debug("URL:"+url);
		if (url.contains(".gif") || url.contains(".css") || url.contains(".js")){
			((HttpServletResponse) response).setDateHeader("Expires",
					   System.currentTimeMillis(  ) + AppConstants.BROWSER_CACHE_DEF_EXPIRY);
			LOGGER.debug("Static headers attacted.");
		}
		else if (cacheEnabled && url.contains("/news/") && !url.contains("/disclaimer.htm") && !url.contains("/jetty.htm")){
			PageType pageType;
			
			if (url.contains("/latest/")){
				pageType = PageType.STORY_ALL_LIST;
			}
			else if(url.contains("/tweets/")){
				pageType = PageType.TWEET_LIST;
			}
			else if(url.contains("/bollywood/")){
				pageType = PageType.BOLLYWOOD_LIST;
			}
			else if(url.contains("/hollywood/")){
				pageType = PageType.HOLLYWOOD_LIST;
			}
			else if(url.contains("/home.htm")){
				pageType = PageType.HOME;
			}
			else{
				//Story
				pageType = PageType.STORY;
			}
			LOGGER.debug("PageType identified as "+pageType.name());
			Long pageCacheId = pageType.getPageCacheId(url);
			PageCacheEntity pageCacheEntity = getCache(pageCacheId, request, response, chain);
			out.print(pageCacheEntity.getData());
			return;
		}
		else if (cacheEnabled && url.contains("/photogallery/")){
			
			PageType pageType;
			
			if (url.contains("/wallpaper/")){
				pageType = PageType.PHOTO_DETAIL;
			}
			else if(url.contains("/main.htm")){
				pageType = PageType.PHOTOGALLERY_HOME;
			}
			else{
				pageType = PageType.PHOTOGALLERY_DETAIL;
			}
			LOGGER.debug("PageType identified as "+pageType.name());
			Long pageCacheId = pageType.getPageCacheId(url);
			PageCacheEntity pageCacheEntity = getCache(pageCacheId, request, response, chain);
			out.print(pageCacheEntity.getData());
			return;
		}
		else if(cacheEnabled && url.contains("/t/")){
			PageType pageType = PageType.TAG_DETAIL;
			LOGGER.debug("PageType identified as "+pageType.name());
			Long pageCacheId = pageType.getPageCacheId(url);
			PageCacheEntity pageCacheEntity = getCache(pageCacheId, request, response, chain);
			out.print(pageCacheEntity.getData());
			return;
		}
		
		LOGGER.debug("CacheFilter else.");
		chain.doFilter(request, response);
	}
	
	private PageCacheEntity getCache(Long pageCacheId, ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		//Try to fetch the page from entity cache
		PageCacheEntity pageCacheEntity = (PageCacheEntity) entityCache.getEntity(PageCacheEntity.class, pageCacheId);
		if(pageCacheEntity == null || !pageCacheEntity.isValid()){
			LOGGER.debug("Attempt to fetch page from memcache failed, will now try to fetch cache from DB");
			//Try to fetch from DB cache
			pageCacheEntity = pageCacheDao.getById(pageCacheId);
			if (pageCacheEntity == null || !pageCacheEntity.isValid()){
				//Page not found in DB cache.
				LOGGER.debug("Attempt to fetch page cache from DB failed, will now try to create page");
				ServletResponse responseWrapper = new CharResponseWrapper((HttpServletResponse)response);
				chain.doFilter(request, responseWrapper);
				pageCacheEntity = new PageCacheEntity(pageCacheId, responseWrapper.toString());
				
				//Save page in DB cache
				pageCacheDao.save(pageCacheEntity);
				
				//Save page in memcache
				entityCache.putEntity(PageCacheEntity.class, pageCacheId, pageCacheEntity);
			}
			else{
				//Page found in DB cache.
				
				//Save page in memcache
				entityCache.putEntity(PageCacheEntity.class, pageCacheId, pageCacheEntity);
			}
		}
		return pageCacheEntity;
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
