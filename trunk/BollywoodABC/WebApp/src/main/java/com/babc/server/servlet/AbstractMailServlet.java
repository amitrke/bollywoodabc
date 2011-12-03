package com.babc.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.babc.server.service.MailService;
import com.babc.server.service.XmppService;
import com.babc.server.utils.ApplicationContextProvider;

public abstract class AbstractMailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected transient static final Logger LOGGER = Logger.getLogger(AbstractMailServlet.class.getName());
	protected MailService mailService;
	ApplicationContext applicationContext = null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (applicationContext == null){
			applicationContext = ApplicationContextProvider.getCtx();
			mailService = (MailService) applicationContext.getBean("mailService");
		}
	}
}
