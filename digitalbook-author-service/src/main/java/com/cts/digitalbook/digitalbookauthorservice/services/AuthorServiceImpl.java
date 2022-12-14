package com.cts.digitalbook.digitalbookauthorservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookauthorservice.dtos.AuthorLoginDTO;
import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;
import com.cts.digitalbook.digitalbookauthorservice.exceptions.DigitalBookException;
import com.cts.digitalbook.digitalbookauthorservice.repositories.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public AuthorEntity registerAuthorService(AuthorEntity authorEntity) throws DigitalBookException {

		if (!authorEntity.getAuthorEmail().isEmpty() && !authorEntity.getPassword().isEmpty()
				&& !authorEntity.getAuthorName().isEmpty()) {

			Optional<AuthorEntity> optionalAuthor = authorRepository.findByEmailId(authorEntity.getAuthorEmail());
			if (optionalAuthor.isEmpty()) {

				AuthorEntity newAuthorEntity = new AuthorEntity();

				newAuthorEntity.setAuthorEmail(authorEntity.getAuthorEmail().toLowerCase());
				newAuthorEntity.setAuthorName(authorEntity.getAuthorName());
				newAuthorEntity.setPassword(authorEntity.getPassword());

				return authorRepository.save(newAuthorEntity);

			} else {
				throw new DigitalBookException(
						"Author EmailID id already Exit. Please use the same or create new account. Thank You !!!");

			}
		} else {

			throw new DigitalBookException(
					"Author Registration Failed. PLease fill all the fileds like Name, EmailID, Password. Thank You !!!");

		}
	}

	@Override
	public AuthorEntity authorLogin(AuthorLoginDTO authorLoginDTO) throws DigitalBookException {
		Optional<AuthorEntity> optionalAuthor = authorRepository
				.findByEmailId(authorLoginDTO.getAuthorEmail().toLowerCase());

		if (!optionalAuthor.isEmpty()) {
			if (optionalAuthor.get().getPassword().equals(authorLoginDTO.getPassword())) {
				return optionalAuthor.get();
			} else {
				throw new DigitalBookException("Please enter the correct password.");
			}
		} else {
			throw new DigitalBookException("Author Not Registered. Please register Author");
		}

	}

	@Override
	@Cacheable(key = "#authorId",value = "authorIdStore")
	public Optional<AuthorEntity> getAuthorById(int authorId) {
		return authorRepository.findById(authorId);

	}

	@Override
	@Cacheable(key = "#authorName",value = "authorNameStore")
	public Optional<AuthorEntity> getAuthorByName(String authorName) {

		return authorRepository.findByName(authorName);
	}
}
