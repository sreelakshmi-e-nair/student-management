package com.data.book.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.data.book.demo.exceptions.BookGeneralException;
import com.data.book.demo.models.Book;


@Service
public class BookService{
	@Value("${bookapplication.getBooks}")
	private String url;
	private RestTemplate restTemplate;
	
	@Autowired
	 public BookService(RestTemplateBuilder restTemplateBuilder) {
		
		 this.restTemplate=restTemplateBuilder.build();
		 
	}
	

	public List<Book> getBookData() {
		
		 try {
			 return restTemplate.exchange(url, HttpMethod.GET, null, List.class).getBody();
			 
		 }catch(HttpClientErrorException e) {
			 throw new HttpClientErrorException(e.getStatusCode());
		 }
		 catch(Exception e) {
			 throw new BookGeneralException("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		
		 
	}
	 	 
	 
}
