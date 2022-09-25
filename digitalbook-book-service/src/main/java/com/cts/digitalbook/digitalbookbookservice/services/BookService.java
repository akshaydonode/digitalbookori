package com.cts.digitalbook.digitalbookbookservice.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookSearchDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;

public interface BookService {

	BookEntity createBookByAuthor(int authorId, MultipartFile image, BookEntity bookEntity) throws DigitalBookException;

	BookEntity updateBookDetails(int authorId, int bookId, BookEntity bookEntity) throws DigitalBookException;

	List<BookDetailsDTO> searchBook(BookSearchDTO bookSearchDTO) throws DigitalBookException;

	List<BookDetailsDTO> getAllBooks() throws DigitalBookException;

}
