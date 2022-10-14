package com.cts.digitalbook.digitalbookreaderservice.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookreaderservice.clients.BookServiceClient;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookSubscribeDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.SubscriptionDetailsDTO;
import com.cts.digitalbook.digitalbookreaderservice.entities.ReaderEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribeDetailsEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribedBookDetails;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;
import com.cts.digitalbook.digitalbookreaderservice.repositories.ReaderRepository;
import com.cts.digitalbook.digitalbookreaderservice.repositories.SubscriptionRepository;

@Service
public class ReaderServiceImpl implements ReaderService {

	@Autowired
	EntityDtoMapper mapper;

	@Autowired
	ReaderRepository readerRepository;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	BookServiceClient bookServiceClient;

	@Override
	public ReaderEntity addReader(ReaderEntity readerEntity) throws DigitalBookException {
		ReaderEntity readerEntity2 = new ReaderEntity();
		if(readerEntity.getReaderEmail().length()>=0 && readerEntity.getReaderName().length()>0) {
			Optional<ReaderEntity> readerOptional = readerRepository.getReaderByEmail(readerEntity.getReaderEmail());

			if (readerOptional.isEmpty()) {
				readerEntity2.setReaderEmail(readerEntity.getReaderEmail().toLowerCase());
				readerEntity2.setReaderName(readerEntity.getReaderName());
				return readerRepository.save(readerEntity2);
			} else {
				throw new DigitalBookException("Reader is exit...");
			}
		}else {
			throw new DigitalBookException("Please add valid name and email");
		}
		

	}

	@Override
	public Optional<ReaderEntity> getReader(String readerEmail) {
		return readerRepository.getReaderByEmail(readerEmail);
	}

	@Override
	public SubscriptionEntity bookSubscribe(BookSubscribeDTO bookSubscribeDTO) throws DigitalBookException {

		// System.out.println("Objects.nonNull(bookSubscribeDTO):
		// "+Optional.of(bookSubscribeDTO).isEmpty());
		if (!Optional.of(bookSubscribeDTO).isEmpty()) {
			SubscriptionEntity subscriptionEntity = new SubscriptionEntity();

			Optional<SubscriptionEntity> subsOptional = subscriptionRepository
					.findByBookIdAndReaderId(bookSubscribeDTO.bookId, bookSubscribeDTO.getReaderId());

			if (!subsOptional.isEmpty()) {
				throw new DigitalBookException("Book Already Subscribed.");
			} else {
				if (!Optional.of(bookSubscribeDTO.getReaderId()).isEmpty()) {
					Optional<ReaderEntity> readerOptional = getReader(bookSubscribeDTO.getReaderEmail().toLowerCase());
					ReaderEntity readerEntity2 = new ReaderEntity();
					if (readerOptional.isEmpty()) {

						readerEntity2.setReaderEmail(bookSubscribeDTO.getReaderEmail());
						readerEntity2.setReaderName(bookSubscribeDTO.getReaderEmail());
						/* ReaderEntity readerEntity= */addReader(readerEntity2);

						subscriptionEntity.setSubscribed(true);
						subscriptionEntity.setBookId(bookSubscribeDTO.getBookId());
						subscriptionEntity.setReaderId(readerEntity2);
						subscriptionEntity.setSubscriptionDate(new Date());
						subscriptionEntity.setActive(true);

						return subscriptionRepository.save(subscriptionEntity);

					} else {
						subscriptionEntity.setSubscribed(true);
						subscriptionEntity.setBookId(bookSubscribeDTO.getBookId());
						subscriptionEntity.setReaderId(readerOptional.get());
						subscriptionEntity.setSubscriptionDate(new Date());
						subscriptionEntity.setActive(true);
						return subscriptionRepository.save(subscriptionEntity);
					}
				} else {
					Optional<ReaderEntity> readerOptional = readerRepository.findById(bookSubscribeDTO.getReaderId());
					subscriptionEntity.setBookId(bookSubscribeDTO.getBookId());
					subscriptionEntity.setReaderId(readerOptional.get());
					subscriptionEntity.setSubscriptionDate(new Date());

					return subscriptionRepository.save(subscriptionEntity);
				}
			}

		} else {
			throw new DigitalBookException("BookSubcriptionDTO is null, please retry again...");
		}

	}

	@Override
	public Optional<SubscribeDetailsEntity> getReaderSubscribeBook(String readerEmailId) throws DigitalBookException {

		Optional<ReaderEntity> readerOptional = getReader(readerEmailId);
		if (readerOptional.isEmpty()) {
			throw new DigitalBookException("Reader didn't subscribed any book.");
		} else {
			Optional<List<SubscriptionEntity>> subsCriptonalEntitiesOptional = subscriptionRepository
					.findSubscriptionByReaderID(readerOptional.get().getReaderId());
			if (subsCriptonalEntitiesOptional.isEmpty()) {

				throw new DigitalBookException("Oops. Didn't found any subscribed book. please subscribe the book.");

			} else {
				SubscribeDetailsEntity subscribeDetailsEntity = new SubscribeDetailsEntity();

				subscribeDetailsEntity.setReaderId(readerOptional.get().getReaderId());
				List<Integer> integers = new ArrayList<Integer>();
				// List<Date> subscribeDate
				for (SubscriptionEntity subscriptionEntity : subsCriptonalEntitiesOptional.get()) {
					System.out.println(subscriptionEntity.toString());
					if (subscriptionEntity.isActive()) {
						integers.add(subscriptionEntity.getBookId());

					}
				}
				subscribeDetailsEntity.setBookIds(integers);
				return Optional.of(subscribeDetailsEntity);
			}

		}

	}

