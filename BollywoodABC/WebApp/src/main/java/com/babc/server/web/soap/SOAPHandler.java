package com.babc.server.web.soap;
import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import org.springframework.context.ApplicationContext;

import com.babc.server.web.soap.jaxws.GetRelatedStories;
import com.babc.server.web.soap.jaxws.GetTweets;

public class SOAPHandler {
	private static final String NAMESPACE_URI = "http://soap.web.server.babc.com/";
	  private static final QName VERSION = new QName(NAMESPACE_URI, "version");
	  private static final QName GET_RELATED_STORIES = new QName(NAMESPACE_URI, "getRelatedStories");
	  private static final QName GET_TWEETS = new QName(NAMESPACE_URI, "getTweets");
	  
	  private MessageFactory messageFactory;
	  private SoapAdaptor soapAdapter;

	  public SOAPHandler(ApplicationContext context) throws SOAPException {
	    messageFactory = MessageFactory.newInstance();
	    soapAdapter = new SoapAdaptor(context);
	  }

	  public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException {
	    SOAPBody soapBody = request.getSOAPBody();
	    Iterator iterator = soapBody.getChildElements();
	    Object responsePojo = null;
	    while (iterator.hasNext()) {
	      Object next = iterator.next();
	      if (next instanceof SOAPElement) {
	        SOAPElement soapElement = (SOAPElement) next;
	        QName qname = soapElement.getElementQName();
	          if (VERSION.equals(qname)) {
	            responsePojo = handleVersionRequest(soapElement);
	            break;
	          } 
	          else if (GET_RELATED_STORIES.equals(qname)) {
	            responsePojo = handleGetRelatedStoriesRequest(soapElement);
	            break;
	          }
	          else if (GET_TWEETS.equals(qname)) {
	            responsePojo = handleGetTweetsRequest(soapElement);
	            break;
	          }
	      }
	    }
	    SOAPMessage soapResponse = messageFactory.createMessage();
	    soapBody = soapResponse.getSOAPBody();
	    if (responsePojo != null) {
	      JAXB.marshal(responsePojo, new SAAJResult(soapBody));
	    } else {
	      SOAPFault fault = soapBody.addFault();
	      fault.setFaultString("Unrecognized SOAP request.");
	    }
	    return soapResponse;
	  }

	  private Object handleVersionRequest(SOAPElement soapElement) {
	    return soapAdapter.version();
	  }
	  
	  private Object handleGetRelatedStoriesRequest(SOAPElement soapElement){
		  GetRelatedStories getRelatedStories = JAXB.unmarshal(new DOMSource(soapElement), GetRelatedStories.class);
		  return soapAdapter.getRelatedStories(getRelatedStories.getArg0());
	  }
	  
	  private Object handleGetTweetsRequest(SOAPElement soapElement){
		  GetTweets getTweets = JAXB.unmarshal(new DOMSource(soapElement), GetTweets.class);
		  return soapAdapter.getTweets(getTweets.getArg0());
	  }
}
