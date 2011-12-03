package com.babc.server.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.AppConstants;
import com.babc.server.dao.MailQueueDao;
import com.babc.server.dao.UserDao;
import com.babc.server.model.MailQueueEntity;
import com.babc.server.model.UserEntity;
import com.google.appengine.api.datastore.Text;

@Service("mailService")
public class MailService {
	
	private @Autowired UserDao userDao;
	private @Autowired MailQueueDao mailQueueDao;
	
	public void subscribeForNewsletter(String emailAddress){
		//Check if the user is already in user table
		UserEntity userEntity = null;
		if (userDao.getByEmail(emailAddress)==null){
			userEntity = new UserEntity(null, emailAddress);
			userEntity = userDao.save(userEntity);
		}
		
		//Add to mail queue.
		MailQueueEntity mailQueueEntity = new MailQueueEntity(userEntity.getId(), 
				new Text("body"), "TEXT", "You have been Successfully subscribed", 1);
		mailQueueDao.save(mailQueueEntity);
	}
	
	public void processDayMailQueue(){
		processMailQueue(5);
	}
	
	public void processNightMailQueue(){
		processMailQueue(10);
	}
	
	private void processMailQueue(int priority){
		List<MailQueueEntity> mailQueueEntities = mailQueueDao.getByPriority(priority);
		for(MailQueueEntity mailQueueEntity: mailQueueEntities){
			if (sendMail(mailQueueEntity.getSubject(), mailQueueEntity.getSubject(), mailQueueEntity.getBody().getValue())){
				mailQueueEntity.setDeliveryDate(new Date());
				mailQueueEntity.setStatus(AppConstants.MAIL_Q_STATUS_DELIVERED);
				mailQueueDao.save(mailQueueEntity);
			}
		}
	}
	
	public boolean sendMail(String to, String subject, String body ){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        Message msg = new MimeMessage(session);
        try {
			msg.setFrom(new InternetAddress("admin@bollyabc.appspotmail.com", "BollywoodABC.com Admin"));
			msg.addRecipient(Message.RecipientType.TO,
	                 new InternetAddress(to, "Mr. User"));
			msg.setSubject(subject);
	        msg.setText(body);
	        Transport.send(msg);
	        return true;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		return false;
	}
}
