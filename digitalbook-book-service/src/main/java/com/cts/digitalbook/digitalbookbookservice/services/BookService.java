package com.cts.digitalbook.digitalbookbookservice.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookEntityDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookSearchDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.SubscribedBookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {

	BookEntity createBookByAuthor(int authorId,
			/* MultipartFile image, */ @Valid BookEntityDTO bookEntity) throws DigitalBookException;

	BookEntity updateBookDetails(int authorId, int bookId, BookEntity bookEntity) throws DigitalBookException;

	List<BookDetailsDTO> searchBook(BookSearchDTO bookSearchDTO) throws DigitalBookException;

	List<BookDetailsDTO> getAllBooks() throws DigitalBookException;

	List<SubscribedBookDetailsDTO> getReaderSubscribeBook(String readerEmailId) throws DigitalBookException;

	Optional<BookDetailsDTO> getBookDetails(int bookId);

	String blockBook(int authorId, int bookId) throws DigitalBookException, JsonProcessingException;

	List<BookDetailsDTO> getAuthorBooks(int authorId) throws DigitalBookException;

}
