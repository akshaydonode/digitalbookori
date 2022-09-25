package com.cts.digitalbook.digitalbookbookservice.services;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookbookservice.clients.AuthorServiceClient;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.Author;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;

@Service
public class EntityDtoMapperImpl implements EntityDtoMapper {

	@Autowired
	AuthorServiceClient authorServiceClient;

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
		bookDetailsDTO.setActive(bookEntity.getActive());
		bookDetailsDTO.setBookId(bookEntity.getBookId());
		bookDetailsDTO.setCategory(bookEntity.getCategory());
		bookDetailsDTO.setContents(bookEntity.getContents());
		bookDetailsDTO.setLogo(bookEntity.getLogo());
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

		bookDetailsDTO.setActive(bookEntity.getActive());
		bookDetailsDTO.setBookId(bookEntity.getBookId());
		bookDetailsDTO.setCategory(bookEntity.getCategory());
		bookDetailsDTO.setContents(bookEntity.getContents());
		bookDetailsDTO.setLogo(bookEntity.getLogo());
		bookDetailsDTO.setPrice(bookEntity.getPrice());
		bookDetailsDTO.setPublished(bookEntity.getPublished());
		bookDetailsDTO.setPublisher(bookEntity.getPublisher());
		bookDetailsDTO.setTitle(bookEntity.getTitle());
		bookDetailsDTO.setUpdateDate(bookEntity.getUpdateDate());

		return bookDetailsDTO;

	}

}