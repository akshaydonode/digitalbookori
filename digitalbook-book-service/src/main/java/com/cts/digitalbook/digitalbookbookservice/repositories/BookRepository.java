package com.cts.digitalbook.digitalbookbookservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.digitalbook.digitalbookbookservice.entities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	@Query("select b from BookEntity b where b.bookId=?2 and b.authorId=?1")
	Optional<BookEntity> getBookDetailsByBookIdAndAuthorId(int authorId, int bookId);

	@Query("select b from BookEntity b where b.title=?1")
	Optional<BookEntity> checkTitleExitOrNot(String title);

	@Query("select b from BookEntity b where b.title LIKE %?1% OR b.authorId = ?2 OR b.category LIKE %?3% OR b.price =?4 OR b.publisher LIKE %?5%")
	List<BookEntity> searchBook(String title, int authorId, String category, double price, String publisher);

	@Query("select b from BookEntity b where b.title LIKE %?1% OR b.category LIKE %?2% OR b.price =?3 OR b.publisher LIKE %?4%")
	List<BookEntity> searchBookWithoutAuthorName(String title, String category, double price, String publisher);

	@Query("select b from BookEntity b where b.bookId=?1 AND b.isAtive=true")
	Optional<BookEntity> findByBookId(int bookID);
	
	@Query("select b from BookEntity b where b.authorId=?1")
	Optional<List<BookEntity>> getAuthorBooks(int authorId);

//	@Query("select b BookEntity b where b.bookId=?2 and b.")
//	Optional<BookEntity> findBookByBookIdAndAuthorId(int authorId, int bookId);

}

//List<BookEntity> searchBook(Object object, Integer integer, Object object2, double price, Object object3);

////@Query("select b from BookEntity b where b.title LIKE ('%', ?1, '%') OR b.category LIKE ('%', ?3, '%') or b.price LIKE ('%', ?4, '%') or b.publisher LIKE ('%', ?5, '%') or b.authorId in (select a from AuthorEntity a where a.authorName LIKE ('%', ?2,'%'))")
//List<BookEntity> searchBook(String title, String authorName, String category, double price, String publisher);