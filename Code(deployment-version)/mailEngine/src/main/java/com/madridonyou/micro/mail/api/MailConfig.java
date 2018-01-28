package com.madridonyou.micro.mail.api;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {

	@Bean
	public JavaMailSenderImpl getJavaMailSenderImpl() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		//Amazon SES Service
//		mailSender.setHost("email-smtp.eu-west-1.amazonaws.com");
//		mailSender.setUsername("");
//		mailSender.setPassword("");
		mailSender.setUsername("lumostesting@gmail.com");
		mailSender.setPassword("lumostesting");
		mailSender.setHost("smtp.gmail.com"); 
		mailSender.setPort(465);
		Properties prop = mailSender.getJavaMailProperties();		
		prop.put("mail.transport.protocol", "smtps");
		prop.put("mail.smtps.auth", "true");
		prop.put("mail.smtps.starttls.enable", "true");
		prop.put("mail.smtps.starttls.required", "true");
		prop.put("mail.smtps.ssl.trust", "smtp.gmail.com");
		return mailSender;
	}
}
