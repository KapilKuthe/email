package com.onlyxcodes.SendEmailAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    private String to;
    private String cc;
    private String Bcc;
    private String subject;
    private String message;

    
}