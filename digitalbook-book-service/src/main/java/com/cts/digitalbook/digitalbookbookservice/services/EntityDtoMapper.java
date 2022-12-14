package com.cts.digitalbook.digitalbookbookservice.services;

import java.util.Optional;

import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.SubscribedBookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.Author;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;

public interface EntityDtoMapper {

	public BookDetailsDTO bookDetailsDTO(BookEntity bookEntity, Optional<Author> authorEntity);

	public BookDetailsDTO bookDetailsDTO(BookEntity bookEntity2);

	public SubscribedBookDetailsDTO getBookDetails(int bookID, int readerId);
}
