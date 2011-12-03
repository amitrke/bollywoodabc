package com.babc.server.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CatchAllMailServlet extends AbstractMailServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			System.out.println(message.getSender().getType());
		} catch (MessagingException e) {
			LOGGER.error(e);
		}
	}
	
	
}
