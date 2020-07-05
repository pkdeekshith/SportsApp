package com.ins.pos.service.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
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

	public boolean sendSms(String yourapiKey, String textMessage, String textSender, String mobileNumber) {
		boolean status = false;
		try {

			String apiKey = "apikey=" + URLEncoder.encode(yourapiKey, "UTF-8");
			String message = "&message=" + URLEncoder.encode(textMessage, "UTF-8");
			String sender = "&sender=" + URLEncoder.encode(textSender, "UTF-8");
			String numbers = "&numbers=" + URLEncoder.encode(mobileNumber, "UTF-8");

			// Send data
			String uri = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;

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
