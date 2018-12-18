package com.changyin58.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.changyin58.service.KafkaMessageSendService;



@RestController
@RequestMapping("/send")
public class KafkaMessageSend {

   @Autowired
    private KafkaMessageSendService kafkaMessageSendService;
 
    @RequestMapping("/sendMessage")
    public String send(/*@RequestParam(required=false) String message*/ ){
        try {
        	 String message = "Hello 888---";
            kafkaMessageSendService.send(message);
            return message;
        } catch (Exception e) {
            return "send failed.";
        }
        
       
    }
}
