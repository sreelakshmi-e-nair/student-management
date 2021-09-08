package com.book.data.services;

import java.util.List;

import com.book.data.models.Book;

public interface BookService {
	
	public List<Book> getBookData(String filePath);

}
