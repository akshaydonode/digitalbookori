package com.cts.digitalbook.digitalbookauthorservice.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.digitalbook.digitalbookauthorservice.dtos.AuthorLoginDTO;
import com.cts.digitalbook.digitalbookauthorservice.dtos.ResponseDTO;
import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;
import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookauthorservice.services.AuthorService;

@SpringBootTest
public class AuthorControllerTest {

	@InjectMocks
	AuthorController authorController;

	@Mock
	AuthorService authorService;

	@Test
	public void registerTest() throws DigitalBookException {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setAuthorEmail("akshay@mail.com");
		authorEntity.setAuthorId(1);
		authorEntity.setAuthorName("digitalbook");
		authorEntity.setPassword("QAWSED");

		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setMessage("Author Registered Successfully...");
		responseDto.setResult(responseDto);

		when(authorService.registerAuthorService(authorEntity)).thenReturn(authorEntity);
		//assertNotNull(authorController.registerAuthor(authorEntity), authorEntity);

		assertNotNull(authorController.registerAuthor(authorEntity));
	}

	@Test
	public void registerTestWithException() throws DigitalBookException {
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setAuthorEmail(null);

		when(authorService.registerAuthorService(authorEntity)).thenThrow(new DigitalBookException(
				"Author Registration Failed. PLease fill all the fileds like Name, EmailID, Password. Thank You !!!"));

		authorController.registerAuthor(authorEntity);

	}

	@Test
	public void loginTest() throws DigitalBookException {
		AuthorLoginDTO authorLoginDTO = new AuthorLoginDTO();
		authorLoginDTO.setAuthorEmail("aaa@gmail.com");
		authorLoginDTO.setPassword("qaws12");

		AuthorEntity authorEntity = new AuthorEntity();

		when(authorService.authorLogin(authorLoginDTO)).thenReturn(authorEntity);

		authorController.loginAuthor(authorLoginDTO);
	}

	@Test
	public void loginTestwithException() throws DigitalBookException {

		AuthorLoginDTO authorLoginDTO = new AuthorLoginDTO();
		when(authorService.authorLogin(authorLoginDTO))
				.thenThrow(new DigitalBookException("Author Not Registered. Please register Author"));
		authorController.loginAuthor(authorLoginDTO);
	}
}
