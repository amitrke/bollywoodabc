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
import com.babc.server.model.PageCache;
import com.babc.server.utils.EntityCache;

public class CacheFilter extends AbstractFilter {
	
	private EntityCache entityCache;
	private transient static final Logger LOGGER = Logger.getLogger(CacheFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		super.doFilter(request, response, chain);
		if (entityCache == null){
			entityCache = (EntityCache) applicationContext.getBean("entityCache");
		}
		
		PrintWriter out = response.getWriter();
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		
		if (url.contains(".gif") || url.contains(".css") || url.contains(".js")){
			//response.setContentType("image/gif");
			((HttpServletResponse) response).setDateHeader("Expires",
					   System.currentTimeMillis(  ) + AppConstants.BROWSER_CACHE_DEF_EXPIRY);
			LOGGER.debug("Static headers attacted.");
		}
		else if((url.contains("/") || url.contains("/news/") || url.contains("/photogallery/")) && !url.contains("/admin/") && !url.contains("/batch/") && !url.contains("/mq/")){
			PageCache pageCache = (PageCache) entityCache.getEntity(PageCache.class, url);
			if(pageCache == null){
				LOGGER.debug("Page Cache MISS; url="+url);
				ServletResponse responseWrapper = new CharResponseWrapper((HttpServletResponse)response);
				chain.doFilter(request, responseWrapper);
				pageCache = new PageCache(responseWrapper.toString());
				entityCache.putEntity(PageCache.class, url, pageCache);
			}
			out.print(pageCache.getData());
			return;
		}
		LOGGER.debug("CacheFilter else.");
		chain.doFilter(request, response);
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
