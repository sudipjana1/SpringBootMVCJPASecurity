package com.iiht.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iiht.cognizant.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) = :title" )
    List<Book> findByBookTitle(@Param("title") String title);

}
