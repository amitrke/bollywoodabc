package com.babc.server.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babc.server.AppConstants;
import com.babc.server.dao.MailQueueDao;
import com.babc.server.dao.StoryDao;
import com.babc.server.dao.SubscriptionDao;
import com.babc.server.dao.UserDao;
import com.babc.server.model.MailQueueEntity;
import com.babc.server.model.Paging;
import com.babc.server.model.StoryEntity;
import com.babc.server.model.SubscriptionEntity;
import com.babc.server.model.UserEntity;
import com.babc.server.model.vo.TweetListVo;

@Service("mailService")
public class MailService {
	
	private @Autowired UserDao userDao;
	private @Autowired MailQueueDao mailQueueDao;
	private @Autowired SubscriptionDao subscriptionDao;
	private @Autowired StoryDao storyDao;
	private @Autowired TwitterService twitterService;
	
	private static transient final Log LOGGER = LogFactory.getLog(MailService.class);
	
	public void subscribeForNewsletter(InternetAddress internetAddress){
		//Check if the user is already in user table
		UserEntity userEntity = userDao.getByEmail(internetAddress.getAddress());
		if (userEntity==null){
			userEntity = new UserEntity(internetAddress.getPersonal(), internetAddress.getAddress());
			userEntity = userDao.save(userEntity);
		}
		
		MailQueueEntity mailQueueEntity = new MailQueueEntity();
		
		//Check if user is in subscription table, add if not.
		SubscriptionEntity subscriptionEntity = subscriptionDao.get(userEntity.getId(), AppConstants.SUBSCRIPTION_WEEKLY_NEWSLETTER);
		if (subscriptionEntity == null){
			subscriptionEntity = new SubscriptionEntity(userEntity.getId(), AppConstants.SUBSCRIPTION_WEEKLY_NEWSLETTER);
			subscriptionDao.save(subscriptionEntity);
			
			//Send the user an email that he has been successfully subscribed.
			mailQueueEntity = new MailQueueEntity(userEntity.getId(), 
					"Greetings,<p>You have been Successfully subscribed to BollywoodABC Weekly newsletter" +
					"<p>You can also add <b>bollyabc@appspot.com</b> to your Google Talk messenger to keep yourself" +
					" updated with Bollywood world as and when something happens.</p>" + getMailFooter()
					,"text/html", "You have been Successfully subscribed", 1);
		}
		else{
			//Send the user an email that he is already subscribed.
			mailQueueEntity = new MailQueueEntity(userEntity.getId(), 
					"Greetings,<p>Thanks for reaching BollywoodABC, Your request to subscribe for newsletter could not" +
					"be completed as you are already subscribed.</p>" +
					"<p>You can also add <b>bollyabc@appspot.com</b> to your Google Talk messenger to keep yourself" +
					" updated with Bollywood world as and when something happens.</p>" + getMailFooter()
					,"text/html", "You are already subscribed to this list", 1);
		}
		
		//Add to mail queue.
		mailQueueDao.save(mailQueueEntity);
		
		//Process mail queue.
		processMailQueue(2);
	}
	
	private String getMailFooter(){
		return "<p>To unsubscribe send an email to unsubscribe@bollyabc.appspotmail.com, with subject unsubscribe." + 
		"Thanks,<br>BollywoodABC Team.<br> <A href='http://www.bollywoodabc.com/'>BollywoodABC.com</A></p>";
	}
	
	public void unsubscribeNewsletter(InternetAddress internetAddress){
		//Check if the user is already in user table
		UserEntity userEntity = userDao.getByEmail(internetAddress.getAddress());
		if (userEntity==null){
			userEntity = new UserEntity(internetAddress.getPersonal(), internetAddress.getAddress());
			userEntity = userDao.save(userEntity);
			sendMailUserNotSubscribed(userEntity);
		}
		else{
			//Check if user is in subscription list
			SubscriptionEntity subscriptionEntity = subscriptionDao.get(userEntity.getId(), AppConstants.SUBSCRIPTION_WEEKLY_NEWSLETTER);
			if (subscriptionEntity == null){
				sendMailUserNotSubscribed(userEntity);
			}
			else{
				//Unsubscribe the user
				subscriptionDao.remove(subscriptionEntity.getId());
				sendMailUserUnSubscribed(userEntity);
			}
		}
	}
	
