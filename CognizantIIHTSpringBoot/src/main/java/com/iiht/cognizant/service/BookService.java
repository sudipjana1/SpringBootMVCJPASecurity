package com.iiht.cognizant.service;



import java.util.List;
import java.util.Optional;

import com.iiht.cognizant.model.Book;

public interface BookService {

	public void addBook(Book book);
	public Optional<Book> searchBookbyId(long bookId);
	public boolean deleteBookbyId(long bookId) ;
	public List<Book> getAllBooks();
	public List<Book> getBookByTitle(String title);
}
