package com.cts.digitalbook.digitalbookreaderservice.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.digitalbook.digitalbookreaderservice.dtos.BookDetailsDTO;

@FeignClient("BOOK-SERVICE")
public interface BookServiceClient {

	@GetMapping("/book/reader/{bookId}")
	Optional<BookDetailsDTO> getBookDetails(@PathVariable int bookId);

}
