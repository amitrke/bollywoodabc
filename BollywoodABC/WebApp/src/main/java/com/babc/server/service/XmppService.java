package com.babc.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.dao.StoryDao;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

@Service("xmppService")
public class XmppService {
	
	private transient static final Logger LOGGER = Logger.getLogger(XmppService.class.getName());
	
	private @Autowired StoryDao storyDao;
	
	public void onMessage(JID jid, String body){
		
		StringBuilder stringBuilder = new StringBuilder();
		if (body.equals("headlines")){
			List<StoryEntity> storyEntities = storyDao.get(new Paging(5,0));
			
			for(StoryEntity storyEntity: storyEntities){
				stringBuilder.append(storyEntity.getTitle());
				stringBuilder.append("\n");
			}
		}
		else if(body.equals("subscribe headlines")){
			stringBuilder.append("Under Construction");
		}
		else if(body.equals("unsubscribe")){
			stringBuilder.append("Under Construction");
		}
		else{
			stringBuilder.append("Valid options:\r");
			stringBuilder.append("headlines : Gets you last 5 bollywood headlines\r");
			stringBuilder.append("subscribe headlines : Updates you as and when things happen\r");
			stringBuilder.append("unsubscribe: Sick of babc pinging you.\r");
		}
		
		
		sendMessage(jid, stringBuilder.toString());
	}
	
	private void sendMessage(JID jid, String msgBody){
		
        Message msg = new MessageBuilder()
            .withRecipientJids(jid)
            .withBody(msgBody)
            .build();
                
        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        if (xmpp.getPresence(jid).isAvailable()) {
            SendResponse status = xmpp.sendMessage(msg);
            messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
        }

        if (!messageSent) {
        	LOGGER.info("Unable to send message to "+jid.getId());
        }
	}
	
	
}