	@Override
	public BookDetailsDTO getReaderBook(String readerEmailId, int bookId) throws DigitalBookException {

		Optional<BookDetailsDTO> bookDetailsOptional = bookServiceClient.getBookDetails(bookId);

		if (bookDetailsOptional.isEmpty()) {
			throw new DigitalBookException("bookdetails not found.");
		} else {
			return bookDetailsOptional.get();
		}

	}

	@Override
	public BookDetailsDTO getReaderBookBySubscriptionId(String readerEmailId, int subscriptionId)
			throws DigitalBookException {

		Optional<ReaderEntity> readerOptional = getReader(readerEmailId);

		if (readerOptional.isEmpty()) {
			throw new DigitalBookException("Reader didn't subscribed any book.");
		} else {
			Optional<SubscriptionEntity> subOptional = subscriptionRepository.findById(subscriptionId);
			if (subOptional.isEmpty()) {
				throw new DigitalBookException("Subscription id wrong...");
			} else {
				BookDetailsDTO bookDetailsDTO = getReaderBook(readerEmailId, subOptional.get().getBookId());
				if (Objects.nonNull(bookDetailsDTO)) {
					return bookDetailsDTO;
				} else {
					throw new DigitalBookException("Book not fond, may book service is down");
				}
			}
		}

	}

	@Override
	public String unSubscribeBook(String readerEmailId, int bookId) throws DigitalBookException {

		Optional<ReaderEntity> readerOptional = getReader(readerEmailId);
		if (readerOptional.isEmpty()) {
			throw new DigitalBookException("Reader didn't subscribed any book.");
		} else {
			Optional<SubscriptionEntity> subOptional = subscriptionRepository.findByBookIdAndReaderId(bookId,
					readerOptional.get().getReaderId());
			if (subOptional.isEmpty()) {
				throw new DigitalBookException("Reader didn't subscribed any book.");
			} else {
				System.out.println(
						new Date().getHours() - subOptional.get().getSubscriptionDate().getHours() + " total hrs");
				System.out.println();
				if (new Date().getHours() - subOptional.get().getSubscriptionDate().getHours() <= 24
						&& new Date().getDate() - subOptional.get().getSubscriptionDate().getDate() == 0) {
					subOptional.get().setSubscribed(false);
					subscriptionRepository.save(subOptional.get());
					return "Book Unsubscribed successfully...";
				} else {
					throw new DigitalBookException("You can't unsubscribe book after 24 hrs, thanks");
				}

			}
		}
	}

	@Override
	public Optional<SubscriptionDetailsDTO> getReaderDetailsByBookId(int bookId) throws DigitalBookException {
		List<ReaderEntity> readerEntities = subscriptionRepository.getReaderIdsByBookId(bookId);

		if (!readerEntities.isEmpty()) {

			SubscriptionDetailsDTO subscriptionDetailsDTO = new SubscriptionDetailsDTO();
			List<Integer> readerIds = new ArrayList<>();

			for (ReaderEntity readerEntity : readerEntities) {
				readerIds.add(readerEntity.getReaderId());
			}
			subscriptionDetailsDTO.setBookId(bookId);
			subscriptionDetailsDTO.setReaderId(readerIds);
			return Optional.of(subscriptionDetailsDTO);

		} else {
			throw new DigitalBookException("book id don't have subscribed reader.");
		}

	}

	@Override
	public String updateBlockBookDetails(int bookId) throws DigitalBookException {
		Optional<List<SubscriptionEntity>> optionalSubscriptionEntity = subscriptionRepository
				.getBookSubscriptionDetails(bookId);

		if (optionalSubscriptionEntity.isEmpty()) {
			throw new DigitalBookException("book id don't have subscribed reader.");

		} else {
			for (SubscriptionEntity subscriptionEntity : optionalSubscriptionEntity.get()) {
				Optional<SubscriptionEntity> subscriptionEntity2 = subscriptionRepository
						.findById(subscriptionEntity.getSubscriptionId());
				if (subscriptionEntity2.isEmpty()) {
					throw new DigitalBookException("book id don't have subscribed reader.");
				} else {
					subscriptionEntity2.get().setActive(false);
					subscriptionRepository.save(subscriptionEntity2.get());
				}
			}
		}

		return "Blocked book updated sucessfully";
	}

	@Override
	public String updateUnBlockBookDetails(int bookId) throws DigitalBookException {
		Optional<List<SubscriptionEntity>> optionalSubscriptionEntity = subscriptionRepository
				.getBookSubscriptionDetails(bookId);

		if (optionalSubscriptionEntity.isEmpty()) {
			throw new DigitalBookException("book id don't have subscribed reader.");

		} else {
			for (SubscriptionEntity subscriptionEntity : optionalSubscriptionEntity.get()) {
				Optional<SubscriptionEntity> subscriptionEntity2 = subscriptionRepository
						.findById(subscriptionEntity.getSubscriptionId());
				if (subscriptionEntity2.isEmpty()) {
					throw new DigitalBookException("book id don't have subscribed reader.");
				} else {
					subscriptionEntity2.get().setActive(true);
					subscriptionRepository.save(subscriptionEntity2.get());
				}
			}
		}

		return " Un-Blocked book updated sucessfully";
	}

}
