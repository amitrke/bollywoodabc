package com.babc.server.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;

import com.babc.server.utils.ApplicationContextProvider;

public abstract class AbstractFilter implements Filter {
	
	protected ApplicationContext applicationContext;
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		if (applicationContext == null){
			applicationContext = ApplicationContextProvider.getCtx();
		}
	}

}
