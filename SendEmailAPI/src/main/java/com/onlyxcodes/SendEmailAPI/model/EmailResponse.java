package com.onlyxcodes.SendEmailAPI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    private int Status;
    private int ErrorCode;
    private String MessageType;
    private String Message;
//    private EmailRequestBody email;
    //(1 ,0 , "I" , "sucess" , list.get(0));
}
