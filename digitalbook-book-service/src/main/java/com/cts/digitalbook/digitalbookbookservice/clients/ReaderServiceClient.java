package com.cts.digitalbook.digitalbookbookservice.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookbookservice.entities.SubscribeDetailsEntity;

@FeignClient("READER-SERVICE")
public interface ReaderServiceClient {

	@GetMapping("/reader/getSubscribeBook/{readerEmailId}")
	Optional<SubscribeDetailsEntity> getSubscribedBookDetails(@PathVariable String readerEmailId) throws DigitalBookException;

	@GetMapping("/reader/updateBlockBookDetails/{bookId}")
	String updateBlockBookDetails(@PathVariable int bookId);
	
	@GetMapping("/reader/updateUnBlockBookDetails/{bookId}")
	String updateUnBlockBookDetails(@PathVariable int bookId);
}
