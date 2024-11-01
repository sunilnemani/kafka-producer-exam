package com.sunil.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.kafka.dto.Consumer;
import com.sunil.kafka.service.KafkaMessagePublisherService;

@RestController
@RequestMapping("/producerApp")
public class KafkaController
{
	
	private static final Logger log= LoggerFactory.getLogger(KafkaController.class);
	
	@Autowired
	private KafkaMessagePublisherService service;
	
	@GetMapping("/publishString")
	public ResponseEntity<String> stringPublishMessage(@RequestParam(name = "name") String name)
	{
		try
		{
			service.sendStringMessageToTopic("Hi "+name+", : Sending message");
			return ResponseEntity.ok("Successfully published");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/publish")
	public ResponseEntity<?> publishMessage(@RequestBody Consumer consumer)
	{
		try
		{
			log.info(consumer.toString());
			service.sendMessageToTopic(consumer);
			return ResponseEntity.ok("Successfully published");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
