package com.cts.digitalbook.digitalbookreaderservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.digitalbook.digitalbookreaderservice.entities.ReaderEntity;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity, Integer> {

	@Query("select r from ReaderEntity r where r.readerEmail=?1")
	Optional<ReaderEntity> getReaderByEmail(String readerEmail);

}
