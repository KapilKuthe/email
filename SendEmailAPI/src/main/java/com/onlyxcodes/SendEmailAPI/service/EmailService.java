package com.onlyxcodes.SendEmailAPI.service;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailService {

	private RestTemplate restTemplate;

	public boolean sendEmail(String subject, String message, String to) {
		boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code
								// process email

		String senderEmail = "heytest.api@gmail.com";
		String senderPassword = "hflcsrdauqhctwzz";

		// Properties class enables us to connect to the host SMTP server
		Properties properties = new Properties();

		// Setting necessary information for object property
		properties.put("mail.smtp.auth", "true"); // enable authentication
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Mention the SMTP server address. Here Gmail's SMTP server
															// is being used to send email
		properties.put("mail.smtp.port", "587"); // 587 is TLS port number
		properties.put("mail.smtp.starttls.enable", "true"); // enable TLS-protected connection

		System.out.println("Authenticator further");
//	    get the session object and pass username and password
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		};

		Session session = Session.getInstance(properties, auth);

		try {

			MimeMessage msg = new MimeMessage(session); // Create a default MimeMessage object for compose the message
			msg.setFrom(new InternetAddress("kapil.kuthe@heytrous.com")); // adding sender email id to msg object
			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));// adding recipient to msg object
//	        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));// adding recipient to msg object
			msg.setSubject(subject); // adding subject to msg object
			msg.setText(message); // adding text to msg object

			Transport.send(msg); // Transport class send the message using send() method
			System.out.println("Email Sent Successfully...");

			foo = true; // Set the "foo" variable to true after successfully sending emails

		} catch (Exception e) {
			System.out.println("EmailService File Error" + e);
		}

		return foo; // and return foo variable
	}

	public HashMap<String, Object> netcoreMail(@RequestBody HashMap<String, Object> email) {

		String url = "https://emailapi.netcorecloud.net/v5.1/mail/send";
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> map = new HashMap<>();

		responseData.put("email", email.get("email"));
		responseData.put("reply_to", email.get("email"));
		responseData.put("subject", email.get("email"));
		responseData.put("tags", email.get("email"));
		responseData.put("value", email.get("email"));

		responseData.put("template_id", 0);
		responseData.put("type", "html");

		restTemplate = new RestTemplate();

		System.out.println(url);
		try {

			HttpEntity<HashMap<String, Object>> entity = new HttpEntity<>(responseData);
			HttpEntity<String> result = restTemplate.postForEntity(url, entity, String.class);

			System.out.println(result);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(result.getBody());
			map.put("info", jsonNode);
			return map;

		} catch (Exception e) {

			map.put("info", "error");
		}
		return map;
	}

}
