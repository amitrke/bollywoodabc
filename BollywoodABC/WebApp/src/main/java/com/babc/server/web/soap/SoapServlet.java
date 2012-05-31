package com.babc.server.web.soap;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.springframework.context.ApplicationContext;

import com.babc.server.utils.ApplicationContextProvider;


public class SoapServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected ApplicationContext applicationContext;
	static MessageFactory messageFactory;
	private SOAPHandler soapHandler;

	  static {
	    try {
	      messageFactory = MessageFactory.newInstance();
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	  }


	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		  if (applicationContext == null){
				applicationContext = ApplicationContextProvider.getCtx();
				try {
					soapHandler = new SOAPHandler(applicationContext);
				} catch (SOAPException e) {
					throw new RuntimeException(e);
				}
		  }
	    try {
	      // Get all the headers from the HTTP request
	      MimeHeaders headers = getHeaders(req);

	      // Construct a SOAPMessage from the XML in the request body
	      InputStream is = req.getInputStream();
	      SOAPMessage soapRequest = messageFactory.createMessage(headers, is);

	      // Handle soapReqest
	      SOAPMessage soapResponse = soapHandler.handleSOAPRequest(soapRequest);

	      // Write to HttpServeltResponse
	      resp.setStatus(HttpServletResponse.SC_OK);
	      resp.setContentType("text/xml;charset=\"utf-8\"");
	      OutputStream os = resp.getOutputStream();
	      soapResponse.writeTo(os);
	      os.flush();
	    } catch (SOAPException e) {
	      throw new IOException("Exfception while creating SOAP message.", e);
	    }
	  }

	  @SuppressWarnings("unchecked")
	  static MimeHeaders getHeaders(HttpServletRequest req) {
	    Enumeration headerNames = req.getHeaderNames();
	    MimeHeaders headers = new MimeHeaders();
	    while (headerNames.hasMoreElements()) {
	      String headerName = (String) headerNames.nextElement();
	      String headerValue = req.getHeader(headerName);
	      StringTokenizer values = new StringTokenizer(headerValue, ",");
	      while (values.hasMoreTokens()) {
	        headers.addHeader(headerName, values.nextToken().trim());
	      }
	    }
	    return headers;
	  }

}
