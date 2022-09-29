package com.cts.training.digitalbooknotificationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.training.digitalbooknotificationservice.dtos.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerListener {

	@Autowired
	NotificationService notificationService;

	ObjectMapper objectMapper = new ObjectMapper();

	private static final String TOPIC = "kafka-topic";

	@KafkaListener(topics = TOPIC, groupId = "group_new", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(String book) throws JsonMappingException, JsonProcessingException {
		// System.out.println("Consumed JSON Message: " + bookId);

		System.out.println(book + " : BookId");
		// System.out.println("after parsing to int bookid value is:" +
		// Integer.parseInt(bookId));

		Book book2 = objectMapper.readValue(book, Book.class);

		notificationService.sendNotification(book2);

	}
}
