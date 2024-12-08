package com.sunil.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.sunil.kafka.dto.Consumer;

@Configuration
public class KafkaProducerConfig
{
	
	private static final Logger log = LoggerFactory.getLogger(KafkaProducerConfig.class);
	
	@Bean
	public NewTopic createNewStringTopic()
	{
		log.info("Creating topics");
		return new NewTopic("string_topic", 3, (short) 1);
	}
	
	@Bean
	public ProducerFactory<String, Consumer> producerFactory()
	{
		log.info("Initializing producerFactory");
		Map<String, Object> props=new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Consumer>(props);
	}
	
	@Bean
	public KafkaTemplate<String, Consumer> kafkaTemplate()
	{
		log.info("Initializing kafkaTemplate");
		return new KafkaTemplate<String, Consumer>(producerFactory());
	}
	
	@Bean
	public NewTopic createNewConsumerTopic()
	{
		log.info("Creating topics");
		return new NewTopic("consumer_topic", 3, (short) 3);
	}
	
	@Bean
	public ProducerFactory<String, String> stringProducerFactory()
	{
		log.info("Initializing stringProducerFactory");
		Map<String, Object> props=new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<String, String>(props);
	}
	
	@Bean
	public KafkaTemplate<String, String> stringKafkaTemplate()
	{
		log.info("Initializing stringKafkaTemplate");
		return new KafkaTemplate<String, String>(stringProducerFactory());
	}
	
}
