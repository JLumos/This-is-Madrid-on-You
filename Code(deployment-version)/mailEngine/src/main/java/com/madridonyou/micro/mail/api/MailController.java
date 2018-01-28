package com.madridonyou.micro.mail.api;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madridonyou.micro.domain.inputs.Attachment;
import com.madridonyou.micro.domain.inputs.MailInput;
import com.madridonyou.micro.domain.outputs.MailOutput;

@RestController
public class MailController implements MailAPI{

	public ResponseEntity<MailOutput> login(@RequestBody @Valid MailInput input) {

		MailOutput responseBody = new MailOutput();
		HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MailConfig.class);
		context.refresh();
		JavaMailSenderImpl mailSender = context.getBean(JavaMailSenderImpl.class);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageBuilder;
		try {
			messageBuilder = new MimeMessageHelper(message, true);
			messageBuilder.setFrom("Madrid On You <lumostesting@mail.com>");
			messageBuilder.setTo(input.getEmail() != null ? input.getEmail():"jesusperezmelero@gmail.com");
			messageBuilder.setSubject(input.getSubject() != null ? input.getSubject() : "Test");
			if (input.getAttachments() != null && input.getAttachments().size() > 0)
				this.attachDocuments(input.getAttachments());
			message.setContent(input.getBody() != null ? input.getBody() : "Error sending mail :(", "text/html");
			mailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			responseBody.setStatus(e.getMessage());
		}
		context.close();
		responseBody.setStatus("Mail Sent");
		responseStatus = HttpStatus.OK;
		return new ResponseEntity<MailOutput>(responseBody, responseStatus);
	}

	private void attachDocuments(List<Attachment> attachments) {
		
		
	}


}
