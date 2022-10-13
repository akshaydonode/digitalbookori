package com.cts.digitalbook.digitalbookbookservice.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookbookservice.clients.AuthorServiceClient;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookEntityDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookSearchDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.ResponseDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.SubscribedBookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.Author;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;
import com.cts.digitalbook.digitalbookbookservice.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorServiceClient authorServiceClient;

	@PostMapping("/{authorId}/books")
	public ResponseDTO createBook(
			@PathVariable int authorId, /* @RequestPart MultipartFile image, */
			@RequestBody BookEntityDTO bookEntity) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			BookEntity bookEntity2 = bookService.createBookByAuthor(authorId, /* image, */ bookEntity);
			responseDto.setResult(bookEntity2);
			responseDto.setMessage("Book Created Successfully.");
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;

	}

	@PutMapping("/{authorId}/books/{bookId}")
	public ResponseDTO updateBookDetails(@PathVariable int authorId, @PathVariable int bookId,
			@RequestBody BookEntity bookEntity) {
		ResponseDTO responseDto = new ResponseDTO();
		try {
			BookEntity bookEntity2 = bookService.updateBookDetails(authorId, bookId, bookEntity);
			responseDto.setResult(bookEntity2);
			responseDto.setMessage("Book Updated Successfully.");
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}

	@PostMapping("/search")
	public ResponseDTO searchBook(@RequestBody BookSearchDTO bookSearchDTO) {
		ResponseDTO responseDto = new ResponseDTO();
		//ResponseEntity<Object> responseEntity =new res
		System.out.println("search called anf fields:"+bookSearchDTO.toString());
		List<BookDetailsDTO> bookEntities;
		try {
			bookEntities = bookService.searchBook(bookSearchDTO);
			//List<Object> response = new ArrayList<>();
			//response.add(bookEntities);
			responseDto.setMessage("Book Found Successfully");
			responseDto.setResultArray(bookEntities.toArray());
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("/getAuthor/{authorId}")
	public Author getAuthorFromAuthorService(@PathVariable int authorId) {
		Optional<Author> author = authorServiceClient.getAuthorByID(authorId);

		return author.get();
	}

	@GetMapping("getAllBooks")
	public ResponseDTO getAllBooks() {
		ResponseDTO responseDto = new ResponseDTO();

		List<BookDetailsDTO> bookEntities;
		try {
			bookEntities = bookService.getAllBooks();
			List<Object> response = new ArrayList<>();
			response.add(bookEntities);
			responseDto.setResponse(response);
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("/{emailID}/books")
	public ResponseDTO getSubscribeBooks(@PathVariable("emailID") String readerEmailId) {
		ResponseDTO responseDto = new ResponseDTO();
		List<SubscribedBookDetailsDTO> subscribedBookDetails;
		try {
			subscribedBookDetails = bookService.getReaderSubscribeBook(readerEmailId);

			responseDto.setMessage("Book Found Successfully");
			responseDto.setResultArray(subscribedBookDetails.toArray());
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("/reader/{bookId}")
	public Optional<BookDetailsDTO> getBookDetails(@PathVariable int bookId) {
		return bookService.getBookDetails(bookId);
	}

	@PostMapping("/block/{authorId}/{bookId}")
	public ResponseDTO blockBook(@PathVariable int authorId, @PathVariable int bookId) {
		ResponseDTO responseDto = new ResponseDTO();
		try {
			String message = bookService.blockBook(authorId, bookId);
			responseDto.setMessage(message);
		} catch (Exception e) {
			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}
	
	@GetMapping("/books/{authorId}")
	public ResponseDTO getAuthorBooks(@PathVariable int authorId) {
		ResponseDTO responseDto = new ResponseDTO();
		List<BookDetailsDTO> bookEntities;
		try {
			bookEntities = bookService.getAuthorBooks(authorId);
			responseDto.setMessage("Book Found Successfully");
			responseDto.setResultArray(bookEntities.toArray());
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}
		
		return responseDto;
	}

}
