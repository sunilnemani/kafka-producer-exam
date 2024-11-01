package com.sunil.kafka.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sunil.kafka.dto.Consumer;

@Service
public class KafkaMessagePublisherService
{
	
//	private static final Logger log = LoggerFactory.getLogger(KafkaMessagePublisherService.class);
	
	@Autowired
	private KafkaTemplate<String, Consumer> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, String> stringKafkaTemplate;
	
	public void sendMessageToTopic(Consumer consumer)
	{
		kafkaTemplate.send("consumer_topic", null, consumer);
	}

	public void sendStringMessageToTopic(String name)
	{
		stringKafkaTemplate.send("string_topic", null, name);
	}

}
