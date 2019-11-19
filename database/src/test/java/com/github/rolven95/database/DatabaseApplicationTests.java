package com.github.rolven95.database;

import com.github.rolven95.database.model.BookModel;
import com.github.rolven95.database.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseApplicationTests {

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
	}

	@Test
	void insert(){
		BookModel bookModel = new BookModel();
		bookModel.setAuthor("A221");
		bookModel.setId(9);
		bookModel.setName("book9");
		bookModel.setPrice(9D);

		bookService.insert(bookModel);
	}
}