	private void sendMailUserUnSubscribed(UserEntity userEntity){
		MailQueueEntity mailQueueEntity = new MailQueueEntity(userEntity.getId(), 
				"Greetings,<p>Thanks for reaching BollywoodABC, You have been sucessfully unsubscribed" +
				".</p>" +
				"<p>You can also add <b>bollyabc@appspot.com</b> to your Google Talk messenger to keep yourself" +
				" updated with Bollywood world as and when something happens.</p>" + getMailFooter()
				,"text/html", "You have been sucessfully unsubscribed from this list", 1);
		//Add to mail queue.
		mailQueueDao.save(mailQueueEntity);
		
		//Process mail queue.
		processMailQueue(2);
	}
	
	private void sendMailUserNotSubscribed(UserEntity userEntity){
		MailQueueEntity mailQueueEntity = new MailQueueEntity(userEntity.getId(), 
				"Greetings,<p>Thanks for reaching BollywoodABC, Your request to unsubscribe for newsletter could not" +
				"be completed as you are not subscribed for newsletter.</p>" +
				"<p>You can also add <b>bollyabc@appspot.com</b> to your Google Talk messenger to keep yourself" +
				" updated with Bollywood world as and when something happens.</p>" + getMailFooter()
				,"text/html", "You are not subscribed to this list", 1);
		//Add to mail queue.
		mailQueueDao.save(mailQueueEntity);
		
		//Process mail queue.
		processMailQueue(2);
	}
	
	public void sendWeeklyNewsletter(){
		List<SubscriptionEntity> subscriptionEntities = subscriptionDao.get(AppConstants.SUBSCRIPTION_WEEKLY_NEWSLETTER);
		
		String newsletterContent = createWeeklyNewsletter();
		if (newsletterContent != null){
			for(SubscriptionEntity subscriptionEntity:subscriptionEntities){
				MailQueueEntity mailQueueEntity = new MailQueueEntity(subscriptionEntity.getUserId(), newsletterContent,
						"text/html", "BollywoodABC.com Weekly Newsletter", 9);
				mailQueueDao.save(mailQueueEntity);
			}
		}
		else{
			LOGGER.info("Could not send newsletter, as there were no stories to send.");
		}
		
	}
	
	private String createWeeklyNewsletter(){
		StringBuilder nl = new StringBuilder();
		
		//Get today's date and one week ago date.
		Date todaysDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(todaysDate);
		calendar.add(Calendar.DAY_OF_MONTH, -14);
		Date oneWeekAgo = calendar.getTime();
		
		nl.append("<p><b>Recent stories:</b></p>");
		
		List<StoryEntity> storyEntities = storyDao.get(oneWeekAgo, todaysDate);
		
		if (storyEntities.size() < 1) return null;
		
		for(StoryEntity storyEntity: storyEntities){
			nl.append("<p><b><A HREF='http://www.bollywoodabc.com");
			nl.append(storyEntity.getStoryUrl());
			nl.append("'>");
			nl.append(storyEntity.getTitle());
			nl.append("</A></b><br>");
			nl.append(storyEntity.getIntro());
			nl.append("</p>");
		}
		
		
		nl.append("<p><b>Recent Tweets:</b></p>");
		List<TweetListVo> tweetListVos = twitterService.getTweetList(new Paging());
		for(TweetListVo tweetListVo: tweetListVos){
			nl.append("<p><b>");
			nl.append(tweetListVo.getName());
			nl.append("</b> ");
			nl.append(tweetListVo.getTweet());
			nl.append("</p>");
		}
		
		nl.append(getMailFooter());
		return nl.toString();
	}
	
	public void processMailQueue(int priority){
		List<MailQueueEntity> mailQueueEntities = mailQueueDao.getByPriority(priority);
		for(MailQueueEntity mailQueueEntity: mailQueueEntities){
			UserEntity userEntity = userDao.getById(mailQueueEntity.getUserId());
			if (sendMail(userEntity.getEmail(), mailQueueEntity.getSubject(), mailQueueEntity.getBody().getValue())){
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
	                 new InternetAddress(to));
			msg.setSubject(subject);
	        msg.setText(body);
	        msg.setContent(body, "text/html");
	        Transport.send(msg);
	        return true;
		} catch (UnsupportedEncodingException e1) {
			LOGGER.error(e1);
		} catch (MessagingException e1) {
			LOGGER.error(e1);
		}
		return false;
	}
}