package com.babc.server.web.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.google.apphosting.api.ApiProxy.OverQuotaException;

public abstract class AbstractBaseController {
	
	private static final Log logger = LogFactory.getLog(AbstractBaseController.class);
		
	@ExceptionHandler(OverQuotaException.class)
	public ModelAndView handleException(OverQuotaException ex) {
		logger.error(ex);
		ModelAndView mav = new ModelAndView("ui.error", "error", "BollywoodABC is Overcapacity, Please visit us later.");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		logger.error(ex);
		ModelAndView mav = new ModelAndView("ui.error", "error", "A Server Error has occured, Please visit us later.");
		return mav;
	}
}
