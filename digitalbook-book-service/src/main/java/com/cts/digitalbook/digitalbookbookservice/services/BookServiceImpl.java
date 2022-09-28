package com.cts.digitalbook.digitalbookbookservice.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookbookservice.clients.AuthorServiceClient;
import com.cts.digitalbook.digitalbookbookservice.clients.ReaderServiceClient;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.BookSearchDTO;
import com.cts.digitalbook.digitalbookbookservice.dtos.SubscribedBookDetailsDTO;
import com.cts.digitalbook.digitalbookbookservice.entities.Author;
import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;
import com.cts.digitalbook.digitalbookbookservice.entities.SubscribeDetailsEntity;
import com.cts.digitalbook.digitalbookbookservice.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorServiceClient authorServiceClient;

	@Autowired
	ReaderServiceClient readerServiceClient;

	@Autowired
	EntityDtoMapper mapper;

	@Override
	@Transactional
	public BookEntity createBookByAuthor(int authorId, MultipartFile image, BookEntity bookEntity)
			throws DigitalBookException {

		Optional<Author> authorEntity = authorServiceClient.getAuthorByID(authorId);

		BookEntity bookEntity2 = new BookEntity();

		if (!authorEntity.isEmpty()) {
			if (Objects.nonNull(bookEntity) && Objects.nonNull(bookEntity.getTitle())
					&& Objects.nonNull(bookEntity.getCategory()) && Objects.nonNull(bookEntity.getContents())) {
				Optional<BookEntity> bookEntityOpt = bookRepository.checkTitleExitOrNot(bookEntity.getTitle());

				if (bookEntityOpt.isEmpty()) {

					try {
						bookEntity2.setLogo(image.getBytes());
						bookEntity2.setImageType(image.getContentType());
						bookEntity2.setActive(bookEntity.getActive());
						bookEntity2.setAuthorId(bookEntity.getAuthorId());
						bookEntity2.setCategory(bookEntity.getCategory());
						bookEntity2.setContents(bookEntity.getContents());
						bookEntity2.setPrice(bookEntity.getPrice());
						bookEntity2.setPublished(new Date());
						bookEntity2.setPublisher(bookEntity.getPublisher());
						bookEntity2.setTitle(bookEntity.getTitle());
						bookEntity2.setUpdateDate(new Date());

					} catch (IOException e) {
						throw new DigitalBookException("Problem with book logo.");
					}
					return bookRepository.save(bookEntity2);

				} else {
					throw new DigitalBookException("Book already created with" + " '" + bookEntity.getTitle() + "' "
							+ "title. Please create with another title.");

				}
			} else {
				throw new DigitalBookException(
						"Please fill mandatory details like Book-Title, Book-Category and Book-Contents");
			}

		} else {
			throw new DigitalBookException("Author is not registered or valid....");
		}

	}

	@Override
	@Transactional
	public BookEntity updateBookDetails(int authorId, int bookId, BookEntity bookEntity) throws DigitalBookException {

		Optional<BookEntity> bookDetails = bookRepository.getBookDetailsByBookIdAndAuthorId(authorId, bookId);

		if (!bookDetails.isEmpty()) {
			if (Objects.nonNull(bookEntity.getCategory())) {
				bookDetails.get().setCategory(bookEntity.getCategory());
			}

			if (Objects.nonNull(bookEntity.getContents())) {
				bookDetails.get().setContents(bookEntity.getContents());
			}

			if (Objects.nonNull(bookEntity.getPublished())) {
				bookDetails.get().setPublished(bookEntity.getPublished());
			}

			if (Objects.nonNull(bookEntity.getPublisher())) {
				bookDetails.get().setPublisher(bookEntity.getPublisher());
			}

			if (Objects.nonNull(bookEntity.getLogo())) {
				bookDetails.get().setLogo(bookEntity.getLogo());
			}
			if (Objects.nonNull(bookEntity.getPrice())) {
				bookDetails.get().setPrice(bookEntity.getPrice());
			}
			if (Objects.nonNull(bookEntity.getActive())) {
				bookDetails.get().setActive(bookEntity.getActive());
			}
			bookDetails.get().setUpdateDate(new Date());

			// need to write for code for active status of book for reader
			return bookRepository.save(bookDetails.get());

		} else {
			throw new DigitalBookException("Invalid Book ID or Author ID...");
		}

	}

	@Override
	@Transactional
	public List<BookDetailsDTO> searchBook(BookSearchDTO bookSearchDTO) throws DigitalBookException {
		List<BookEntity> bookEntities;
		Optional<Author> authorEntity = null;
		if (Objects.nonNull(bookSearchDTO)) {
			if (Objects.nonNull(bookSearchDTO.getAuthorName())) {
				System.out.println("searcing with author name");
				authorEntity = authorServiceClient.getAuthorByName(bookSearchDTO.getAuthorName());
				bookEntities = bookRepository.searchBook(bookSearchDTO.getTitle(), authorEntity.get().getAuthorId(),
						bookSearchDTO.getCategory(), bookSearchDTO.getPrice(), bookSearchDTO.getPublisher());
			} else {
				System.out.println("searcing withount author name");
				bookEntities = bookRepository.searchBookWithoutAuthorName(bookSearchDTO.getTitle(),
						bookSearchDTO.getCategory(), bookSearchDTO.getPrice(), bookSearchDTO.getPublisher());
			}

			if (!bookEntities.isEmpty()) {
				List<BookDetailsDTO> bookDetailsDTOs = new ArrayList();

				for (BookEntity bookEntity2 : bookEntities) {
					BookDetailsDTO bookDetailsDTO = mapper.bookDetailsDTO(bookEntity2, authorEntity);
					bookDetailsDTOs.add(bookDetailsDTO);
				}

				return bookDetailsDTOs;
			} else {
				throw new DigitalBookException("Oops. Didn't found any book.");
			}

		} else

		{
			throw new DigitalBookException("Please enter atleast one parameter for search.");
		}

//		List<BookEntity> bookEntity = bookRepository.searchBook(
//				bookSearchDTO.getTitle().isEmpty() ? bookSearchDTO.getTitle() : null,
//				authorEntity.isEmpty() ? null : authorEntity.get().getAuthorId(),
//				bookSearchDTO.getCategory().isEmpty() ? bookSearchDTO.getCategory() : null,
//				bookSearchDTO.getPrice() != 0 ? bookSearchDTO.getPrice() : null,
//				bookSearchDTO.getPublisher().isEmpty() ? bookSearchDTO.getPublisher() : null);

	}

	@Override
	public List<BookDetailsDTO> getAllBooks() throws DigitalBookException {
		List<BookEntity> bookEntities = bookRepository.findAll();
		List<BookDetailsDTO> bookDetailsDTOs = new ArrayList();

		if (Objects.nonNull(bookEntities)) {

			for (BookEntity bookEntity2 : bookEntities) {
				BookDetailsDTO bookDetailsDTO = mapper.bookDetailsDTO(bookEntity2);
				bookDetailsDTOs.add(bookDetailsDTO);
			}

			return bookDetailsDTOs;
		} else {
			throw new DigitalBookException("Books not present in database");
		}

	}

	@Override
	public List<SubscribedBookDetailsDTO> getReaderSubscribeBook(String readerEmailId) throws DigitalBookException {
		Optional<SubscribeDetailsEntity> subscribeDetailsEntity = readerServiceClient
				.getSubscribedBookDetails(readerEmailId);
		if (subscribeDetailsEntity.isEmpty()) {
			throw new DigitalBookException("Reader didn't subscribe any book.");
		} else {

			List<SubscribedBookDetailsDTO> bookDetailsDTOs = new ArrayList();

			for (int bookID : subscribeDetailsEntity.get().getBookIds()) {
				SubscribedBookDetailsDTO subscribedBookDetailsDTO = mapper.getBookDetails(bookID,
						subscribeDetailsEntity.get().getReaderId());
				bookDetailsDTOs.add(subscribedBookDetailsDTO);
			}

			return bookDetailsDTOs;
		}

	}

	@Override
	@Transactional
	public Optional<BookDetailsDTO> getBookDetails(int bookId) {

		BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();
		Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
		Optional<Author> authorEntityOpt = authorServiceClient.getAuthorByID(bookEntity.get().getAuthorId());

		bookDetailsDTO.setActive(bookEntity.get().getActive());
		if (!authorEntityOpt.isEmpty()) {
			bookDetailsDTO.setAuthorId(authorEntityOpt.get().getAuthorId());
			bookDetailsDTO.setAuthorName(authorEntityOpt.get().getAuthorName());
		}
		if (!bookEntity.isEmpty()) {
			bookDetailsDTO.setBookId(bookEntity.get().getBookId());
			bookDetailsDTO.setCategory(bookEntity.get().getCategory());
			bookDetailsDTO.setContents(bookEntity.get().getContents());
			bookDetailsDTO.setLogo(bookEntity.get().getLogo());
			bookDetailsDTO.setPrice(bookEntity.get().getPrice());
			bookDetailsDTO.setPublished(bookEntity.get().getPublished());
			bookDetailsDTO.setTitle(bookEntity.get().getTitle());
			bookDetailsDTO.setUpdateDate(bookEntity.get().getUpdateDate());
		}

		return Optional.of(bookDetailsDTO);
	}

}
