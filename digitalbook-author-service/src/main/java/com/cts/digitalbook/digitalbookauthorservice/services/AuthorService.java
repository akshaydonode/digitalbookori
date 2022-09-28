package com.cts.digitalbook.digitalbookauthorservice.services;

import java.util.Optional;

import com.cts.digitalbook.digitalbookauthorservice.dtos.AuthorLoginDTO;
import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;
import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;

public interface AuthorService {

	public AuthorEntity registerAuthorService(AuthorEntity author) throws DigitalBookException;

	public AuthorEntity authorLogin(AuthorLoginDTO authorLoginDTO) throws DigitalBookException;

	public Optional<AuthorEntity> getAuthorById(int authorId);

	public Optional<AuthorEntity> getAuthorByName(String authorName);

}
