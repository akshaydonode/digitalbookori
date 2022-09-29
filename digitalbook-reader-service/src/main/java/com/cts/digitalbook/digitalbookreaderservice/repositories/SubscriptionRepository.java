package com.cts.digitalbook.digitalbookreaderservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.digitalbook.digitalbookreaderservice.entities.ReaderEntity;
import com.cts.digitalbook.digitalbookreaderservice.entities.SubscriptionEntity;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {

	@Query("select s from SubscriptionEntity s where s.readerEntity.readerId=?1")
	Optional<List<SubscriptionEntity>> findSubscriptionByReaderID(int readerId);

	@Query("select s from SubscriptionEntity s where s.bookId=?1 and s.readerEntity.readerId=?2")
	Optional<SubscriptionEntity> findByBookIdAndReaderId(int bookId, int readerId);

	@Query("select s.readerEntity from SubscriptionEntity s where s.bookId=?1")
	List<ReaderEntity> getReaderIdsByBookId(int bookId);

}
