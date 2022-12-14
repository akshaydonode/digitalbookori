package com.cts.digitalbook.digitalbookbookservice.services;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookbookservice.clients.AuthorServiceClient;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.SubscribedBookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.Author;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;
import com.cts.digitalbook.digitalbookbookservice.repositories.BookRepository;

@Service
public class EntityDtoMapperImpl implements EntityDtoMapper {

	@Autowired
	AuthorServiceClient authorServiceClient;

	@Autowired
	BookRepository bookRepository;

	@Override
	@Transactional
	public BookDetailsDTO bookDetailsDTO(BookEntity bookEntity, Optional<Author> authorEntity) {

		BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();

		if (Objects.isNull(authorEntity)) {
			Optional<Author> authorEntityOpt = authorServiceClient.getAuthorByID(bookEntity.getAuthorId());

			bookDetailsDTO.setAuthorId(authorEntityOpt.get().getAuthorId());
			bookDetailsDTO.setAuthorName(authorEntityOpt.get().getAuthorName());
		} else {
			bookDetailsDTO.setAuthorId(authorEntity.get().getAuthorId());
			bookDetailsDTO.setAuthorName(authorEntity.get().getAuthorName());
		}
		bookDetailsDTO.setActive(bookEntity.getAtive());
		bookDetailsDTO.setBookId(bookEntity.getBookId());
		bookDetailsDTO.setCategory(bookEntity.getCategory());
		bookDetailsDTO.setContents(bookEntity.getContents());
		// bookDetailsDTO.setLogo(bookEntity.getLogo());
		bookDetailsDTO.setPrice(bookEntity.getPrice());
		bookDetailsDTO.setPublished(bookEntity.getPublished());
		bookDetailsDTO.setPublisher(bookEntity.getPublisher());
		bookDetailsDTO.setTitle(bookEntity.getTitle());
		bookDetailsDTO.setUpdateDate(bookEntity.getUpdateDate());

		return bookDetailsDTO;
	}

	@Override
	public BookDetailsDTO bookDetailsDTO(BookEntity bookEntity) {
		BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();

		Optional<Author> authorEntityOpt = authorServiceClient.getAuthorByID(bookEntity.getAuthorId());
		bookDetailsDTO.setAuthorId(authorEntityOpt.get().getAuthorId());
		bookDetailsDTO.setAuthorName(authorEntityOpt.get().getAuthorName());

		bookDetailsDTO.setActive(bookEntity.getAtive());
		bookDetailsDTO.setBookId(bookEntity.getBookId());
		bookDetailsDTO.setCategory(bookEntity.getCategory());
		bookDetailsDTO.setContents(bookEntity.getContents());
		// bookDetailsDTO.setLogo(bookEntity.getLogo());
		bookDetailsDTO.setPrice(bookEntity.getPrice());
		bookDetailsDTO.setPublished(bookEntity.getPublished());
		bookDetailsDTO.setPublisher(bookEntity.getPublisher());
		bookDetailsDTO.setTitle(bookEntity.getTitle());
		bookDetailsDTO.setUpdateDate(bookEntity.getUpdateDate());

		return bookDetailsDTO;

	}

	@Override
	//@Transactional
	public SubscribedBookDetailsDTO getBookDetails(int bookID, int readerId) {
		System.out.println("readerid "+readerId+" bookID:"+bookID);
		SubscribedBookDetailsDTO bookDetailsDTO = new SubscribedBookDetailsDTO();
		Optional<BookEntity> bookEntity = bookRepository.findByBookId(bookID);
		System.out.println(bookEntity.get().toString());
		Optional<Author> authorEntityOpt = authorServiceClient.getAuthorByID(bookEntity.get().getAuthorId());

		if (!authorEntityOpt.isEmpty()) {
			bookDetailsDTO.setAuthorName(authorEntityOpt.get().getAuthorName());
		}

		if (!bookEntity.isEmpty()) {
			bookDetailsDTO.setBookId(bookID);
			bookDetailsDTO.setBookTitle(bookEntity.get().getTitle());
			bookDetailsDTO.setCatogory(bookEntity.get().getCategory());
			bookDetailsDTO.setPrice(bookEntity.get().getPrice());
			bookDetailsDTO.setPublished(bookEntity.get().getPublished());
			bookDetailsDTO.setReaderId(readerId);
			bookDetailsDTO.setUpdatedDate(bookEntity.get().getUpdateDate());
			bookDetailsDTO.setContent(bookEntity.get().getContents());
			bookDetailsDTO.setPublisher(bookEntity.get().getPublisher());
		}

		return bookDetailsDTO;

	}

}
