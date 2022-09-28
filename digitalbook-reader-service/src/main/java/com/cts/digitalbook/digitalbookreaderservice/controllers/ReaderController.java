package com.cts.digitalbook.digitalbookreaderservice.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookDetailsDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.BookSubscribeDTO;
import com.cts.digitalbook.digitalbookreaderservice.dtos.ResponseDTO;
import com.cts.digitalbook.digitalbookreaderservice.entities.ReaderEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscribeDetailsEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;
import com.cts.digitalbook.digitalbookreaderservice.services.ReaderService;

@RestController
@RequestMapping("/reader")
@CrossOrigin
public class ReaderController {

	@Autowired
	ReaderService readerService;

	@PostMapping("/addReader")
	public ResponseDTO addReader(@RequestBody ReaderEntity readerEntity) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			ReaderEntity readerEntity2 = readerService.addReader(readerEntity);
			responseDto.setResult(readerEntity2);
			responseDto.setMessage("Reader added Successfully.");
		} catch (DigitalBookException e) {

			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}

	@PostMapping("/book/subscribe")
	public ResponseDTO bookSubscribe(@RequestBody BookSubscribeDTO bookSubscribeDTO) {
		ResponseDTO responseDto = new ResponseDTO();
		try {
			SubscriptionEntity subscriptionEntity = readerService.bookSubscribe(bookSubscribeDTO);
			responseDto.setResult(subscriptionEntity);
			responseDto.setMessage("Booked Subscribed Successfully.");
		} catch (DigitalBookException e) {

			responseDto.setException(e.getMessage());
		}
		return responseDto;
	}

//	@GetMapping("/{emailID}/books")
//	public ResponseDTO getSubscribeBooks(@PathVariable("emailID") String readerEmailId) {
//		ResponseDTO responseDto = new ResponseDTO();
//		List<SubscribedBookDetails> subscribedBookDetails;
//		try {
//			subscribedBookDetails = readerService.getReaderSubscribeBook(readerEmailId);
//
//			List<Object> response = new ArrayList<>();
//			response.add(subscribedBookDetails);
//			responseDto.setResponse(response);
//		} catch (DigitalBookException e) {
//			responseDto.setException(e.getMessage());
//		}
//
//		return responseDto;
//	}

	@GetMapping("/getSubscribeBook/{readerEmailId}")
	public Optional<SubscribeDetailsEntity> getSubscribeBooks(@PathVariable String readerEmailId)
			throws DigitalBookException {
		try {
			return readerService.getReaderSubscribeBook(readerEmailId);
		} catch (Exception e) {
			throw new DigitalBookException(e);
		}

	

	}

	@GetMapping("/{emailID}/books/bookid/{bookId}")
	public ResponseDTO getSubscribeBooks(@PathVariable("emailID") String readerEmailId, @PathVariable int bookId) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			BookDetailsDTO bookDetailsDTO = readerService.getReaderBook(readerEmailId, bookId);

			responseDto.setResult(bookDetailsDTO);
			responseDto.setMessage("Booked found Successfully.");

		} catch (DigitalBookException e) {

			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("/{emailID}/book/scriptionid/{subscriptionId}")
	public ResponseDTO getSubscribeBooksBySubscriptionId(@PathVariable("emailID") String readerEmailId,
			@PathVariable int subscriptionId) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			BookDetailsDTO bookDetailsDTO = readerService.getReaderBookBySubscriptionId(readerEmailId, subscriptionId);

			responseDto.setResult(bookDetailsDTO);
			responseDto.setMessage("Booked found Successfully.");

		} catch (DigitalBookException e) {

			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("/{emailID}/books/{bookId}/refund")
	public ResponseDTO refundBook(@PathVariable("emailID") String readerEmailId, @PathVariable int bookId) {
		System.out.println("(\"/{emailId}/books/{bookId}/refund\") controller got call");
		ResponseDTO responseDto = new ResponseDTO();
		try {
			String message = readerService.unSubscribeBook(readerEmailId, bookId);
			responseDto.setMessage(message);

		} catch (DigitalBookException e) {

			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

}
