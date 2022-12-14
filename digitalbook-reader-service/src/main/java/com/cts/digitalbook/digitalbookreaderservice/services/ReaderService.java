package com.cts.digitalbook.digitalbookreaderservice.services;

import java.util.Optional;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookSubscribeDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.SubscriptionDetailsDTO;
import com.cts.digitalbook.digitalbookreaderservice.entities.ReaderEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribeDetailsEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;

public interface ReaderService {

	ReaderEntity addReader(ReaderEntity readerEntity) throws DigitalBookException;

	SubscriptionEntity bookSubscribe(BookSubscribeDTO bookSubscribeDTO) throws DigitalBookException;

	Optional<SubscribeDetailsEntity> getReaderSubscribeBook(String readerEmailId) throws DigitalBookException;

	BookDetailsDTO getReaderBook(String readerEmailId, int bookId) throws DigitalBookException;

	BookDetailsDTO getReaderBookBySubscriptionId(String readerEmailId, int subscriptionId) throws DigitalBookException;

	String unSubscribeBook(String readerEmailId, int bookId) throws DigitalBookException;

	Optional<SubscriptionDetailsDTO> getReaderDetailsByBookId(int bookId) throws DigitalBookException;

	Optional<ReaderEntity> getReader(String readerEmail);

	String updateBlockBookDetails(int bookId) throws DigitalBookException;

	String updateUnBlockBookDetails(int bookId) throws DigitalBookException;

}
