package com.github.rolven95.database.service;

import com.github.rolven95.database.mapper.BookMapper;
import com.github.rolven95.database.model.BookModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public List<BookModel> getAllBooks() {
        return bookMapper.selectAll();
    }

    public BookModel getById(Integer id) {
        return bookMapper.getById(id);
    }

    public void insert(BookModel bookModel) {
        log.debug("Service, inserting book:{}", bookModel);
        bookMapper.insert(bookModel);
    }
}