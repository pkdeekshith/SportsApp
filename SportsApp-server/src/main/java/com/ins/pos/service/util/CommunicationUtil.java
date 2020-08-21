package com.ins.pos.service.util;

import java.net.URLEncoder;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CommunicationUtil {
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${sms.url}")
	private String smsURL;
	
	@Value("${sms.username}")
	private String smsUserName;
	
	@Value("${sms.password}")
	private String smsPassword;
	
	@Value("${sms.senderId}")
	private String smsSenderId;

	public boolean sendSms(String textMessage, String mobileNumber) {
		boolean status = false;
		try {

			String uri = smsURL.replace("$username", smsUserName).replace("$password", smsPassword).replace("$message", textMessage).replace("$mobNo", mobileNumber).replace("$sender", smsSenderId); 
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				status = true;
			}
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	public boolean sendEmail(String to, String subject, String mailbody) {
		boolean status = false;
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(mailbody, true);
			javaMailSender.send(msg);
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
}
