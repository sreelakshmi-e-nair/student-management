package com.book.data.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.book.data.exceptions.BookGeneralException;
import com.book.data.models.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class BookServiceImpl implements BookService {

	public List<Book> getBookData(String filePath) {

		JSONParser parser = new JSONParser();

		try {

			Object jsonBookData = parser.parse(new FileReader(new ClassPathResource(filePath).getFile()));
			JSONArray jsonBookList = (JSONArray)jsonBookData;
			List<Book> bookList=processJsonArrayToList(jsonBookList);

			return bookList;

		} catch (FileNotFoundException e) {

			throw new BookGeneralException("File not found!",HttpStatus.NOT_FOUND);

		} catch (IOException e) {

			throw new BookGeneralException(e.getMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE);

		} catch (ParseException e) {

			throw new BookGeneralException(e.getMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE);

		} catch (Exception e) {

			throw new BookGeneralException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


	private List<Book> processJsonArrayToList(JSONArray jsonBookList) {
		ObjectMapper objectMapper=new ObjectMapper();
		List<Book> bookList=new ArrayList<>();
		try {
			for(Object bookData:jsonBookList) {
				Book book =new Book();

				book=objectMapper.readValue(bookData.toString(), Book.class);

				bookList.add(book);
			}
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return bookList;
	}
}
