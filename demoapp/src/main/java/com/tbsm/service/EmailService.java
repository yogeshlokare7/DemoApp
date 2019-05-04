package com.tbsm.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tbsm.service.impl.SocietyServiceImpl;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SocietyServiceImpl.class);

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	public void sendEmailHtml(String htmlContains, String email, String subject) throws javax.mail.MessagingException {
		try {
			MimeMessage mimemessage=mailSender.createMimeMessage();
			MimeMessageHelper mimeMessHelp=new MimeMessageHelper(mimemessage, true);
			mimemessage.setContent(htmlContains.trim(), "text/html");
			mimeMessHelp.setFrom("experienceamericacamps@gmail.com");
			mimeMessHelp.setTo(email);
			mimeMessHelp.setSubject(subject);
			mailSender.send(mimemessage);
		}
		catch (Exception e) {
			logger.info("Something is wrong with email. Please try again.");
		}
	}
}
