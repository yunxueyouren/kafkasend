package com.changyin58.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;




@Service
public class KafkaMessageSendService {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageSendService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	@Value("${kafka.app.topic.foo}")
	private String topic;

	public void send(String message) {

		LOG.info("topic=" + topic + ",message=" + message);
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		future.addCallback(success -> LOG.info("KafkaMessageProducer success！"),
				fail -> LOG.error("KafkaMessageProducer fail！"));
	}



	
	
	
}