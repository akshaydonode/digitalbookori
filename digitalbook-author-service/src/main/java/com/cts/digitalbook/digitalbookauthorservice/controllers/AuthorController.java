package com.cts.digitalbook.digitalbookauthorservice.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.digitalbook.digitalbookauthorservice.dtos.AuthorLoginDTO;
import com.cts.digitalbook.digitalbookauthorservice.dtos.ResponseDTO;
import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;
import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookauthorservice.services.AuthorService;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/signup")
	public ResponseDTO registerAuthor(@RequestBody AuthorEntity authorEntity) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			AuthorEntity authorEntity2 = authorService.registerAuthorService(authorEntity);
			responseDto.setResult(authorEntity2);
			responseDto.setMessage("Author Registered Successfully...");
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;

	}

	@GetMapping("/login")
	public ResponseDTO loginAuthor(@RequestBody AuthorLoginDTO authorLoginDTO) {
		ResponseDTO responseDto = new ResponseDTO();

		try {
			AuthorEntity authorEntity2 = authorService.authorLogin(authorLoginDTO);
			responseDto.setResult(authorEntity2);
			responseDto.setMessage("Author Login successfully");
		} catch (DigitalBookException e) {
			responseDto.setException(e.getMessage());
		}

		return responseDto;
	}

	@GetMapping("getAuthorByID/{authorId}")
	public Optional<AuthorEntity> getAuthorByID(@PathVariable int authorId) {
		return authorService.getAuthorById(authorId);
	}
	
	@GetMapping("author/getAuthorByName/{authorName}")
	public Optional<AuthorEntity> getAuthorByName(@PathVariable String authorName){
		return authorService.getAuthorByName(authorName);
	}

}