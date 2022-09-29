package com.cts.training.digitalbooknotificationservice.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.training.digitalbooknotificationservice.dtos.SubscriptionDetailsDTO;


@FeignClient("READER-SERVICE")
public interface ReaderServiceClient {

	@GetMapping("/reader/getReaderByBookId/{bookId}")
	Optional<SubscriptionDetailsDTO> getReaderDetailsByBookId(@PathVariable int bookId);

}
