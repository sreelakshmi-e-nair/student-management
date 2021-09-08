package com.book.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.data.models.Book;
import com.book.data.services.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/book")
	public List<Book> fetchBookData() {
		
		return bookService.getBookData("books.json");
	}

}
