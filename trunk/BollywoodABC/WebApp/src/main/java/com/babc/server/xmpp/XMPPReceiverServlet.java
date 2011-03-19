package com.babc.server.xmpp;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.babc.server.service.XmppService;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@SuppressWarnings("serial")
public class XMPPReceiverServlet extends HttpServlet{
	
	private transient static final Logger LOGGER = Logger.getLogger(XMPPReceiverServlet.class.getName());
	private XmppService xmppService;
	
	ApplicationContext applicationContext = null;
	
    @Override
	public void init() throws ServletException {
		super.init();
		xmppService = new XmppService();
	}
    
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
          throws IOException {
		
		if (applicationContext == null){
			System.out.println("setting context in get");
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			xmppService = (XmppService) applicationContext.getBean("xmppService");
		}
		
		XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        Message message = xmpp.parseMessage(req);

        JID fromJid = message.getFromJid();
        String body = message.getBody();
        LOGGER.info("xmpp message from "+fromJid.getId()+" - "+body);
        xmppService.onMessage(fromJid, body);
    }
}