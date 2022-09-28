package com.cts.training.digitalbooknotificationservice.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class KafkaConsumerListener {

	private static final String TOPIC = "kafka-topic";

	

	@KafkaListener(topics = TOPIC, groupId = "group_new", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(String bookId) throws JsonMappingException, JsonProcessingException {
		System.out.println("Consumed JSON Message: " + bookId);
	
		System.out.println(bookId+" : BookId");


	}
}
