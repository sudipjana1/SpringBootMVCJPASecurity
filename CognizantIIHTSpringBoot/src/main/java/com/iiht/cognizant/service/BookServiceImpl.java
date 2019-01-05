package com.iiht.cognizant.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.iiht.cognizant.model.Book;
import com.iiht.cognizant.repository.BookRepository;

@Component
public class BookServiceImpl implements BookService{
	@Autowired
	BookRepository bookdao;
	
	@Override
	public void addBook(Book book) {
		bookdao.save(book);
	}
	
	@Override
	public Optional<Book> searchBookbyId(long bookId) {
		Optional<Book> book = null;
		book = bookdao.findById(bookId);
		return book;
	}
	
	@Override
	public boolean deleteBookbyId(long bookId) {
		//bookdao.deleteBookById(bookId);
		if( bookdao.findById(bookId) == null) {
			return false;
		}else {
			bookdao.deleteById(bookId);
			return true;
		}
		
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> listbook = new <Book>ArrayList();
		listbook = bookdao.findAll();
		return listbook;
	}


	@Override
	public List<Book> getBookByTitle(String title) {
		List<Book> listbook = bookdao.findByBookTitle(title);
		System.out.println(listbook.toString());
		
		return listbook;
	}
	
}
