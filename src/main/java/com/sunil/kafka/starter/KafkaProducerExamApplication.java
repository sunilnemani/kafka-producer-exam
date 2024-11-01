package com.sunil.kafka.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sunil.*")
public class KafkaProducerExamApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(KafkaProducerExamApplication.class, args);
	}
}
