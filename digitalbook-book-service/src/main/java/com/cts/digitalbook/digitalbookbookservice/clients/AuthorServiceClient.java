package com.cts.digitalbook.digitalbookbookservice.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.digitalbook.digitalbookbookservice.entities.Author;

@FeignClient("AUTHOR-SERVICE")
public interface AuthorServiceClient {

	@GetMapping("author/getAuthorByID/{authorId}")
	Optional<Author> getAuthorByID(@PathVariable int authorId);

	@GetMapping("author/getAuthorByName/{authorName}")
	Optional<Author> getAuthorByName(@PathVariable String authorName);
}
