package com.cts.training.digitalbooknotificationservice.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.training.digitalbooknotificationservice.clients.ReaderServiceClient;
import com.cts.training.digitalbooknotificationservice.dtos.Book;
import com.cts.training.digitalbooknotificationservice.dtos.SubscriptionDetailsDTO;
import com.cts.training.digitalbooknotificationservice.entities.NotificationEntity;
import com.cts.training.digitalbooknotificationservice.repositories.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	ReaderServiceClient readerServiceClient;

	@Autowired
	NotificationRepository notificationRepository;

	@Override
	public void sendNotification(Book book2) {
		Optional<SubscriptionDetailsDTO> subsDetailsOptional = readerServiceClient
				.getReaderDetailsByBookId(book2.getBookId());

		for (int readerId : subsDetailsOptional.get().getReaderId()) {
			NotificationEntity notificationEntity = new NotificationEntity();
			notificationEntity.setCreatedDate(new Date());
			notificationEntity.setRead(false);
			notificationEntity.setNotificationMessage(
					"Book Name: " + book2.getBookName() + " is blocked by book author. book is unavailable to read");
			notificationEntity.setReaderId(readerId);
			notificationRepository.save(notificationEntity);
		}

	}

}
