package com.cts.digitalbook.digitalbookauthorservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

	@Query("select a from AuthorEntity a where a.authorEmail=?1")
	public Optional<AuthorEntity> findByEmailId(String authorEmail);

	@Query("select a from AuthorEntity a where a.authorName=?1")
	public Optional<AuthorEntity> findByName(String authorName);

}
