package com.onlyxcodes.SendEmailAPI.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlyxcodes.SendEmailAPI.model.EmailRequest;
import com.onlyxcodes.SendEmailAPI.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService service;
	
	//this api send simple email
	
    @PostMapping(path = "/sendingemail",produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {
        System.out.println(request);
        boolean result = this.service.sendEmail(request.getSubject(), request.getMessage(), request.getTo());

        if(result){
            return  ResponseEntity.ok("Email Properly Sent Successfully... ");

        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
        }
    }
    
    @PostMapping("/netcoreMail")
    public HashMap<String, Object> netcoreMail (@RequestBody HashMap<String, Object> email)
    {
		return null;
    	
    }
}

//{
//	"subject" : "test email plain text",
//	"message" : "this email sending with simple text message",
//	"to" : "3kkuthe@gmail.com"
//}